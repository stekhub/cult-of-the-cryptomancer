package com.cryptomancer.services;

import com.cryptomancer.clients.OpenAiClient;
import com.cryptomancer.configurations.GamePropertiesConfig;
import com.cryptomancer.configurations.OpenAIPropertiesConfig;
import com.cryptomancer.dto.ConversationDto;
import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ConversationService {

    @Autowired
    private GamePropertiesConfig gamePropertiesConfig;

    @Autowired
    private OpenAIPropertiesConfig openAIPropertiesConfig;

    @Autowired
    private OpenAiClient openAiClient;

    public ConversationDto sendConversationToOpenAI(ConversationDto conversationDto) {
        log.info("Processing conversation request for request: " + conversationDto);

        List<ChatMessage> messages = createChatMessagesList(conversationDto);
        String responseFromOpenAI = openAiClient.callOpenAiWebservice(messages);
        conversationDto.setNewChatResponse(removePassphraseFromResponse(responseFromOpenAI));
        return conversationDto;
    }

    public boolean isUserInputContainingPassphrase(String userInput) {
        boolean isPassphraseContained = userInput.matches(gamePropertiesConfig.getPassphraseRegex());
        log.info(String.format("Checking if user has guessed the passphrase: result=%s, regex=%s, userInput=%s",
                isPassphraseContained, gamePropertiesConfig.getPassphraseRegex(), userInput));
        return isPassphraseContained;
    }

    private String removePassphraseFromResponse(String responseFromOpenAI) {
        /* Since ChatGPT is not guaranteed to keep any secrets, the passphrase may be contained in the response.
           To ensure the game progresses as expected, unintentional mentioning of the passphrase has to be removed. */
        if (responseFromOpenAI != null) {
            return responseFromOpenAI.replaceAll(gamePropertiesConfig.getPassphraseRemovalRegex(),
                    gamePropertiesConfig.getPassphraseRemovalSubstitute());
        } else {
            return null;
        }
    }

    private List<ChatMessage> createChatMessagesList(ConversationDto conversationDto) {
        /* OpenAI/ChatGPT is not holding a session for chat interaction. All chat messages the language model is
        supposed to remember have to be sent with every request. Message input is minimized to save costs. */
        List<ChatMessage> chatMessages = new ArrayList<>();

        chatMessages.add(new ChatMessage("system", openAIPropertiesConfig.getSystemMessage()));

        if (conversationDto.getPreviousUserInput() != null) {
            chatMessages.add(new ChatMessage("user", conversationDto.getPreviousUserInput()));
        }

        if (conversationDto.getPreviousChatResponse() != null) {
            chatMessages.add(new ChatMessage("assistant", conversationDto.getPreviousChatResponse()));
        }

        if (conversationDto.getNewUserInput() != null) {
            chatMessages.add(new ChatMessage("user", conversationDto.getNewUserInput()));
        } else {
            // Repeat the system message as user input to ensure the language model adheres to the rules
            chatMessages.add(new ChatMessage("user", openAIPropertiesConfig.getSystemMessage()));
            conversationDto.setNewUserInput(openAIPropertiesConfig.getSystemMessage());
        }

        return chatMessages;
    }
}
