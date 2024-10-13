package com.cryptomancer.services;

import com.cryptomancer.configurations.GamePropertiesConfig;
import com.cryptomancer.web3j.CultOfTheCryptomancerContract;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Service
@Log4j2
public class CultistAscensionService {

    @Autowired
    private GamePropertiesConfig gamePropertiesConfig;

    @Autowired
    private CultOfTheCryptomancerContract cultOfTheCryptomancerContract;

    public void ascendAdaptToCultist(String adaptAddress) {
        log.info("Ascending adept to cultist: " + adaptAddress);
        try {
            TransactionReceipt result = cultOfTheCryptomancerContract.addAndFundCultist(adaptAddress,
                    gamePropertiesConfig.getAscensionReward()).send();
            log.info("Created addAndFundCultist transaction on the blockchain: " + result);
        } catch (Exception e) {
            log.error("Failed to create addAndFundCultist transaction on the blockchain.", e);
        }
    }
}