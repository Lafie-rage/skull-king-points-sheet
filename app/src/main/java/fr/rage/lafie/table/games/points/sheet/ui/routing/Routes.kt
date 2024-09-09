package fr.rage.lafie.table.games.points.sheet.ui.routing

import kotlinx.serialization.Serializable

// FIXME Once Compose navigation safe args library supports parameters annotated with @Serializable(with=...), migrate Strings to UUIDs

@Serializable
object Home

@Serializable
object CreateGame

@Serializable
data class Matches(
    val gameId: String,
)

@Serializable
data class CreateMatch(
    val gameId: String,
)

@Serializable
data class DefinePlayers(
    val matchId: String,
)

@Serializable
data class ChooseRound(
    val matchId: String,
)

@Serializable
data class ChoosePlayerRoute(
    val matchId: String,
    val roundIndex: Int,
)

@Serializable
data class PlayerRoundPointRoute(
    val playerId: String,
    val roundIndex: Int,
)

@Serializable
data class MatchRecap(
    val matchId: String,
)

@Serializable
data class PlayerRecap(
    @Serializable val playerId: String,
)