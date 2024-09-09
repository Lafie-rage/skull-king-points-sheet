package fr.rage.lafie.table.games.points.sheet.utils

import kotlin.reflect.KClass

// region TRANSFORM SUCCESS
inline fun <A, B> Result<A>.map(transform: (A) -> B): Result<B> = when (this) {
    is Result.Success -> Result.Success(transform(getOrNull()!!))
    is Result.Error -> this
    is Result.Loading -> this
}

suspend inline fun <A, B> Result<A>.flatMap(crossinline transform: suspend (A) -> Result<B>): Result<B> =
    when (this) {
        is Result.Success -> transform(data)
        is Result.Error -> this
        is Result.Loading -> this
    }

inline fun <A> Result<A>.ifSuccess(
    action: (A) -> Unit,
): Result<A> {
    if (this is Result.Success) action(data)
    return this
}
// endregion

// region TRANSFORM ERROR
inline fun <A, reified E : Throwable> Result<A>.onErrorResume(
    type: KClass<E>,
    fallback: (E) -> A,
): Result<A> =
    when (this) {
        is Result.Success -> this
        is Result.Error -> {
            if (exception is E) Result.Success(fallback(exception)) else this
        }

        is Result.Loading -> this
    }
// endregion

// region COMBINE
fun <A, B> Result<A>.zip(
    other: Result<B>,
): Result<Pair<A, B>> = when {
    this is Result.Error -> this
    other is Result.Error -> other
    this is Result.Loading -> this
    other is Result.Loading -> other
    else -> Result.Success(Pair(this.getOrNull()!!, other.getOrNull()!!))
}
// endregion