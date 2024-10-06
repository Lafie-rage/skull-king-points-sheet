package fr.rage.lafie.skull.king.points.sheet.domain.mapper

import fr.rage.lafie.skull.king.points.sheet.data.database.entity.PlayerPointsEntity
import fr.rage.lafie.skull.king.points.sheet.data.model.PlayerPoints
import fr.rage.lafie.skull.king.points.sheet.ui.page.player.point.state.PlayerPointsState

// region ENTITY -> MODEL
fun PlayerPointsEntity.toModel() = PlayerPoints(
    id = id,
    playerId = playerId,
    roundIndex = roundIndex,
    points = points,
)

fun List<PlayerPointsEntity>.toModel(): List<PlayerPoints> = this.map(PlayerPointsEntity::toModel)
// endregion

// region MODEL -> STATE
fun PlayerPoints.toState() = PlayerPointsState(
    id = id,
    playerId = playerId,
    roundIndex = roundIndex,
    points = points,
)

fun List<PlayerPoints>.toState(): List<PlayerPointsState> = this.map(PlayerPoints::toState)
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