package com.igordam.todo.usecase

import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.common.ItemModel
import com.igordam.todo.data.model.request.ToDoItemRequest
import com.igordam.todo.data.repository.ToDoItemRepository

class ToDoItemUseCaseImpl(private val repository: ToDoItemRepository): ToDoItemUseCase {
    override suspend fun getToDoItems(id: Int): Resource<Failure, List<ItemModel>> =
        repository.getToDoItems(id)

    override suspend fun postItem(
        id: Int,
        request: ToDoItemRequest
    ): Resource<Failure, List<ItemModel>> =
        repository.postItem(id, request)

    override suspend fun putItem(
        id: Int,
        itemId: Int,
        request: ToDoItemRequest
    ): Resource<Failure, List<ItemModel>> =
        repository.putItem(id, itemId, request)

    override suspend fun deleteItem(id: Int, itemId: Int): Resource<Failure, List<ItemModel>> =
        repository.deleteItem(id, itemId)
}