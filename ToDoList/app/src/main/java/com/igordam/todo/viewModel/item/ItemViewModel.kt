package com.igordam.todo.viewModel.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igordam.todo.core.extensions.logger
import com.igordam.todo.core.network.Failure
import com.igordam.todo.data.model.common.ItemModel
import com.igordam.todo.usecase.ToDoItemUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ItemViewModel(
    private val useCase: ToDoItemUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _itemsList = MutableLiveData<List<ItemModel>>()
    val itemsList: LiveData<List<ItemModel>> = _itemsList
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getItemsList(id: Int) {
        _loading.postValue(true)
        viewModelScope.launch {
            kotlinx.coroutines.withContext(ioDispatcher) {
                return@withContext useCase.getToDoItems(id)
            }.fold(::onFailure, ::onListsSuccess)
        }
    }

    private fun onFailure(error: Failure) {
        logger(error.toString())
        _loading.postValue(false)
        _error.postValue(error.toString())
    }

    private fun onListsSuccess(response: List<ItemModel>) {
        _loading.postValue(false)
        _itemsList.postValue(response)
    }
}