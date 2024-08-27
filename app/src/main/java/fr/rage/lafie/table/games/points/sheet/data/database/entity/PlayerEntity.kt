package fr.rage.lafie.table.games.points.sheet.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "player")
data class PlayerEntity(
    @PrimaryKey val id: UUID,
    val name: String,
    val matchId: UUID,
)