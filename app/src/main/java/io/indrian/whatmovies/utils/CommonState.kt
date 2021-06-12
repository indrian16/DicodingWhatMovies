package io.indrian.whatmovies.utils

sealed class CommonState<out T> {
    object Loading : CommonState<Nothing>()
    object Empty : CommonState<Nothing>()
    data class Loaded<T>(val data: T) : CommonState<T>()
    data class Error(val message: String) : CommonState<Nothing>()
}
