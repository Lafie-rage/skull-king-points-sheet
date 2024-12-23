package fr.rage.lafie.skull.king.points.sheet.data.database.converter

import androidx.room.TypeConverter
import java.util.UUID

class Converters {

    @TypeConverter
    fun toUUID(value: String?): UUID? {
        return value?.let { uuid ->
            UUID.fromString(uuid)
        }
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
}