package fr.rage.lafie.table.games.points.sheet.domain.mapper

import fr.rage.lafie.table.games.points.sheet.data.database.entity.PlayerEntity
import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import java.util.UUID

// region ENTITY -> MODEL
fun PlayerEntity.toModel() = Player(
    id = id,
    name = name,
)

fun List<PlayerEntity>.toModel(): List<Player> = this.map(PlayerEntity::toModel)
// endregion

// region MODEL -> STATE
fun Player.toState() = PlayerState(
    id = id,
    name = name,
)

fun List<Player>.toState(): List<PlayerState> = this.map(Player::toState)
// endregion

// region MODEL -> ENTITY
fun Player.toEntity(matchId: UUID) = PlayerEntity(
    id = id,
    matchId = matchId,
    name = name,
)

fun List<Player>.toEntity(matchId: UUID) = this.map { player ->
    player.toEntity(matchId)
}
// endregion