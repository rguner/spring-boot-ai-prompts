package org.example.springbootaiprompts.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimplePromptController {

    private final ChatClient.Builder chatClientBuilder;

    @GetMapping("")
    public String simplePrompt() {
        ChatClient chatClient = chatClientBuilder.build();
        return chatClient.prompt(new Prompt("Tell me a dad joke")).call().content();
    }

}
