package fr.rage.lafie.table.games.points.sheet.ui.page.player.choose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.domain.usecase.match.GetMatchPlayerListUseCase
import fr.rage.lafie.table.games.points.sheet.domain.usecase.shared.GetPageTitleUsingMatchAndGameNamesByMatchIdUseCase
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.state.ChoosePlayerState
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChoosePlayerRoute
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.map
import fr.rage.lafie.table.games.points.sheet.utils.zip
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
    private val getTitleUseCase: GetPageTitleUsingMatchAndGameNamesByMatchIdUseCase,
) : ViewModel() {

    private val routeParams: ChoosePlayerRoute = savedStateHandle.toRoute()

    val state: StateFlow<Result<ChoosePlayerState>> = buildState(
        UUID.fromString(routeParams.matchId),
    )

    private fun buildState(matchId: UUID) = getPlayersUseCase(matchId)
        .filterNotNull()
        .map { rounds ->
            getTitleUseCase(matchId).zip(rounds)
                .map { (title, players) ->
                    ChoosePlayerState(
                        title = title,
                        players = players,
                    )
                }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Result.Loading,
        )
}