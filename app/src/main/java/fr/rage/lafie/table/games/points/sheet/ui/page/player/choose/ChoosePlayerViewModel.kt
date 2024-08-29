package fr.rage.lafie.table.games.points.sheet.ui.page.player.choose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.rage.lafie.table.games.points.sheet.domain.usecase.GetMatchPlayerListUseCase
import fr.rage.lafie.table.games.points.sheet.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import java.util.UUID

@KoinViewModel
class ChoosePlayerViewModel(
    private val useCase: GetMatchPlayerListUseCase,
) : ViewModel() {

    private val _players = MutableStateFlow<Result<List<PlayerState>>>(Result.Loading)
    val players: StateFlow<Result<List<PlayerState>>>
        get() = _players

    fun setMatchId(matchId: UUID) {
        viewModelScope.launch {
            useCase.invoke(matchId).collect { players ->
                _players.value = players
            }
        }
    }
}