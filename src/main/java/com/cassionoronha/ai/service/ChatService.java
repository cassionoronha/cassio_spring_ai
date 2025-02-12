package com.cassionoronha.ai.service;

import org.springframework.stereotype.Service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String ask(String prompt) {
        CallResponseSpec response = chatClient.prompt()
                .user(prompt)
                .call();
        return response.content();
    }
}
