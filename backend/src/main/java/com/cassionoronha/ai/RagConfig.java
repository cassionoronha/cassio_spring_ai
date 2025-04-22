package com.cassionoronha.ai;

import java.io.File;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;



@Configuration
public class RagConfig {
    @Value("classpath:/WEF_Future_of_Jobs_2025_Press_Release_PTBR.pdf")
    private Resource pdfResource;

    @Bean
    SimpleVectorStore vectorStore(EmbeddingModel embeddingModel) {
        SimpleVectorStore vectorStore = new SimpleVectorStore(embeddingModel);

        File vectorStoreFile = new File("vector-store.json");
        if (vectorStoreFile.exists()) {
            System.out.println("Loading vector store from file: " + vectorStoreFile.getAbsolutePath());
            vectorStore.load(vectorStoreFile);
        } else {
            System.out.println("Creating new vector store file");

            TextReader textReader = new TextReader(pdfResource);
            textReader.getCustomMetadata()
                  .put("filename", "WEF_Future_of_Jobs_2025_Press_Release_PTBR.pdf");
            List<Document> documents = textReader.get();

            TextSplitter textSplitter = new TokenTextSplitter(7000, 500, 0, 50, false);
            List<Document> splitDocuments = textSplitter.apply(documents);

            vectorStore.add(splitDocuments);
        }
        return vectorStore;
    }
}
