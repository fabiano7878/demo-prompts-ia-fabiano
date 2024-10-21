package br.com.openai.prompt.service;

import dev.langchain4j.agent.tool.Tool;
import io.quarkus.logging.Log;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class EmailService {


    @Inject
    Mailer mailer;

    @Tool("send the given content by email")
    public String sendAnEmail(String content) {
        Log.info("Sending an email: "+ content);
        mailer.send(Mail.withText("sendMeALetter@quarkus.io", "A poem for you", content));
        return URLDecoder.decode(content, StandardCharsets.UTF_8);

    }

}
