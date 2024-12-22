package fr.rage.lafie.skull.king.points.sheet.domain.mapper

import fr.rage.lafie.skull.king.points.sheet.data.database.entity.MatchEntity
import fr.rage.lafie.skull.king.points.sheet.data.model.Match
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.choose.state.MatchState

// region ENTITY -> MODEL
fun MatchEntity.toModel() = Match(
    id = id,
    name = name,
    roundCounter = roundCounter,
    isFinished = isFinished,
)

fun List<MatchEntity>.toModel(): List<Match> = this.map(MatchEntity::toModel)
// endregion

// region MODEL -> STATE
fun Match.toState() = MatchState(
    id = id,
    name = name,
    isFinished = isFinished,
)

fun List<Match>.toState() = this.map(Match::toState)
// endregion

// region MODEL -> ENTITY
fun Match.toEntity() = MatchEntity(
    id = id,
    name = name,
    roundCounter = roundCounter,
    isFinished = isFinished,
)
// endregion