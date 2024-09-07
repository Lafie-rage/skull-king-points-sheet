package fr.rage.lafie.table.games.points.sheet.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PlayerRoundAndPointEntity(
    @Embedded val player: PlayerEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "playerId",
    )
    val points: PlayerHasPointDuringRoundEntity,
)