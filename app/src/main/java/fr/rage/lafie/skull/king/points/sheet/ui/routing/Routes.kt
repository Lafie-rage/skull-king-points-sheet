package fr.rage.lafie.skull.king.points.sheet.ui.routing

import kotlinx.serialization.Serializable

// FIXME Once Compose navigation safe args library supports parameters annotated with @Serializable(with=...), migrate Strings to UUIDs
@Serializable
object ChooseMatchRoute

@Serializable
object CreateMatchRoute

@Serializable
object CreateMatchMainInfosRoute

@Serializable
data class CreateMatchPlayerListRoute(
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