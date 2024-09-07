package fr.rage.lafie.table.games.points.sheet.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = PlayerEntity::class,
            parentColumns = ["id"],
            childColumns = ["playerId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
)
data class PlayerHasPointDuringRoundEntity(
    @PrimaryKey val id: UUID,
    val playerId: UUID,
    val roundId: UUID,
    val points: Long,
)
