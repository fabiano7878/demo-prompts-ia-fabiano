package br.com.openai.prompt.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(tools = EmailService.class)
public interface PoemAiService {

    //@SystemMessage("You are a professional poet")
    //@UserMessage("Write a poem about {topic}. The poem should be {lines} lines long. Then send this poem by email., always write in Portuguese pt-br")
    //String writeAPoem(String topic, int lines);

}
