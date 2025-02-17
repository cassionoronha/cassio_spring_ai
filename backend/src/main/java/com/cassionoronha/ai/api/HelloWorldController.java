package com.cassionoronha.ai.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation; // added import

@Tag(name = "HelloWorld API", description = "Operations related to the Hello World endpoint")
@RestController
public class HelloWorldController {

    @Operation(summary = "Returns a greeting", description = "Simple endpoint that returns 'Hello, World!'") 
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
