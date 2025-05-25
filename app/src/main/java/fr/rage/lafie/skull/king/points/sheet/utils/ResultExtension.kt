package fr.rage.lafie.skull.king.points.sheet.utils

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

fun <A> Result<A>.then(): Result<Unit> = when (this) {
    is Result.Loading -> this
    is Result.Error -> this
    is Result.Success -> Result.Success(Unit)
}
// endregion

// region TRANSFORM ERROR
inline fun <A, reified E : Throwable> Result<A>.onErrorResume(
    type: KClass<E>,
    fallback: (E) -> A,
): Result<A> = when (this) {
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

fun <A, B, C> Result<A>.zip(
    first: Result<B>,
    second: Result<C>,
): Result<Triple<A, B, C>> = when {
    this is Result.Error -> this
    first is Result.Error -> first
    second is Result.Error -> second
    this is Result.Loading -> this
    first is Result.Loading -> first
    second is Result.Loading -> second
    else -> Result.Success(Triple(this.getOrNull()!!, first.getOrNull()!!, second.getOrNull()!!))
}
// endregion

// region CHAIN
inline fun <A> Result<A>.doOnNext(
    action: (A) -> Unit,
): Result<A> {
    if (this is Result.Success) action(data)
    return this
}
// endregion

// region CONVERT Result<List<T>> <-> List<Result<T>>
fun <A> Result<List<A>>.toIterable(): List<Result<A>> = when (this) {
    is Result.Error -> listOf(Result.Error(this.exception))
    is Result.Loading -> listOf(Result.Loading)
    is Result.Success -> data.map { Result.Success(it) }
}

fun <A> List<Result<A>>.collectList(): Result<List<A>> {
    val result = this.map { value ->
        // If their is any error or loading, they shall prevail
        if (value is Result.Error) return Result.Error(value.exception)
        if (value is Result.Loading) return Result.Loading
        value.getOrNull()!!
    }
    return Result.Success(result)
}
// endregion