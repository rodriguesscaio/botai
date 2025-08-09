package com.firstchat.botai.adapter.output.client

import com.firstchat.botai.adapter.output.dto.SendMessageRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.reactive.function.client.WebClient

class SendMessageClient(
    private val webClient: WebClient
) {

    @Value("\${WHATSAPP_ACCESS_TOKEN}")
    lateinit var accessToken: String

    fun post(phoneNumberId: String, payload: SendMessageRequest) {
        webClient.post()
            .uri("https://graph.facebook.com/v19.0/$phoneNumberId/messages")
            .header("Authorization", "Bearer $accessToken")
            .bodyValue(payload)
            .retrieve()
            .toBodilessEntity()
            .block() // em um contexto reativo, use .subscribe()
    }
}

