package com.web.web_spingboot.controller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class WasmController {

    @GetMapping("/Build")
    public ResponseEntity<byte[]> getWasmFile() throws IOException {
        // 从 resources/static/build 目录的上级目录加载 .wasm 文件
        ClassPathResource resource = new ClassPathResource("../static/build/zhouweilun.wasm");
        byte[] wasmData = Files.readAllBytes(Path.of(resource.getURI()));

        // 设置正确的 Content-Type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/wasm"));

        // 返回带有正确 Content-Type 的响应
        return new ResponseEntity<>(wasmData, headers, HttpStatus.OK);
    }
}
