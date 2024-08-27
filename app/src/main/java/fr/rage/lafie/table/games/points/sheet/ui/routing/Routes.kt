package fr.rage.lafie.table.games.points.sheet.ui.routing

import fr.rage.lafie.table.games.points.sheet.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
object Home

@Serializable
object CreateGame

@Serializable
data class Matches(
    @Serializable(with = UUIDSerializer::class) val gameId: UUID,
)

@Serializable
data class CreateMatch(
    @Serializable(with = UUIDSerializer::class) val gameId: UUID,
)

@Serializable
data class DefinePlayers(
    @Serializable(with = UUIDSerializer::class) val gameId: UUID,
    @Serializable(with = UUIDSerializer::class) val matchId: UUID,
)

@Serializable
data class Rounds(
    @Serializable(with = UUIDSerializer::class) val gameId: UUID,
    @Serializable(with = UUIDSerializer::class) val matchId: UUID,
)

@Serializable
data class ChoosePlayer(
    @Serializable(with = UUIDSerializer::class) val gameId: UUID,
    @Serializable(with = UUIDSerializer::class) val matchId: UUID,
    val roundIndex: Int,
)

@Serializable
data class PlayerRoundPoint(
    @Serializable(with = UUIDSerializer::class) val gameId: UUID,
    @Serializable(with = UUIDSerializer::class) val matchId: UUID,
    val roundIndex: Int,
    @Serializable(with = UUIDSerializer::class) val playerId: UUID,
)

@Serializable
data class MatchRecap(
    @Serializable(with = UUIDSerializer::class) val gameId: UUID,
    @Serializable(with = UUIDSerializer::class) val matchId: UUID,
)

@Serializable
data class PlayerRecap(
    @Serializable(with = UUIDSerializer::class) val gameId: UUID,
    @Serializable(with = UUIDSerializer::class) val matchId: UUID,
    @Serializable(with = UUIDSerializer::class) val playerId: UUID,
)