package com.firstchat.botai.config

import com.firstchat.botai.adapter.output.client.AgentClient
import com.firstchat.botai.adapter.output.client.SendMessageClient
import com.firstchat.botai.adapter.output.client.TokenClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ClientBeans {

    @Bean
    fun sendMessageClient(
        webClient: WebClient
    ): SendMessageClient {
        return SendMessageClient(webClient)
    }

    @Bean
    fun tokenClient(
        webClient: WebClient
    ): TokenClient {
        return TokenClient(webClient)
    }

    @Bean
    fun agentClient(
        webClient: WebClient,
        tokenClient: TokenClient,
    ): AgentClient {
        return AgentClient(webClient, tokenClient)
    }


}