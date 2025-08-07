package com.firstchat.botai.adapter.input.controller

import com.firstchat.botai.adapter.input.whatsapp.dto.WhatsAppWebhookPayload
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/")
class WebHookWhatsAppController {

    @Value("\${WHATSAPP_VERIFY_TOKEN}")
    private lateinit var verifyToken: String

    @GetMapping("/webhook")
    fun verifyWebhook(
        @RequestParam("hub.mode") mode: String?,
        @RequestParam("hub.challenge") challenge: String,
        @RequestParam("hub.verify_token") token: String?
    ): ResponseEntity<String> {

        if (mode == "subscribe" && token == verifyToken) {
            println("Webhook verificado com sucesso!")
            return ResponseEntity.ok(challenge)
        } else {
            println("Falha na verificação do webhook. Token inválido ou modo incorreto.")
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }

    @PostMapping
    fun inputMessage(
        @RequestBody input: WhatsAppWebhookPayload
    ): ResponseEntity<WhatsAppWebhookPayload> {
        println("EVENTO RECEBIDO")
        println(LocalDateTime.now())
        println(input)

        return ResponseEntity.ok().build()
    }
}