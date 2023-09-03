package com.example.threads.controller;

import com.example.threads.service.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/server")
public class CloudController {
    @Autowired
    private CloudService service;

    @PostMapping(params = "size")
    @ResponseStatus(HttpStatus.CREATED)
    public String requestServer(@RequestParam(value = "size", required = true) int size) {
        return service.requestServer(size);
    }

}
