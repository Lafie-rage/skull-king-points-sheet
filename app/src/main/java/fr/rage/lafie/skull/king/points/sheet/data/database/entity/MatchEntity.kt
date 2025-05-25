package fr.rage.lafie.skull.king.points.sheet.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "match",
)
data class MatchEntity(
    @PrimaryKey val id: UUID,
    val name: String,
    val roundCounter: Int,
    val isFinished: Boolean,
)
