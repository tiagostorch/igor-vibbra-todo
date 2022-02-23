package com.igordam.todo.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igordam.todo.core.extensions.logger
import com.igordam.todo.core.network.Failure
import com.igordam.todo.data.model.request.ToDoListRequest
import com.igordam.todo.data.model.response.UpdateToDoListResponse
import com.igordam.todo.usecase.ToDoListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class EditViewModel(
    private val useCase: ToDoListUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun editToDo(id: Int, name: String) {
        _loading.postValue(true)
        viewModelScope.launch {
            kotlinx.coroutines.withContext(ioDispatcher) {
                return@withContext useCase.putLists(id, ToDoListRequest(id, name))
            }.fold(::onFailure, ::onListsSuccess)
        }
    }

    fun createToDo(id: Int, name: String) {
        _loading.postValue(true)
        viewModelScope.launch {
            kotlinx.coroutines.withContext(ioDispatcher) {
                return@withContext useCase.postLists(ToDoListRequest(id, name))
            }.fold(::onFailure, ::onListsSuccess)
        }
    }

    private fun onFailure(error: Failure) {
        logger(error.toString())
        _loading.postValue(false)
        _error.postValue(error.toString())
    }

    private fun onListsSuccess(response: UpdateToDoListResponse) {
        logger(response.toString())
        _loading.postValue(false)
        _success.postValue(true)
    }
}