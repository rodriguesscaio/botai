package com.firstchat.botai.adapter.input.whatsapp.dto

import com.fasterxml.jackson.annotation.JsonAlias

data class WhatsAppWebhookPayload(
    val `object`: String,
    val entry: List<WhatsAppEntryDto>
)

data class WhatsAppEntryDto(
    val id: String?,
    val changes: List<WhatsAppChangeDto>?
)

data class WhatsAppChangeDto(
    val value: ChangeValueDto,
    val field: String // Para verificar se é "messages"
)

data class ChangeValueDto(
    @JsonAlias(value = ["messaging_product"])
    val messagingProduct: String?,
    val metadata: MetadataDto?,
    val contacts: List<ContactDto>?,
    val messages: List<MessageDto>?, // Lista de mensagens recebidas
    val statuses: List<StatusDto>?   // Lista de status de mensagens (se for um evento de status)
)

data class MetadataDto(
    @JsonAlias(value = ["display_phone_number"])
    val displayPhoneNumber: String?,

    @JsonAlias(value = ["phone_number_id"])
    val phoneNumberId: String?,

)

data class ContactDto(
    val profile: ProfileDto?,

    @JsonAlias(value = ["wa_id"])
    val waId: String?
)

data class ProfileDto(
    val name: String?
)

data class MessageDto(
    val from: String?,
    val id: String?,
    val timestamp: String?,
    val type: String?,
    val text: TextDto?
)

data class TextDto(
    val body: String?
)

data class StatusDto(
    val id: String?, // ID da mensagem enviada que teve o status atualizado
    val status: String?, // "sent", "delivered", "read", "failed"
    val timestamp: String?,

    @JsonAlias(value = ["recipient_id"])
    val recipientId: String?,
    val conversation: ConversationDto?,
    val pricing: PricingDto?
)

data class ConversationDto(
    val id: String?,
    val origin: OriginDto?
)

data class OriginDto(
    val type: String?
)

data class PricingDto(
    val billable: Boolean?,

    @JsonAlias(value = ["pricing_model"])
    val pricingModel: String?,
    val category: String?,
    val type: String?
)