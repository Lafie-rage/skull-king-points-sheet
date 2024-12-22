package fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.maininfos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.game.GetGameByIdUseCase
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.maininfos.state.CreateMatchMainInfosState
import fr.rage.lafie.skull.king.points.sheet.ui.routing.CreateMatchRoute
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateMatchMainInfosViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: GetGameByIdUseCase,
) : ViewModel() {
    private val routeParams: CreateMatchRoute = savedStateHandle.toRoute()

    private val _state: MutableState<Result<CreateMatchMainInfosState>> =
        mutableStateOf(Result.Loading)
    val state: State<Result<CreateMatchMainInfosState>>
        get() = _state

    init {
        viewModelScope.launch {
            _state.value = useCase(UUID.fromString(routeParams.gameId)).map { game ->
                CreateMatchMainInfosState(
                    maxPlayers = game.maxPlayers,
                    minPlayers = game.minPlayers,
                )
            }
        }
    }

    fun changeMatchName(matchName: String) {
        _state.value = _state.value.map { it.copy(matchName = matchName) }
    }
}