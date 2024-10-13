package com.cryptomancer.clients;

import com.cryptomancer.configurations.OpenAIPropertiesConfig;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import java.util.List;

@Service
@Log4j2
public class OpenAiClient {

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private OpenAIPropertiesConfig openAIPropertiesConfig;

    public String callOpenAiWebservice(List<ChatMessage> messages) {
        log.info("Calling Open AI webservice.");

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .messages(messages)
                .model(openAIPropertiesConfig.getModel())
                .build();
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            ChatCompletionResult chatCompletionResult = openAiService.createChatCompletion(chatCompletionRequest);
            List<ChatCompletionChoice> chatCompletionChoices = chatCompletionResult.getChoices();

            if (chatCompletionChoices.size() <= 0) {
                log.error(String.format("Open AI did not respond with a completion choice for transaction id=%s",
                        chatCompletionResult.getId()));
                return null;
            }

            String responseFromOpenAi = chatCompletionChoices.get(0).getMessage().getContent();

            stopWatch.stop();
            log.info(String.format("Retrieved completion choice from Open AI: id=%s, tokenUsage=%s, executionTime=%s",
                    chatCompletionResult.getId(), chatCompletionResult.getUsage().getTotalTokens(),
                    stopWatch.getTotalTimeMillis()));

            return responseFromOpenAi;
        } catch (Exception e) {
            log.error("Failed to call Open AI webservice endpoint.", e);
            return null;
        }
    }
}