package com.cassionoronha.ai.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import com.cassionoronha.ai.service.ChatService;

@Tag(name = "Chat API", description = "Operations related to the Chat functionality")
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @Operation(summary = "Ask Chat", description = "Endpoint to send a query to the chat service")
    @GetMapping("/ask")
    public ResponseEntity<String> ask(@Parameter(description = "The prompt for the chat query")
                                      @RequestParam(value = "prompt", required = false) String prompt) {
        if (prompt == null || prompt.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Please provide a prompt parameter in the request.");
        }
        String response = chatService.ask(prompt);
        return ResponseEntity.ok(response);
    }
}
