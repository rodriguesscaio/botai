package com.firstchat.botai.adapter.output

import com.firstchat.botai.adapter.output.client.SendMessageClient
import com.firstchat.botai.domain.Message
import com.firstchat.botai.domain.toSendMessageRequest
import com.firstchat.botai.port.output.SendMessageApiPort

class SendMessageAdapter(
    private val sendMessageClient: SendMessageClient
): SendMessageApiPort {
    override fun send(message: Message) {
        val sendMessageRequest = message.toSendMessageRequest()

        println("Body to send: $sendMessageRequest")

        try {
            sendMessageClient.post(message.phoneNumberId, sendMessageRequest)
        } catch (e: Exception) {
            println("Erro ao chamar a API do WhatsApp: ${e.message}")
            println("Erro: ${e}")
        }
    }
}