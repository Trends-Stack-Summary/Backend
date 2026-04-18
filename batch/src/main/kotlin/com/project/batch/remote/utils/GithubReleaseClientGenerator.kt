package com.project.batch.remote.utils

import com.project.batch.remote.constants.GithubReleaseUri.BASE_URL
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest
import tools.jackson.databind.ObjectMapper

@Component
class GithubReleaseClientGenerator(
    @Value("\${aws.secrets.name.github-release-token}") private val secretName: String,
    private val secretsManagerClient: SecretsManagerClient,
    private val objectMapper: ObjectMapper,
) {

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val ACCEPT_HEADER = "Accept"
        private const val X_GITHUB_API_VERSION_HEADER = "X-GITHUB-API-VERSION"
    }

    private val token: String by lazy {
        val secretString = secretsManagerClient.getSecretValue(
            GetSecretValueRequest.builder().secretId(secretName).build()
        ).secretString()
        objectMapper.readTree(secretString).get(secretName).asString()
    }

    fun generate(): WebClient = WebClient.builder()
        .baseUrl(BASE_URL)
        .clientConnector(ReactorClientHttpConnector(HttpClient.create().responseTimeout(java.time.Duration.ofSeconds(30))))
        .codecs { it.defaultCodecs().maxInMemorySize(10 * 1024 * 1024) }
        .defaultHeader(AUTHORIZATION_HEADER, "Bearer $token")
        .defaultHeader(ACCEPT_HEADER, "application/vnd.github+json")
        .defaultHeader(X_GITHUB_API_VERSION_HEADER, "2022-11-28")
        .build()
}