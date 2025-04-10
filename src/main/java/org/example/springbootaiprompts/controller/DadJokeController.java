package org.example.springbootaiprompts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DadJokeController {

    private final ChatClient.Builder chatClientBuilder;

    @GetMapping("dad-joke")
    public String joke() {

        ChatClient chatClient = chatClientBuilder.build();
        UserMessage userMessage = new UserMessage("Tell me a joke about cats");
        Prompt prompt = new Prompt(userMessage);
        return chatClient.prompt(prompt).call().content();

    }

    @GetMapping("dad-joke-with-system-message")
    public String userAndSystemMessage() {

        ChatClient chatClient = chatClientBuilder.build();
        SystemMessage systemMessage = new SystemMessage("""
        Your primary function is tell Dad Jokes. 
        If someone asks you any other type of joke please tell them you only know Dad Jokes.
        """);
        UserMessage userMessage = new UserMessage("Tell me a serios joke about the universe");
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        return chatClient.prompt(prompt).call().content();

    }
}
