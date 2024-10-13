package com.cryptomancer.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import java.time.Duration;

import static com.theokanning.openai.service.OpenAiService.*;

@Configuration
public class OpenAIServiceConfig {

    @Autowired
    private OpenAIPropertiesConfig openAIPropertiesConfig;

    @Bean
    public OpenAiService openAiService() {
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(openAIPropertiesConfig.getApiKey(),
                Duration.ofMillis(openAIPropertiesConfig.getTimeout())).newBuilder().build();
        Retrofit retrofit = defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        return new OpenAiService(api);
    }
}

