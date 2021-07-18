package com.example.demo.controllers;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/demo")
public class HelloWorldController {

    @GetMapping
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("ok");
    }

}
