package fr.rage.lafie.table.games.points.sheet.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import fr.rage.lafie.table.games.points.sheet.R
import fr.rage.lafie.table.games.points.sheet.ui.component.core.Loader

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}

val <T> Result<T>.isSuccess get() = this is Result.Success<T>
val <T> Result<T>.isError get() = this is Result.Error
val <T> Result<T>.isLoading get() = this == Result.Loading

fun <T> Result<T>.getOrNull(): T? = when (this) {
    is Result.Success -> this.data
    else -> null
}

fun <T> Result<T>.getExceptionOrNull(): Throwable? = when (this) {
    is Result.Error -> this.exception
    else -> null
}

@Composable
fun <T> Result<T>.MapToComposable(
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable (Throwable) -> Unit = {
        DefaultErrorPlaceholder(it)
    },
    onLoading: @Composable () -> Unit = {
        Loader()
    },
) {
    when (this) {
        is Result.Success -> onSuccess(this.data)
        is Result.Error -> onError(this.exception)
        is Result.Loading -> onLoading()
    }
}

@Composable
private fun DefaultErrorPlaceholder(error: Throwable) {
    println(error.stackTraceToString())
    Text(
        color = Color.Red,
        text = stringResource(R.string.unable_to_load_data)
    )
}
