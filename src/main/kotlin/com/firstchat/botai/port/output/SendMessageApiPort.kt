package com.firstchat.botai.port.output

import com.firstchat.botai.domain.Message

interface SendMessageApiPort {

    fun send(message: Message, responseMessage: String)
}