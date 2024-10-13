package com.cryptomancer.web3j;

import lombok.extern.log4j.Log4j2;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

@Log4j2
public class CustomGasProvider implements ContractGasProvider {

    private static final BigInteger ADD_AND_FUND_CULTIST_GAS_CONSUMPTION = BigInteger.valueOf(125_000);

    private static final BigInteger DEFAULT_GAS_PRICE = BigInteger.valueOf(450_000_000_000L);

    private final Web3j web3j;

    public CustomGasProvider(Web3j web3j) {
        this.web3j = web3j;
    }

    @Override
    public BigInteger getGasPrice(String s) {
        return getGasPriceFromGateway();
    }

    @Override
    public BigInteger getGasPrice() {
        return getGasPriceFromGateway();
    }

    private BigInteger getGasPriceFromGateway() {
        try {
            EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
            log.info("Retrieved gas price from gateway: " + ethGasPrice.getGasPrice());
            return ethGasPrice.getGasPrice();
        } catch (Exception e) {
            log.error("Failed to retrieve gas price. Defaulting to static gas price: "
                    + DEFAULT_GAS_PRICE, e);
            return DEFAULT_GAS_PRICE;
        }
    }

    @Override
    public BigInteger getGasLimit(String s) {
        return ADD_AND_FUND_CULTIST_GAS_CONSUMPTION;
    }

    @Override
    public BigInteger getGasLimit() {
        return ADD_AND_FUND_CULTIST_GAS_CONSUMPTION;
    }
}
