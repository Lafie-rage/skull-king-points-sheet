package fr.rage.lafie.skull.king.points.sheet.ui.page.player.point

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.player.GetPlayerByIdUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.points.GetPlayerPointsByPlayerIdAndRoundIndexUseCase
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.points.SavePlayerPointsUseCase
import fr.rage.lafie.skull.king.points.sheet.ui.page.player.choose.state.PlayerState
import fr.rage.lafie.skull.king.points.sheet.ui.page.player.point.state.PlayerPointsState
import fr.rage.lafie.skull.king.points.sheet.ui.routing.PlayerPointRoute
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.doOnNext
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import java.util.UUID

@KoinViewModel
class PlayerPointViewModel(
    savedStateHandle: SavedStateHandle,
    private val getPlayerUseCase: GetPlayerByIdUseCase,
    private val getPlayerPointsUseCase: GetPlayerPointsByPlayerIdAndRoundIndexUseCase,
    private val savePlayerPointsUseCase: SavePlayerPointsUseCase,
) : ViewModel() {
    private val routeParams: PlayerPointRoute = savedStateHandle.toRoute()

    private val state: MutableState<PlayerPointsState> = mutableStateOf(
        PlayerPointsState(
            id = UUID.randomUUID(),
            playerId = UUID.fromString(routeParams.playerId),
            roundIndex = routeParams.roundIndex,
            points = 0,
        )
    )

    // TODO Merge avec le state
    private val _points: MutableState<Long> = mutableLongStateOf(0)
    val points: State<Long>
        get() = _points

    // TODO Merge avec le state
    private val _player: MutableState<Result<PlayerState>> = mutableStateOf(Result.Loading)
    val player: State<Result<PlayerState>>
        get() = _player


    init {
        viewModelScope.launch {
            // TODO Certainement à simplifier avec retrait de _points et _player
            val playerId = UUID.fromString(routeParams.playerId)
            _player.value = getPlayerUseCase(playerId)

            getPlayerPointsUseCase(playerId, routeParams.roundIndex).doOnNext {
                state.value = it
                _points.value = it.points
            }
        }
    }

    fun setPoints(value: Long) {
        _points.value = value
        state.value = state.value.copy(points = value)
    }

    fun save() {
        viewModelScope.launch {
            savePlayerPointsUseCase(state.value)
        }
    }
}