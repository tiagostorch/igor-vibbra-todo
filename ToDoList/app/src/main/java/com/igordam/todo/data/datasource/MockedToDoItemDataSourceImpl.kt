package com.igordam.todo.data.datasource

import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.common.ItemModel
import com.igordam.todo.data.model.request.ToDoItemRequest
import com.igordam.todo.data.model.response.ToDoListResponse
import com.igordam.todo.data.service.ToDoApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class MockedToDoItemDataSourceImpl: ToDoItemDataSource {

    override suspend fun getToDoItems(id: Int): Resource<Failure, List<ItemModel>> =
        Resource.Success(
            listOf(
                ItemModel(
                    id = 1,
                    item = "todo",
                    order = 2
                )
            )
        )

    override suspend fun postItem(
        id: Int,
        request: ToDoItemRequest
    ): Resource<Failure, List<ItemModel>> =
        Resource.Success(
            listOf(
                ItemModel(
                    id = 1,
                    item = "todo",
                    order = 2
                )
            )
        )

    override suspend fun putItem(
        id: Int,
        itemId: Int,
        request: ToDoItemRequest
    ): Resource<Failure, List<ItemModel>> =
        Resource.Success(
            listOf(
                ItemModel(
                    id = 1,
                    item = "todo",
                    order = 2
                )
            )
        )

    override suspend fun deleteItem(id: Int, itemId: Int): Resource<Failure, List<ItemModel>> =
        Resource.Success(
            listOf(
                ItemModel(
                    id = 1,
                    item = "todo",
                    order = 2
                )
            )
        )
}