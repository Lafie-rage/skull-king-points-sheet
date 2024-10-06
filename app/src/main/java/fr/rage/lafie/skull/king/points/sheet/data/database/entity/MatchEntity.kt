package fr.rage.lafie.skull.king.points.sheet.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "match",
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["id"],
            childColumns = ["gameId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["gameId"]),
    ],
)
data class MatchEntity(
    @PrimaryKey val id: UUID,
    val gameId: UUID,
    val name: String,
    val roundCounter: Int,
    val isFinished: Boolean,
)
