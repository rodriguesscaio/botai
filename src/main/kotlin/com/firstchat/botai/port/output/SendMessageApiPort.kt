package com.firstchat.botai.port.output

import com.firstchat.botai.adapter.output.dto.SendMessageRequest
import com.firstchat.botai.domain.Message

interface SendMessageApiPort {

    fun send(message: Message)
}