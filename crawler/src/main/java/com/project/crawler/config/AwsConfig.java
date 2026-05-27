package com.project.crawler.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

@Configuration
public class AwsConfig {


    @Value("${aws.secrets.name.db}")
    private String dbSecretName;

    private final ObjectMapper objectMapper;

    public AwsConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public SecretsManagerClient secretsManagerClient() {
        return SecretsManagerClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }


    @Bean
    public String dbPassword(SecretsManagerClient client) {
        try {
            String secret = client.getSecretValue(
                    GetSecretValueRequest.builder()
                            .secretId(dbSecretName)
                            .build()
            ).secretString();

            JsonNode node = objectMapper.readTree(secret);
            return node.get("password").asText();
        } catch (Exception e) {
            throw new RuntimeException("DB 비밀번호 로드 실패", e);
        }
    }
}