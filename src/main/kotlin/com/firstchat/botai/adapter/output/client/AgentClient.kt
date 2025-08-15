package com.firstchat.botai.adapter.output.client

import com.firstchat.botai.adapter.output.dto.ChatRequest
import com.firstchat.botai.adapter.output.dto.ChatResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

class AgentClient(
    private val webClient: WebClient,
    private val tokenClient: TokenClient,

) {

    @Value("\${stackspot.agent.base-url}")
    lateinit var baseUrl: String
    @Value("\${stackspot.agent.id}")
    lateinit var agentId: String

    fun sendChatRequest(prompt: String): Mono<String> {
        val requestBody = ChatRequest(
            streaming = false,
            userPrompt = prompt,
            stackspotKnowledge = false,
            returnKsInResponse = true
        )

        return tokenClient.getAccessToken().flatMap { jwtToken ->
            webClient.post()
                .uri("$baseUrl/v1/agent/$agentId/chat")
                .header("Authorization", "Bearer $jwtToken")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(ChatResponse::class.java)
                .map { it.message }
        }
    }
}