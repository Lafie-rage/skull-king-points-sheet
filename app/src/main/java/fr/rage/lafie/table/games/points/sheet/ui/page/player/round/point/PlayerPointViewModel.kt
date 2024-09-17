package fr.rage.lafie.table.games.points.sheet.ui.page.player.round.point

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.domain.usecase.player.GetPlayerByIdUseCase
import fr.rage.lafie.table.games.points.sheet.domain.usecase.points.GetPlayerPointsByPlayerIdUseCase
import fr.rage.lafie.table.games.points.sheet.domain.usecase.points.SavePlayerPointsUseCase
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import fr.rage.lafie.table.games.points.sheet.ui.routing.PlayerPointRoute
import fr.rage.lafie.table.games.points.sheet.utils.Result
import fr.rage.lafie.table.games.points.sheet.utils.ifSuccess
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import java.util.UUID

@KoinViewModel
class PlayerPointViewModel(
    savedStateHandle: SavedStateHandle,
    private val getPlayerUseCase: GetPlayerByIdUseCase,
    private val getPlayerPointsUseCase: GetPlayerPointsByPlayerIdUseCase,
    private val savePlayerPointsUseCase: SavePlayerPointsUseCase,
) : ViewModel() {
    private val state by lazy {
        mutableStateOf(
            PlayerPointsState(
                id = UUID.randomUUID(),
                playerId = UUID.fromString(routeParams.playerId),
                roundIndex = routeParams.roundIndex,
                points = 0,
            )
        )
    }

    private val _points: MutableState<Long> = mutableLongStateOf(0)
    val points: State<Long>
        get() = _points

    private val _player: MutableState<Result<PlayerState>> = mutableStateOf(Result.Loading)
    val player: State<Result<PlayerState>>
        get() = _player

    private val routeParams: PlayerPointRoute = savedStateHandle.toRoute()

    init {
        viewModelScope.launch {
            val playerId = UUID.fromString(routeParams.playerId)
            _player.value = getPlayerUseCase(playerId)

            getPlayerPointsUseCase(playerId).ifSuccess {
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