package com.cryptomancer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConversationDto {

    @Size(max = 50)
    @NotNull
    private String address;

    @Size(max = 20)
    @NotNull
    private String signedMessage;

    @Size(max = 300)
    @NotNull
    private String signature;

    @Size(max = 5000)
    private String previousChatResponse;

    @Size(max = 5000)
    private String newChatResponse;

    @Size(max = 1000)
    private String previousUserInput;

    @Size(max = 1000)
    private String newUserInput;
}
