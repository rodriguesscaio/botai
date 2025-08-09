package com.firstchat.botai.adapter.output.dto

import com.fasterxml.jackson.annotation.JsonAlias

data class SendMessageRequest(
    @JsonAlias(value = ["messaging_product"])
    val messagingProduct: String = "whatsapp",
    val to: String,
    val type: String,
    val text: TextRequest
)