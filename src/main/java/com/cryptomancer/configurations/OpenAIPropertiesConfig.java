package com.cryptomancer.configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "open.ai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenAIPropertiesConfig {

    private String apiKey;

    private String model;

    private Integer timeout;

    private String systemMessage;

}
