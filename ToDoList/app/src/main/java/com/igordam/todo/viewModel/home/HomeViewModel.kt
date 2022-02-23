package com.igordam.todo.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igordam.todo.core.extensions.logger
import com.igordam.todo.core.network.Failure
import com.igordam.todo.data.model.response.ToDoListResponse
import com.igordam.todo.usecase.ToDoListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: ToDoListUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _toDoList = MutableLiveData<List<ToDoListResponse>>()
    val toDoList: LiveData<List<ToDoListResponse>> = _toDoList
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getToDoLists() {
        _loading.postValue(true)
        viewModelScope.launch {
            kotlinx.coroutines.withContext(ioDispatcher) {
                return@withContext useCase.getLists()
            }.fold(::onFailure, ::onListsSuccess)
        }
    }

    private fun onFailure(error: Failure) {
        logger(error.toString())
        _loading.postValue(false)
        _error.postValue(error.toString())
    }

    private fun onListsSuccess(response: List<ToDoListResponse>) {
        _loading.postValue(false)
        _toDoList.postValue(response)
    }

    fun deleteToDo(id: Int) {
        _loading.postValue(true)
        viewModelScope.launch {
            kotlinx.coroutines.withContext(ioDispatcher) {
                return@withContext useCase.deleteLists(id)
            }.fold(::onFailure, ::onDeleteSuccess)
        }
    }

    private fun onDeleteSuccess(response: Any) {
        logger(response.toString())
        getToDoLists()
    }

}