package com.cryptomancer.controllers;

import com.cryptomancer.dto.ConversationDto;
import com.cryptomancer.enums.ErrorCodeEnum;
import com.cryptomancer.services.AuthorizationService;
import com.cryptomancer.services.ConversationService;
import com.cryptomancer.services.CultistAscensionService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/conversation")
@Log4j2
public class ConversationRestController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private CultistAscensionService cultistAscensionService;

    @PostMapping
    public ResponseEntity converse(@Valid @RequestBody ConversationDto conversationDto) {
        log.info("Received conversation request:" + conversationDto.toString());

        try {
            if (!authorizationService.isTimeStampValid(conversationDto.getSignedMessage())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorCodeEnum.SESSION_TIMEOUT);
            }

            if (!authorizationService.isSignatureValid(conversationDto.getSignedMessage(),
                    conversationDto.getSignature(), conversationDto.getAddress())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorCodeEnum.SIGNATURE_INVALID);
            }

            if (!authorizationService.isAddressFromAdept(conversationDto.getAddress())) {
                // Cultists are not authorized to play the game a second time
                if (authorizationService.isAddressFromCultist(conversationDto.getAddress())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorCodeEnum.CULTIST_FOUND);
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorCodeEnum.ADEPT_NOT_FOUND);
                }
            }

            // If all authorization checks passed, forward chat messages to OpenAI
            conversationService.sendConversationToOpenAI(conversationDto);

            // If user input contains the passphrase, ascend him to cultist
            if (conversationService.isUserInputContainingPassphrase(conversationDto.getNewUserInput())) {
                cultistAscensionService.ascendAdaptToCultist(conversationDto.getAddress());

                return ResponseEntity.status(HttpStatus.ACCEPTED).body(conversationDto);
            }
        } catch (Exception e) {
            log.error("General error occurred during processing of conversation: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorCodeEnum.UNEXPECTED_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(conversationDto);
    }
}
