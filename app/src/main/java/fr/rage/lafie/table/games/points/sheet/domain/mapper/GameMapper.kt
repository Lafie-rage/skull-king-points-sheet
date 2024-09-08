package fr.rage.lafie.table.games.points.sheet.domain.mapper

import fr.rage.lafie.table.games.points.sheet.data.database.entity.GameEntity
import fr.rage.lafie.table.games.points.sheet.data.model.Game

// region ENTITY -> MODEL
fun GameEntity.toModel() = Game(
    id = id,
    name = name,
)

fun List<GameEntity>.toModel(): List<Game> = this.map(GameEntity::toModel)
// endregion

// region MODEL -> STATE
// endregion

// region MODEL -> ENTITY
fun Game.toEntity() = GameEntity(
    id = id,
    name = name,
)
// endregion