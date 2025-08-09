package com.firstchat.botai.config

import com.firstchat.botai.adapter.output.client.SendMessageClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
public class ClientBeans {

    @Bean
    fun sendMessageClient(
        webClient: WebClient
    ): SendMessageClient {
        return SendMessageClient(webClient)
    }
}