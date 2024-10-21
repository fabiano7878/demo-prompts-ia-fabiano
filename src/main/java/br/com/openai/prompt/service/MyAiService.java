package br.com.openai.prompt.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(
        tools = EmailService.class
)
public interface MyAiService {

    @SystemMessage("You are a professional poet")
    @UserMessage("""
                Write a poem about {topic}. The poem should be {lines} lines long. Then send this poem by email. O Poema deverá sempre estar escrito em português pt-BR. 
            """)
    String writeAPoem(String topic, int lines);
}
