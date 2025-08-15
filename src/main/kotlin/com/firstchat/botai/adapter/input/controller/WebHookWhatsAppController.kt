package com.firstchat.botai.adapter.input.controller

import com.firstchat.botai.adapter.input.MessageService
import com.firstchat.botai.adapter.input.whatsapp.dto.WhatsAppWebhookPayload
import com.firstchat.botai.adapter.input.whatsapp.dto.toMessage
import com.firstchat.botai.util.logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class WebHookWhatsAppController(
    private val messageService: MessageService
) {

    @Value("\${WHATSAPP_VERIFY_TOKEN}")
    private lateinit var verifyToken: String

    @GetMapping
    fun verifyWebhook(
        @RequestParam("hub.mode") mode: String?,
        @RequestParam("hub.challenge") challenge: String?,
        @RequestParam("hub.verify_token") token: String?
    ): ResponseEntity<String> {

        if (mode == "subscribe" && token == verifyToken) {
            logger.info("Webhook verificado com sucesso!")
            return ResponseEntity.ok(challenge)
        }

        logger.error("Falha na verificação do webhook. Token inválido ou modo incorreto.")
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
    }

    @PostMapping
    fun receiveWhatsAppEvents(
        @RequestBody input: WhatsAppWebhookPayload
    ): ResponseEntity<Void> {
        logger.info("Evento recebido: $input")
        val filterMessage = filterMessageToResponse(input)

        if (filterMessage != null) {
            val message = input.toMessage()
            messageService.responseMessage(message)
        }

        return ResponseEntity.status(HttpStatus.OK).build()
    }

    private fun filterMessageToResponse(whatsAppWebhookPayload: WhatsAppWebhookPayload): WhatsAppWebhookPayload? {
        val filteredEntries = whatsAppWebhookPayload.entry.mapNotNull { entry ->
            val filteredChanges = entry.changes?.filter { change ->
                change.field == "messages" && change.value.messages != null && change.value.contacts != null
            }

            when {
                !filteredChanges.isNullOrEmpty() -> entry.copy(changes = filteredChanges)
                else -> null
            }
        }

        return when {
            filteredEntries.isNotEmpty() -> whatsAppWebhookPayload.copy(entry = filteredEntries)
            else -> null
        }
    }
}