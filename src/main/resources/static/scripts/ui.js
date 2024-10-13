$(function() {
    $("#submitButton").click(submitConversation);
    $("#userInput").keypress(function(e) {
        if(e.which == 13) {
            submitConversation();
        }
    });
});

function submitConversation() {
    if ($("#userInput").val() === "") return;

    conversationDto.newUserInput = $("#userInput").val();

    $("#userInput").val("");
    $("#textArea").val("");

    disableSubmitButton();
    postConversation();
}

async function typeTextSlowly(text) {
    $("#textArea").val("");
    for (let i = 0; i < text.length; i++) {
        $("#textArea").val($("#textArea").val() + text.charAt(i));

        // Avatar animation should be slower than text typing speed
        if (i % 15 == 0) animateAvatar();

        await new Promise(resolve => setTimeout(resolve, 5));
    }
    $("#avatar").attr("src", animationFrames[0]);
}

function enableInputForm() {
    $(".input-container").fadeIn().css('display', 'flex');
}

function disableInputForm() {
    $(".input-container").fadeOut();
}

function disableSubmitButton() {
    $("#submitButton").prop("disabled", true);
    $.LoadingOverlay("show", {
        background:"rgba(255, 255, 255, 0)",
        imageColor:"#f0f0f0"});
}

function enableSubmitButton() {
    $("#submitButton").prop("disabled", false);
    $.LoadingOverlay("hide");
}

function animateAvatar() {
    var randomIndex = Math.floor(Math.random() * animationFrames.length);
    $("#avatar").attr("src", animationFrames[randomIndex]);
}