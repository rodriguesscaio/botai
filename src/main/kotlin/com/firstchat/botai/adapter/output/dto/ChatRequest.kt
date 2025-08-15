package com.firstchat.botai.adapter.output.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatRequest(
    val streaming: Boolean,
    @JsonProperty("user_prompt")
    val userPrompt: String,
    @JsonProperty("stackspot_knowledge")
    val stackspotKnowledge: Boolean,
    @JsonProperty("return_ks_in_response")
    val returnKsInResponse: Boolean
)