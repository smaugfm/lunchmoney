package com.github.smaugfm.lunchmoney.serializer

import com.github.smaugfm.lunchmoney.model.TransactionStatus
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TransactionStatusSerializer : KSerializer<TransactionStatus> {
    override val descriptor =
        PrimitiveSerialDescriptor("TransactionStatus", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): TransactionStatus {
        return TransactionStatus.valueOf(decoder.decodeString().uppercase())
    }

    override fun serialize(encoder: Encoder, value: TransactionStatus) {
        encoder.encodeString(value.toString().lowercase())
    }
}
