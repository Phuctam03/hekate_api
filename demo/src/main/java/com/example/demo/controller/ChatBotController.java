package com.example.demo.controller;

import com.example.demo.Request.ChatBotRequest;
import com.example.demo.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;

    @PostMapping
    public ResponseEntity<Object> processChatBotRequest(@RequestBody ChatBotRequest request){
        Object response = chatBotService.processChatbotRequest(request);
        return  ResponseEntity.ok(response);
    }
}
