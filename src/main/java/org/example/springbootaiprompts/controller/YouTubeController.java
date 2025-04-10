package org.example.springbootaiprompts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/youtube")
public class YouTubeController {

    private final ChatClient.Builder chatClientBuilder;

    @Value("classpath:/prompts/youtube.st")
    private Resource ytPromptResource;

    @GetMapping("popular")
    public String findPopularYoutubersByGenre(@RequestParam(value = "genre", defaultValue = "tech") String genre) {

        ChatClient chatClient = chatClientBuilder.build();
        String message = """
                List 10 of the most popular youtubers in {genre} along with their subscriber counts.
                if you don't know answer, just say "I don't know"
                """;

        PromptTemplate promptTemplate = new PromptTemplate(message);
        Prompt prompt = promptTemplate.create(Map.of("genre", genre));
        return chatClient.prompt(prompt).call().content();
    }

    @GetMapping("popular2")
    public String findPopularYoutubersByGenre2(@RequestParam(value = "genre", defaultValue = "tech") String genre) {

        ChatClient chatClient = chatClientBuilder.build();
        PromptTemplate promptTemplate = new PromptTemplate(ytPromptResource);
        Prompt prompt = promptTemplate.create(Map.of("genre", genre));
        return chatClient.prompt(prompt).call().content();

    }



}
