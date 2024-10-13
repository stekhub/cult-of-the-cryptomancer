function postConversation() {
    $.ajax({
        url: '/api/v1/conversation',
        type: 'POST',
        data: JSON.stringify(conversationDto),
        contentType: 'application/json',
        success: function(response, status, xhr) {
            console.log('Request successful: ', response);

            enableSubmitButton();

            // Ascension to cultist was accepted, outro message is appended to OpenAI response
            if(xhr.status == 202) {
                response.newChatResponse += messages["OUTRO_MESSAGE"];
                disableInputForm();
            }

            typeTextSlowly(response.newChatResponse);

            conversationDto.previousChatResponse = response.newChatResponse;
            conversationDto.previousUserInput = response.newUserInput;
            conversationDto.newUserInput = null;
            conversationDto.newChatResponse = null;
        },
        error: function(xhr, status, error) {
            console.error('Error while calling REST endpoint: ', error);

            if (messages[xhr.responseJSON] !== undefined) {
                typeTextSlowly(messages[xhr.responseJSON]);
            } else {
                typeTextSlowly(messages["UNEXPECTED_ERROR"]);
            }

            enableSubmitButton();
            disableInputForm();
        }
    });
}