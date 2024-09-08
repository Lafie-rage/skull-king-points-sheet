package fr.rage.lafie.table.games.points.sheet.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "player",
    foreignKeys = [
        ForeignKey(
            entity = MatchEntity::class,
            parentColumns = ["id"],
            childColumns = ["matchId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["matchId"]),
    ]
)
data class PlayerEntity(
    @PrimaryKey val id: UUID,
    val matchId: UUID,
    val name: String,
)