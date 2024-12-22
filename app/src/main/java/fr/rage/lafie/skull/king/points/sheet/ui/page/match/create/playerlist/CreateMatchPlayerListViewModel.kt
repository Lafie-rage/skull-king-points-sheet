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
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.shared.GetGameNameByIdUseCase
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.playerlist.state.CreateMatchPlayerListState
import fr.rage.lafie.skull.king.points.sheet.ui.routing.CreateMatchPlayerListRoute
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.getOrNull
import fr.rage.lafie.skull.king.points.sheet.utils.map
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateMatchPlayerListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val saveMatchUseCase: SaveMatchUseCase,
    private val getGameNameUseCase: GetGameNameByIdUseCase,
) : ViewModel() {
    private val routeParams: CreateMatchPlayerListRoute = savedStateHandle.toRoute()

    private val _state: MutableState<Result<CreateMatchPlayerListState>> =
        mutableStateOf(
            Result.Loading
        )
    val state: State<Result<CreateMatchPlayerListState>>
        get() = _state

    init {
        viewModelScope.launch {
            val (gameId, matchName, playersCount) = routeParams
            _state.value = getGameNameUseCase(UUID.fromString(gameId))
                .map { gameName ->
                    CreateMatchPlayerListState(
                        title = "$gameName - $matchName",
                        playersName = List(playersCount) { "" }
                    )
                }
        }
    }

    fun changePlayerName(index: Int, name: String) {
        _state.value = _state.value.map { value ->
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
                id = state.value.getOrNull()!!.matchId,
                gameId = UUID.fromString(routeParams.gameId),
                name = routeParams.matchName,
                playersName = _state.value.getOrNull()!!.playersName,
            )
        }
    }

}