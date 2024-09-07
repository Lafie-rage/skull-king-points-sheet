package fr.rage.lafie.table.games.points.sheet.ui.page.player.choose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import fr.rage.lafie.table.games.points.sheet.domain.usecase.GetMatchPlayerListUseCase
import fr.rage.lafie.table.games.points.sheet.ui.routing.ChoosePlayerRoute
import fr.rage.lafie.table.games.points.sheet.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import java.util.UUID

@KoinViewModel
class ChoosePlayerViewModel(
    saveStateHandle: SavedStateHandle,
    private val useCase: GetMatchPlayerListUseCase,
) : ViewModel() {

    private val _players = MutableStateFlow<Result<List<PlayerState>>>(Result.Loading)
    val players: StateFlow<Result<List<PlayerState>>>
        get() = _players

    private val routeParams: ChoosePlayerRoute = saveStateHandle.toRoute()

    init {
        viewModelScope.launch {
            _players.value = useCase.invoke(UUID.fromString(routeParams.matchId))
                .filterNotNull()
                .first()
        }
    }

    fun setMatchId(matchId: UUID) {

    }
}