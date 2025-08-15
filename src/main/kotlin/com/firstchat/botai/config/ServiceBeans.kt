package com.firstchat.botai.config

import com.firstchat.botai.adapter.input.MessageService
import com.firstchat.botai.adapter.output.SendMessageAdapter
import com.firstchat.botai.adapter.output.client.AgentClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceBeans {

    @Bean
    fun messageService(
        agentClient: AgentClient,
        sendMessageAdapter: SendMessageAdapter
    ): MessageService {
        return MessageService(agentClient, sendMessageAdapter)
    }
}