package com.cryptomancer.services;

import com.cryptomancer.configurations.GamePropertiesConfig;
import com.cryptomancer.web3j.CultOfTheCryptomancerContract;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.utils.Numeric;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

@Service
@Log4j2
public class AuthorizationService {

    @Autowired
    private GamePropertiesConfig gamePropertiesConfig;

    @Autowired
    private CultOfTheCryptomancerContract cultOfTheCryptomancerContract;

    private static final String SIGNATURE_MESSAGE_PREFIX = "\u0019Ethereum Signed Message:\n";

    public boolean isTimeStampValid(String signedMessage) {
        log.info(String.format("Checking if timestamp=%s is not exceeding timeout=%s", signedMessage,
                gamePropertiesConfig.getSessionTimeout()));

        Date signedTimeStamp = new Date(Long.parseLong(signedMessage));
        long currentTimeInMillis = System.currentTimeMillis();
        long sessionTimeoutInMillis = gamePropertiesConfig.getSessionTimeout() * 60 * 1000;
        return signedTimeStamp.getTime() > (currentTimeInMillis - sessionTimeoutInMillis);
    }

    public boolean isAddressFromAdept(String addressOfAdept) {
        log.info(String.format("Checking if address=%s is belonging to an adept.", addressOfAdept));

        try {
            BigInteger timestamp = cultOfTheCryptomancerContract.getCreationTimestampOfAdept(addressOfAdept).send();
            return timestamp.compareTo(BigInteger.ZERO) > 0;
        } catch (Exception e) {
            log.error(String.format("Failed to check if address=%s is belonging to an adept.", addressOfAdept), e);
            return false;
        }
    }

    public boolean isAddressFromCultist(String addressOfAdept) {
        log.info(String.format("Checking if address=%s is belonging to a cultist.", addressOfAdept));

        try {
            BigInteger timestamp = cultOfTheCryptomancerContract.getCreationTimestampOfCultist(addressOfAdept).send();
            return timestamp.compareTo(BigInteger.ZERO) > 0;
        } catch (Exception e) {
            log.error(String.format("Failed to check if address=%s is belonging to a cultist.", addressOfAdept), e);
            return false;
        }
    }

    public boolean isSignatureValid(String message, String signature, String expectedAddress) {
        log.info(String.format("Checking if signature is valid for message=%s, signature=%s and expectedAddress=%s",
                message, signature, expectedAddress));

        String extractedAddress = recoverAddressFromSignature(message, signature);
        boolean validationResult = extractedAddress.equalsIgnoreCase(expectedAddress);

        log.info(String.format("Validation result=%s for message=%s, signature=%s and expectedAddress=%s",
                validationResult, message, signature, expectedAddress));
        return validationResult;
    }

    private String recoverAddressFromSignature(String message, String signature) {
        String hashedMessage = Hash.sha3(Numeric
                .toHexStringNoPrefix((SIGNATURE_MESSAGE_PREFIX + message.length() + message)
                        .getBytes(StandardCharsets.UTF_8)));

        SignatureData signatureData = generateSignatureData(signature);
        int header = 0;

        for (byte b : signatureData.getV()) {
            header = (header << 8) + (b & 0xFF);
        }

        if (header < 27 || header > 34) {
            return null;
        }

        int recId = header - 27;
        BigInteger key = Sign.recoverFromSignature(
                recId,
                new ECDSASignature(
                        new BigInteger(1, signatureData.getR()),
                        new BigInteger(1, signatureData.getS())),
                Numeric.hexStringToByteArray(hashedMessage));

        if (key == null) {
            return null;
        }

        return ("0x" + Keys.getAddress(key)).trim();
    }

    private SignatureData generateSignatureData(String signature) {
        byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
        byte v = signatureBytes[64];

        if (v < 27) {
            v += 27;
        }

        byte[] r = Arrays.copyOfRange(signatureBytes, 0, 32);
        byte[] s = Arrays.copyOfRange(signatureBytes, 32, 64);

        return new SignatureData(v, r, s);
    }
}
