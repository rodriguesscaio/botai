package com.firstchat.botai.adapter.input

import com.firstchat.botai.adapter.output.SendMessageAdapter
import com.firstchat.botai.domain.Message
import org.springframework.scheduling.annotation.Async

open class MessageService(
    private val sendMessageAdapter: SendMessageAdapter
) {

    @Async
    open fun responseMessage(message: Message) {
        sendMessageAdapter.send(message)
    }
}