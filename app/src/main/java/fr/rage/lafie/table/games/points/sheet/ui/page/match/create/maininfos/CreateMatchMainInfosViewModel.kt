package fr.rage.lafie.table.games.points.sheet.ui.page.match.create.maininfos

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateMatchMainInfosViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    val routeParams:   = savedStateHandle.toRoute()
}