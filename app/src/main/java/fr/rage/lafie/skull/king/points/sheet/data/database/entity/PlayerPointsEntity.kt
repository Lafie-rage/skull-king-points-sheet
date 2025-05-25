package fr.rage.lafie.skull.king.points.sheet.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "player_points",
    foreignKeys = [
        ForeignKey(
            entity = PlayerEntity::class,
            parentColumns = ["id"],
            childColumns = ["playerId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["playerId"]),
    ]
)
data class PlayerPointsEntity(
    @PrimaryKey val id: UUID,
    val playerId: UUID,
    val roundIndex: Int,
    val points: Long,
)
