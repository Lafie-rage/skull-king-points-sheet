package fr.rage.lafie.table.games.points.sheet.ui.routing

import kotlinx.serialization.Serializable

// FIXME Once Compose navigation safe args library supports parameters annotated with @Serializable(with=...), migrate Strings to UUIDs

@Serializable
object HomeRoute

@Serializable
object CreateGameRoute

@Serializable
data class ChooseMatchRoute(
    val gameId: String,
)

@Serializable
data class CreateMatchRoute(
    val gameId: String,
)

@Serializable
data class DefinePlayersRoute(
    val matchId: String,
)

@Serializable
data class ChooseRoundRoute(
    val matchId: String,
)

@Serializable
data class ChoosePlayerRoute(
    val matchId: String,
    val roundIndex: Int,
)

@Serializable
data class PlayerPointRoute(
    val playerId: String,
    val roundIndex: Int,
)

@Serializable
data class MatchRecapRoute(
    val matchId: String,
)

@Serializable
data class PlayerRecapRoute(
    @Serializable val playerId: String,
)