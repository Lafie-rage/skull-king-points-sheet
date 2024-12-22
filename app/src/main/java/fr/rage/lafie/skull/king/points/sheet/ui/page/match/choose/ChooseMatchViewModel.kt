package fr.rage.lafie.skull.king.points.sheet.ui.page.match.choose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.rage.lafie.skull.king.points.sheet.domain.mapper.toState
import fr.rage.lafie.skull.king.points.sheet.domain.usecase.match.GetMatchesByGameIdUseCase
import fr.rage.lafie.skull.king.points.sheet.ui.page.match.choose.state.ChooseMatchState
import fr.rage.lafie.skull.king.points.sheet.utils.Result
import fr.rage.lafie.skull.king.points.sheet.utils.map
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChooseMatchViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesByGameIdUseCase,
) : ViewModel() {

    val state: StateFlow<Result<ChooseMatchState>> = buildState()

    private fun buildState(): StateFlow<Result<ChooseMatchState>> {
        return getMatchesUseCase()
            .map { matchesResult ->
                matchesResult.map { matches ->
                    ChooseMatchState(
                        matches = matches.toState(),
                    )
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = Result.Loading,
            )
    }
}