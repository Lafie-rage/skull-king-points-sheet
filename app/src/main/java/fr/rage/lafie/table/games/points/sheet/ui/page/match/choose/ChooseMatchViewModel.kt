package fr.rage.lafie.table.games.points.sheet.ui.page.match.choose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.domain.mapper.toState
import fr.rage.lafie.table.games.points.sheet.domain.usecase.match.GetMatchesByGameIdUseCase
import fr.rage.lafie.table.games.points.sheet.domain.usecase.shared.GetGameNameByIdUseCase
import fr.rage.lafie.table.games.points.sheet.ui.page.match.choose.state.ChooseMatchState
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChooseMatchRoute
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.map
import fr.rage.lafie.table.games.points.sheet.utils.zip
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel
import java.util.UUID

@KoinViewModel
class ChooseMatchViewModel(
    savedStateHandle: SavedStateHandle,
    private val getMatchesUseCase: GetMatchesByGameIdUseCase,
    private val getGameNameUseCase: GetGameNameByIdUseCase,
) : ViewModel() {
    private val routeParams: ChooseMatchRoute = savedStateHandle.toRoute()

    val state: StateFlow<Result<ChooseMatchState>> = buildState()

    private fun buildState(): StateFlow<Result<ChooseMatchState>> {
        val gameId = UUID.fromString(routeParams.gameId)
        return getMatchesUseCase(gameId)
            .map { matchesResult ->
                getGameNameUseCase(gameId)
                    .zip(matchesResult)
                    .map { (gameName, matches) ->
                        ChooseMatchState(
                            gameName = gameName,
                            matches = matches.toState(),
                        )
                    }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = Result.Loading,
            )
    }
}