package br.com.openai.prompt.config;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RequestSplitter {

    private static final int MAX_CHARACTERS = 2000;

    public List<String> split(String text) {
        List<String> pieces = new ArrayList<>();

        if (text != null && !text.isEmpty() && MAX_CHARACTERS > 0) {
            int length = text.length();

            if (length <= MAX_CHARACTERS) {
                return List.of(text);
            }

            int startIndex = 0;
            int endIndex = MAX_CHARACTERS;

            while (startIndex < length) {
                String piece = text.substring(startIndex, endIndex);
                pieces.add(piece);
                startIndex = endIndex;
                endIndex = Math.min(startIndex + MAX_CHARACTERS, length);
            }
        }

        return pieces;
    }
}
