package com.web.web_spingboot.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VideoController {

    private final Map<String, Boolean> userVideoStatus = new HashMap<>(); // 用于存储用户的视频状态，key为用户ID，value为视频状态（是否开启）
    private final Map<String, byte[]> userVideoStreams = new HashMap<>(); // 用于存储用户的视频流，key为用户ID，value为视频流数据
    private final SimpMessagingTemplate stompTemplate; // 用于向WebSocket发送消息的模板

    public VideoController(SimpMessagingTemplate stompTemplate) {
        this.stompTemplate = stompTemplate;
    }

    // 处理开始通话请求
    @MessageMapping("/startCall")
    public void startCall() {
        // 向所有用户发送开始通话事件
        broadcastEvent("startCall");
        System.out.println("开始");
    }

    // 处理结束通话请求
    @MessageMapping("/endCall")
    public void endCall() {
        // 向所有用户发送结束通话事件
        broadcastEvent("endCall");
        System.out.println("结束");
    }

    // 处理用户视频状态的消息
    @MessageMapping("/videoStatus")
    public void handleVideoStatus(VideoStatusMessage statusMessage) {
        // 从消息中获取用户ID和视频状态
        String userId = statusMessage.getUserId();
        boolean videoStatus = statusMessage.isVideoStatus();

        // 更新用户的视频状态
        userVideoStatus.put(userId, videoStatus);

        // 向所有客户端发送更新后的用户列表
        broadcastUserList();
        System.out.println("视频状态");
    }

    // 处理接收到的视频数据消息
    @MessageMapping("/video")
    public void handleVideoData(byte[] videoData, org.springframework.messaging.Message<byte[]> message) {
        String senderId = (String) message.getHeaders().get("simpSessionId");
        userVideoStreams.put(senderId, videoData);
        broadcastVideoData(videoData, senderId);
        System.out.println("处理视频");
    }

    // 广播视频数据给所有客户端
    private void broadcastVideoData(byte[] videoData, String senderId) {
        userVideoStreams.forEach((userId, data) -> {
            if (userVideoStatus.getOrDefault(userId, false) && !userId.equals(senderId)) {
                stompTemplate.convertAndSendToUser(userId, "/topic/video", videoData);
            }
        });
        System.out.println("广播视频");
    }

    // 广播当前用户列表给所有客户端
    private void broadcastUserList() {
        stompTemplate.convertAndSend("/topic/userList", new UserListData(new ArrayList<>(userVideoStatus.keySet())));
        broadcastUserCount(); // 更新参会人数
        System.out.println("广播用户");
    }

    // 广播当前参会人数给所有客户端
    private void broadcastUserCount() {
        stompTemplate.convertAndSend("/topic/userCount", userVideoStatus.size());
    }

    // 广播通话事件给所有客户端
    private void broadcastEvent(String eventName) {
        stompTemplate.convertAndSend("/topic/" + eventName, "");
    }

    // 内部类，用于存储视频状态消息
    public static class VideoStatusMessage {
        private String userId;
        private boolean videoStatus;

        // Getters and setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public boolean isVideoStatus() {
            return videoStatus;
        }

        public void setVideoStatus(boolean videoStatus) {
            this.videoStatus = videoStatus;
        }
    }

    // 内部类，用于存储用户列表数据
    static class UserListData {
        private List<String> users;

        public UserListData(List<String> users) {
            this.users = users;
        }

        public List<String> getUsers() {
            return users;
        }

        public void setUsers(List<String> users) {
            this.users = users;
        }
    }
}
