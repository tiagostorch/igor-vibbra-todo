package com.igordam.todo.core.network

sealed class Resource<out E, out S> {
    data class Success<out S>(val s: S) : Resource<Nothing, S>()
    data class Error<out E>(val e: E) : Resource<E, Nothing>()

    val isSuccess get() = this is Success<S>
    val isError get() = this is Error<E>

    fun <S> success(s: S) = Resource.Success(s)
    fun <E> error(e: E) = Resource.Error(e)

    fun fold(fnE: (E) -> Any, fnS: (S) -> Any): Any =
        when (this){
            is Success -> fnS(s)
            is Error -> fnE(e)
        }
}