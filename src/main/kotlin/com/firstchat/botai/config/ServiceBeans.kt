package com.firstchat.botai.config

import com.firstchat.botai.adapter.input.MessageService
import com.firstchat.botai.adapter.output.SendMessageAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceBeans {

    @Bean
    fun messageService(
        sendMessageAdapter: SendMessageAdapter
    ): MessageService {
        return MessageService(sendMessageAdapter)
    }
}