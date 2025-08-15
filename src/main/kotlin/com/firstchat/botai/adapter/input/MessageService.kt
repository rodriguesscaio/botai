package com.firstchat.botai.adapter.input

import com.firstchat.botai.adapter.output.SendMessageAdapter
import com.firstchat.botai.adapter.output.client.AgentClient
import com.firstchat.botai.domain.Message
import com.firstchat.botai.util.logger
import org.springframework.scheduling.annotation.Async

open class MessageService(
    private val agentClient: AgentClient,
    private val sendMessageAdapter: SendMessageAdapter
) {

    @Async
    open fun responseMessage(message: Message) {
        val userPrompt = message.textMessage

        val responseMessage = agentClient.sendChatRequest(userPrompt).block()

        if (responseMessage != null) {
            sendMessageAdapter.send(message, responseMessage)
        } else {
            logger.warn("A API da StackSpot não retornou uma resposta para o prompt: $userPrompt")
        }
    }
}