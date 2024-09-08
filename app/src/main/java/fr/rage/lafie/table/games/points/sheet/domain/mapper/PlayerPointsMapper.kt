package fr.rage.lafie.table.games.points.sheet.domain.mapper

import fr.rage.lafie.table.games.points.sheet.data.database.entity.PlayerPointsEntity
import fr.rage.lafie.table.games.points.sheet.data.model.PlayerPoints
import fr.rage.lafie.table.games.points.sheet.ui.page.player.round.point.PlayerPointsState

// region ENTITY -> MODEL
fun PlayerPointsEntity.toModel() = PlayerPoints(
    id = id,
    playerId = playerId,
    roundIndex = roundIndex,
    points = points,
)
// endregion

// region MODEL -> STATE
fun PlayerPoints.toState() = PlayerPointsState(
    id = id,
    playerId = playerId,
    roundIndex = roundIndex,
    points = points,
)
// endregion


// region STATE -> MODEL
fun PlayerPointsState.toModel() = PlayerPoints(
    id = id,
    playerId = playerId,
    roundIndex = roundIndex,
    points = points,
)
// endregion

// region MODEL -> ENTITY
fun PlayerPoints.toEntity() = PlayerPointsEntity(
    id = id,
    playerId = playerId,
    roundIndex = roundIndex,
    points = points,
)
// endregion