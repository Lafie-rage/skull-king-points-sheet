package fr.rage.lafie.table.games.points.sheet.domain.mapper

import fr.rage.lafie.table.games.points.sheet.data.database.entity.MatchEntity
import fr.rage.lafie.table.games.points.sheet.data.model.Match

// region ENTITY -> MODEL
fun MatchEntity.toModel() = Match(
    id = id,
    gameId = gameId,
    name = name,
    roundCounter = roundCounter,
)

fun List<MatchEntity>.toModel(): List<Match> = this.map(MatchEntity::toModel)
// endregion

// region MODEL -> STATE
// endregion

// region MODEL -> ENTITY
fun Match.toEntity() = MatchEntity(
    id = id,
    gameId = gameId,
    name = name,
    roundCounter = roundCounter,
)
// endregion