package com.firstchat.botai.adapter.output.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SendMessageRequest(
    @JsonProperty("messaging_product")
    val messagingProduct: String = "whatsapp",
    val to: String,
    val type: String,
    val text: TextRequest
)