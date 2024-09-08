package fr.rage.lafie.table.games.points.sheet.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "game",
)
data class GameEntity(
    @PrimaryKey val id: UUID,
    val name: String,
)
