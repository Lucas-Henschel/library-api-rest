package com.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class MainController {
    @GetMapping
    public ResponseEntity<String> init() {
        return ResponseEntity.ok().body("Library API REST is running 🔥");
    }
}
