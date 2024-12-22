package fr.rage.lafie.skull.king.points.sheet.ui.page.match.choose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toState
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.match.GetMatchesByGameIdUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared.GetGameNameByIdUseCase
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.choose.state.ChooseMatchState
import fr.rage.lafie.skull.king.points.sheet.ui.routing.ChooseMatchRoute
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import fr.rage.lafie.skull.king.points.sheet.utils.zip
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChooseMatchViewModel @Inject constructor(
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