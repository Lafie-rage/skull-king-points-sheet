package fr.rage.lafie.table.games.points.sheet.ui.page.player.round.point

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import fr.rage.lafie.table.games.points.sheet.domain.usecase.GetPlayerByIdUseCase
import fr.rage.lafie.table.games.points.sheet.ui.page.player.choose.PlayerState
import fr.rage.lafie.table.games.points.sheet.utils.Result
import org.koin.android.annotation.KoinViewModel
import java.util.UUID

@KoinViewModel
class PlayerRoundPointViewModel(
    private val useCase: GetPlayerByIdUseCase,
) : ViewModel() {
    private val _points: MutableState<Int> = mutableIntStateOf(0)
    val points: State<Int>
        get() = _points

    private val _player: MutableState<Result<PlayerState>> = mutableStateOf(Result.Loading)
    val player: State<Result<PlayerState>>
        get() = _player

    fun setPlayerId(playerId: UUID) {
        _player.value = useCase.invoke(playerId)
    }

    fun addPoints(value: Int) {
        _points.value += value
    }

    fun removePoints(value: Int) {
        _points.value -= value
    }

    fun setPoints(value: Int) {
        _points.value = value
    }
}