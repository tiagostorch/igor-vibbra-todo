package com.igordam.todo.data.datasource

import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.common.ItemModel
import com.igordam.todo.data.model.request.ToDoItemRequest

interface ToDoItemDataSource {

    suspend fun getToDoItems(id: Int): Resource<Failure, List<ItemModel>>
    suspend fun postItem(id: Int, request: ToDoItemRequest): Resource<Failure, List<ItemModel>>
    suspend fun putItem(id: Int, itemId: Int, request: ToDoItemRequest): Resource<Failure, List<ItemModel>>
    suspend fun deleteItem(id: Int, itemId: Int): Resource<Failure, List<ItemModel>>

}