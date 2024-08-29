package fr.rage.lafie.table.games.points.sheet.domain.mapper

import fr.rage.lafie.table.games.points.sheet.data.database.entity.PlayerEntity
import fr.rage.lafie.table.games.points.sheet.data.model.Player
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState

fun PlayerEntity.toModel() = Player(
    id = id,
    name = name,
)

fun List<PlayerEntity>.toModel(): List<Player> = this.map(PlayerEntity::toModel)

fun Player.toState() = PlayerState(
    id = id,
    name = name,
)

fun List<Player>.toState(): List<PlayerState> = this.map(Player::toState)