package com.cryptomancer.configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

@Configuration
@ConfigurationProperties(prefix = "game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GamePropertiesConfig {

    private Integer sessionTimeout;

    private BigInteger ascensionReward;

    private String passphraseRegex;

    private String passphraseRemovalRegex;

    private String passphraseRemovalSubstitute;

}
