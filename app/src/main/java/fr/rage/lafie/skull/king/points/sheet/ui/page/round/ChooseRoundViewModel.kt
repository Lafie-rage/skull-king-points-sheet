package fr.rage.lafie.skull.king.points.sheet.ui.page.round

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.match.FinishMatchUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.match.GetMatchStatusUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.round.CreateNewRoundUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.round.GetRoundsByMatchIdUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared.GetMatchNameByIdUseCase
import fr.rage.lafie.skull.king.points.sheet.ui.page.round.state.ChooseRoundState
import fr.rage.lafie.skull.king.points.sheet.ui.routing.ChooseRoundRoute
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import fr.rage.lafie.skull.king.points.sheet.utils.zip
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChooseRoundViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRoundsUseCase: GetRoundsByMatchIdUseCase,
    private val getTitleUseCase: GetMatchNameByIdUseCase,
    private val createNewRoundUseCase: CreateNewRoundUseCase,
    private val getMatchStatusUseCase: GetMatchStatusUseCase,
    private val finishMatchUseCase: FinishMatchUseCase,
) : ViewModel() {
    private val routeParams: ChooseRoundRoute = savedStateHandle.toRoute()

    private val _state: MutableState<Result<ChooseRoundState>> =
        mutableStateOf(Result.Loading)
    val state: State<Result<ChooseRoundState>>
        get() = _state

    init {
        val matchId = UUID.fromString(routeParams.matchId)
        viewModelScope.launch {
            _state.value = getTitleUseCase(matchId)
                .zip(
                    getRoundsUseCase(matchId),
                )
                .zip(
                    getMatchStatusUseCase(matchId)
                )
                .map { (triple, isMatchFinished) ->
                    val (title, rounds) = triple
                    ChooseRoundState(
                        title = title,
                        isMatchFinished = isMatchFinished,
                        rounds = rounds
                    )
                }

        }
    }

    fun createNewRound() {
        val matchId = UUID.fromString(routeParams.matchId)
        viewModelScope.launch {
            createNewRoundUseCase(matchId)
            _state.value = state.value
                .zip(getRoundsUseCase(matchId))
                .map { (value, rounds) ->
                    value.copy(rounds = rounds)
                }
        }
    }

    fun finishMatch() {
        val matchId = UUID.fromString(routeParams.matchId)
        viewModelScope.launch {
            finishMatchUseCase(matchId)
            _state.value = state.value
                .zip(getMatchStatusUseCase(matchId))
                .map { (value, isMatchFinished) ->
                    value.copy(isMatchFinished = isMatchFinished)
                }
        }
    }

}