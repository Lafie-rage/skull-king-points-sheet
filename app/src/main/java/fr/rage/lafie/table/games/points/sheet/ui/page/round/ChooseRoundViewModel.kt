package fr.rage.lafie.table.games.points.sheet.ui.page.round

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.domain.usecase.round.GetRoundsByMatchIdUseCase
import fr.rage.lafie.table.games.points.sheet.domain.usecase.shared.GetPageTitleUsingMatchAndGameNamesByMatchIdUseCase
import fr.rage.lafie.table.games.points.sheet.ui.page.round.state.ChooseRoundState
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChooseRound
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.map
import fr.rage.lafie.table.games.points.sheet.utils.zip
import kotlinx.coroutines.launch
import java.util.UUID

class ChooseRoundViewModel(
    savedStateHandle: SavedStateHandle,
    private val getRoundsUseCase: GetRoundsByMatchIdUseCase,
    private val getTitleUseCase: GetPageTitleUsingMatchAndGameNamesByMatchIdUseCase,
) : ViewModel() {
    private val routeParams: ChooseRound = savedStateHandle.toRoute()

    private val _state: MutableState<Result<ChooseRoundState>> =
        mutableStateOf(Result.Loading)
    val state: State<Result<ChooseRoundState>>
        get() = _state

    init {
        val matchId = UUID.fromString(routeParams.matchId)
        viewModelScope.launch {
            _state.value = getTitleUseCase(matchId)
                .zip(getRoundsUseCase(matchId))
                .map { (title, rounds) ->
                    ChooseRoundState(
                        title,
                        rounds
                    )
                }

        }
    }


}