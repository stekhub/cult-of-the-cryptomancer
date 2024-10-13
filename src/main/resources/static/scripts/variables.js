var conversationDto = {
    address : null,
    signedMessage : null,
    signature : null,
    previousUserInput : null,
    previousChatResponse : null,
    newUserInput : null,
    newChatResponse : null
};

var messages;
$.ajax({
    url: "messages/messages.json",
    dataType: "json",
    async: false,
    success: function(data) {
        console.log("Messages loaded successfully.");
        messages = data;
    },
    error: function(xhr, status, error) {
        console.error("Error while loading messages: " + error);
    }
});

var web3 = new Web3(Web3.givenProvider || "ws://localhost:8545");

var animationFrames = ["images/avatar.png", "images/avatar_animation_frame_1.png", "images/avatar_animation_frame_2.png"];