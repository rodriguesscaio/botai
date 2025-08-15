package com.firstchat.botai.adapter.output.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatResponse(
    @JsonProperty("message")
    val message: String
)