package fr.rage.lafie.table.games.points.sheet.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID

class UUIDSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        val uuid = decoder.decodeString()
        return UUID.fromString(uuid)
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        val uuid = value.toString()
        encoder.encodeString(uuid)
    }
}