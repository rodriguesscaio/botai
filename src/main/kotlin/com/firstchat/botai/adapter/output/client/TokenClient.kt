package com.firstchat.botai.adapter.output.client

import com.firstchat.botai.adapter.output.dto.AccessTokenResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

class TokenClient(
    private val webClient: WebClient,

) {

    @Value("\${stackspot.idm.base-url}")
    lateinit var baseUrl: String
    @Value("\${stackspot.idm.realm}")
    lateinit var realm: String
    @Value("\${stackspot.client-id}")
    lateinit var clientId: String
    @Value("\${stackspot.client-key}")
    lateinit var clientKey: String

    @Value("\${WHATSAPP_ACCESS_TOKEN}")
    lateinit var accessToken: String

    fun getAccessToken(): Mono<String> {
        val formData: MultiValueMap<String, String> = LinkedMultiValueMap()
        formData.add("grant_type", "client_credentials")
        formData.add("client_id", clientId)
        formData.add("client_secret", clientKey)

        return webClient.post()
            .uri("$baseUrl/$realm/oidc/oauth/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(AccessTokenResponse::class.java)
            .map { it.accessToken }
    }
}
