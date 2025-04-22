package com.cassionoronha.ai.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cassionoronha.ai.service.RagService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Faq API", description = "Ask the AI a question about the WEF Future of Jobs 2025 report")
@CrossOrigin(origins = "*")
@RequestMapping("/faq")
@RestController
public class FaqController {
    private final RagService ragService;

    public FaqController(RagService ragService) {
        this.ragService = ragService;
    }

    @GetMapping
    public String faq(@RequestParam(value = "message", required = false) String message) {
        if (message == null || message.trim().isEmpty()) {
            return "Please provide a question parameter in the request.";
        }
        return ragService.faq(message);
    }
}
