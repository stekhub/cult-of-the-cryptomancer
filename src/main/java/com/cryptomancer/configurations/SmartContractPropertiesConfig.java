package com.cryptomancer.configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "blockchain.contract")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmartContractPropertiesConfig {

    private String address;

    private String walletFilePassword;

    private String gateway;

    private Integer chainId;

}
