package com.cassionoronha.ai.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class RagService {
    private final ChatClient chatClient;

    @Value("classpath:rag-prompt-template.st")
    private Resource ragPromptTemplate;
    private final VectorStore vectorStore;

    public RagService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder
            .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
            .build();
        this.vectorStore = vectorStore;
    }

    public String faq(String message) {
        // Retrieve context documents if needed
        List<Document> similarDocuments = vectorStore.similaritySearch(
                SearchRequest.query(message).withTopK(2));
        List<String> contentList = similarDocuments.stream()
                .map(Document::getContent)
                .toList();

        // Render the prompt using your template. The PromptTemplate class
        // should take your template and render it with the provided parameters.
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", message);
        promptParameters.put("documents", String.join("\n", contentList));
        Prompt finalPrompt = promptTemplate.create(promptParameters);
        System.out.println(finalPrompt.getContents());
        return chatClient.prompt()
            .user(finalPrompt.getContents())
            .call()
            .content();
        // return chatClient.prompt(finalPrompt).call()
        //     .content();

    }

}
