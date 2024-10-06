package fr.rage.lafie.skull.king.points.sheet.ui.routing

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
object CreateMatchMainInfosRoute

@Serializable
data class CreateMatchPlayerListRoute(
    val gameId: String,
    val matchName: String,
    val playersCount: Int,
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