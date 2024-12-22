package fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.playerlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.match.SaveMatchUseCase
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.playerlist.state.CreateMatchPlayerListState
import fr.rage.lafie.skull.king.points.sheet.ui.routing.CreateMatchPlayerListRoute
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateMatchPlayerListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val saveMatchUseCase: SaveMatchUseCase,
) : ViewModel() {
    private val routeParams: CreateMatchPlayerListRoute = savedStateHandle.toRoute()

    private val _state: MutableState<CreateMatchPlayerListState> =
        mutableStateOf(
            CreateMatchPlayerListState(
                title = routeParams.matchName,
                playersName = List(routeParams.playersCount) { "" }
            )
        )
    val state: State<CreateMatchPlayerListState>
        get() = _state

    init {
        viewModelScope.launch {
            val (matchName, playersCount) = routeParams
            _state.value = CreateMatchPlayerListState(
                title = matchName,
                playersName = List(playersCount) { "" }
            )
        }
    }

    fun changePlayerName(index: Int, name: String) {
        _state.value = _state.value.let { value ->
            value.copy(
                playersName = value.playersName.mapIndexed { i, baseName ->
                    if (i == index) name.trimStart() else baseName
                }
            )
        }

    }

    fun saveMatch() {
        viewModelScope.launch {
            saveMatchUseCase(
                id = state.value.matchId,
                name = routeParams.matchName,
                playersName = _state.value.playersName,
            )
        }
    }

}