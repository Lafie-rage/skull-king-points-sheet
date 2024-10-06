package fr.rage.lafie.skull.king.points.sheet.ui.page.player.choose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toState
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.match.GetMatchPlayerListUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared.GetGameNameByMatchIdUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared.GetMatchNameByIdUseCase
import fr.rage.lafie.skull.king.points.sheet.ui.page.player.choose.state.ChoosePlayerState
import fr.rage.lafie.skull.king.points.sheet.ui.routing.ChoosePlayerRoute
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import fr.rage.lafie.skull.king.points.sheet.utils.zip
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel
import java.util.UUID

@KoinViewModel
class ChoosePlayerViewModel(
    savedStateHandle: SavedStateHandle,
    private val getPlayersUseCase: GetMatchPlayerListUseCase,
    private val getMatchNameUseCase: GetMatchNameByIdUseCase,
    private val getGameNameUseCase: GetGameNameByMatchIdUseCase,
) : ViewModel() {

    private val routeParams: ChoosePlayerRoute = savedStateHandle.toRoute()

    val state: StateFlow<Result<ChoosePlayerState>> =
        buildState()

    private fun buildState(): StateFlow<Result<ChoosePlayerState>> {
        val matchId = UUID.fromString(routeParams.matchId)
        return getPlayersUseCase(matchId)
            .filterNotNull()
            .map { rounds ->
                getMatchNameUseCase(matchId)
                    .zip(getGameNameUseCase(matchId))
                    .map { (matchName, gameName) ->
                        "$gameName - $matchName"
                    }.zip(rounds)
                    .map { (title, players) ->
                        ChoosePlayerState(
                            title = title,
                            players = players.toState(),
                        )
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = Result.Loading,
            )
    }
}