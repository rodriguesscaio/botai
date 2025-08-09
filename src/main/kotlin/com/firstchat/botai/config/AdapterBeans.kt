package com.firstchat.botai.config

import com.firstchat.botai.adapter.output.SendMessageAdapter
import com.firstchat.botai.adapter.output.client.SendMessageClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AdapterBeans {

    @Bean
    fun sendMessageAdapter(
        messageClient: SendMessageClient
    ): SendMessageAdapter {
        return SendMessageAdapter(messageClient)
    }
}