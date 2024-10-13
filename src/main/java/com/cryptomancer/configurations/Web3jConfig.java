package com.cryptomancer.configurations;

import com.cryptomancer.web3j.CultOfTheCryptomancerContract;
import com.cryptomancer.web3j.CustomGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.response.NoOpProcessor;
import java.io.IOException;

@Configuration
public class Web3jConfig {

    @Autowired
    private SmartContractPropertiesConfig smartContractPropertiesConfig;

    @Bean
    public CultOfTheCryptomancerContract cultOfTheCryptomancerContract() throws IOException, CipherException {
        Web3j web3j = Web3j.build(new HttpService(smartContractPropertiesConfig.getGateway()));

        String walletFileAsJson = new String (getClass().getClassLoader()
                .getResourceAsStream("wallet.json").readAllBytes());

        Credentials credentials = WalletUtils.loadJsonCredentials(
                smartContractPropertiesConfig.getWalletFilePassword(), walletFileAsJson);

        TransactionManager transactionManager = new RawTransactionManager(
                web3j, credentials, smartContractPropertiesConfig.getChainId(), new NoOpProcessor(web3j));

        CustomGasProvider customGasProvider = new CustomGasProvider(web3j);

        return CultOfTheCryptomancerContract.load(smartContractPropertiesConfig.getAddress(), web3j,
                transactionManager, customGasProvider);
    }
}