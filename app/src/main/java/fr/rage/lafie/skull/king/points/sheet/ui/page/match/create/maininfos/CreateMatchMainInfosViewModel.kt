package fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.maininfos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.create.maininfos.state.CreateMatchMainInfosState
import javax.inject.Inject

@HiltViewModel
class CreateMatchMainInfosViewModel @Inject constructor(
) : ViewModel() {

    private val _state: MutableState<CreateMatchMainInfosState> =
        mutableStateOf(CreateMatchMainInfosState())
    val state: State<CreateMatchMainInfosState>
        get() = _state


    fun changeMatchName(matchName: String) {
        _state.value = _state.value.copy(matchName = matchName)
    }
}