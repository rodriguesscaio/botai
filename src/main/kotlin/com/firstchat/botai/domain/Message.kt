package com.firstchat.botai.domain

import com.firstchat.botai.adapter.output.dto.SendMessageRequest
import com.firstchat.botai.adapter.output.dto.TextRequest

data class Message(
    val messageProduct: String,
    val phoneNumberId: String,
    val name: String,
    val phoneNumber: String,
    val textMessage: String,
    val typeMessage: String,
    val timestamp: String
)

fun Message.toSendMessageRequest(): SendMessageRequest {
    return SendMessageRequest(
        messagingProduct = this.messageProduct,
        to = this.phoneNumber,
        type = this.typeMessage,
        text = TextRequest(
            body = "respondendo de dentro do seu sistema!!"
        )
    )
}