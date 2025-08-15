package com.firstchat.botai.adapter.output

import com.firstchat.botai.adapter.output.client.SendMessageClient
import com.firstchat.botai.domain.Message
import com.firstchat.botai.domain.toSendMessageRequest
import com.firstchat.botai.port.output.SendMessageApiPort
import com.firstchat.botai.util.logger

class SendMessageAdapter(
    private val sendMessageClient: SendMessageClient
): SendMessageApiPort {
    override fun send(message: Message, responseMessage: String) {
        val sendMessageRequest = message.toSendMessageRequest(responseMessage)

        try {
            sendMessageClient.post(message.phoneNumberId, sendMessageRequest)
        } catch (e: Exception) {
            logger.error("Erro ao chamar a API do WhatsApp: ${e.message}")
            logger.error("Erro: ${e}")
        }
    }
}