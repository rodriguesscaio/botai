package com.firstchat.botai.adapter.input

import com.firstchat.botai.adapter.output.SendMessageAdapter
import com.firstchat.botai.domain.Message

class MessageService(
    private val sendMessageAdapter: SendMessageAdapter
) {

    fun responseMessage(message: Message) {
        sendMessageAdapter.send(message)
    }
}