package fr.rage.lafie.skull.king.points.sheet.ui.page.match.recap

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.rage.lafie.skull.king.points.sheet.data.model.Player
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.match.GetMatchPlayerListUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.points.GetPlayerPointsByPlayerIdUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared.GetMatchNameByIdUseCase
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.recap.state.MatchRecapState
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.recap.state.PlayerAndScoreState
import fr.rage.lafie.skull.king.points.sheet.ui.routing.MatchRecapRoute
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.collectList
import fr.rage.lafie.skull.king.points.sheet.utils.flatMap
import fr.rage.lafie.skull.king.points.sheet.utils.map
import fr.rage.lafie.skull.king.points.sheet.utils.zip
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MatchRecapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMatchNameUseCase: GetMatchNameByIdUseCase,
    private val getPlayersUseCase: GetMatchPlayerListUseCase,
    private val getPlayerPointsUseCase: GetPlayerPointsByPlayerIdUseCase
) : ViewModel() {
    private val routeParams: MatchRecapRoute = savedStateHandle.toRoute()

    val state: StateFlow<Result<MatchRecapState>> = buildState()

    private fun buildState(): StateFlow<Result<MatchRecapState>> {
        val matchId = UUID.fromString(routeParams.matchId)
        return getPlayersUseCase(matchId)
            .map { playersResult ->
                playersResult.flatMap { players ->
                    getPlayerPoints(players)
                        .zip(getTitle(matchId))
                        .map { (players, title) ->
                            MatchRecapState(
                                players = players,
                                title = title,
                            )
                        }
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = Result.Loading,
            )
    }

    private suspend fun getPlayerPoints(players: List<Player>): Result<List<PlayerAndScoreState>> {
        return players.map { player ->
            getPlayerPointsUseCase(player.id)
                .map { scores ->
                    scores.map { roundScore ->
                        roundScore.points
                    }
                }
                .map { points ->
                    PlayerAndScoreState(
                        name = player.name,
                        roundScores = points,
                    )
                }
        }.collectList()
    }

    private suspend fun getTitle(matchId: UUID): Result<String> {
        return getMatchNameUseCase.invoke(matchId)
    }
}