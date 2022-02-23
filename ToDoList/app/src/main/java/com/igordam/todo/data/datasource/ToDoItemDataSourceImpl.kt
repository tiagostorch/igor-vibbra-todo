package com.igordam.todo.data.datasource

import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.common.ItemModel
import com.igordam.todo.data.model.request.ToDoItemRequest
import com.igordam.todo.data.service.ToDoApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class ToDoItemDataSourceImpl: ToDoItemDataSource, KoinComponent {

    private val apiService: ToDoApiService by inject()

    override suspend fun getToDoItems(id: Int): Resource<Failure, List<ItemModel>> =
        apiService.getToDoItems(id)

    override suspend fun postItem(
        id: Int,
        request: ToDoItemRequest
    ): Resource<Failure, List<ItemModel>> =
        apiService.postItem(id, request)

    override suspend fun putItem(
        id: Int,
        itemId: Int,
        request: ToDoItemRequest
    ): Resource<Failure, List<ItemModel>> =
        apiService.putItem(id, itemId, request)

    override suspend fun deleteItem(id: Int, itemId: Int): Resource<Failure, List<ItemModel>> =
        apiService.deleteItem(id, itemId)
}