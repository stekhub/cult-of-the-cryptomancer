async function createGameSession() {
    // Guide user through the installation process, if MetaMask is not installed
    if (typeof window.ethereum !== 'undefined') {
        console.log('Metamask is installed');
        typeTextSlowly(messages["METAMASK_INSTALLED"]);
    } else {
        console.log('Metamask is not installed');
        typeTextSlowly(messages["METAMASK_NOT_INSTALLED"]);
        return;
    }

    // Generating a timestamp for creating a session
    conversationDto.signedMessage = Math.floor(Date.now()).toString();

    try {
        var accounts = await ethereum.request({ method: 'eth_requestAccounts' });
        conversationDto.address = accounts[0];

        // Signing an integer value with the API provided by MetaMask returns unexpected results. Using web3js instead.
        conversationDto.signature = await web3.eth.personal.sign(conversationDto.signedMessage, conversationDto.address)

        // Start conversation with empty user input to receive introduction from the OpenAI language model
        postConversation();

        enableInputForm();
        disableSubmitButton();

        $("#textArea").val("");
    } catch (err) {
        console.log('Error while signing message: ');
        typeTextSlowly(messages["SIGNING_FAILED"]);
    }
}