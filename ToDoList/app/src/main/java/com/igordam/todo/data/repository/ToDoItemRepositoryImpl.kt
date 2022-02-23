package com.igordam.todo.data.repository

import com.igordam.todo.core.extensions.logger
import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.datasource.ToDoItemDataSource
import com.igordam.todo.data.model.common.ItemModel
import com.igordam.todo.data.model.request.ToDoItemRequest

class ToDoItemRepositoryImpl(
    private val datasource: ToDoItemDataSource
): ToDoItemRepository {
    override suspend fun getToDoItems(id: Int): Resource<Failure, List<ItemModel>> {
        return try {
            datasource.getToDoItems(id)
        } catch (error: Exception) {
            logger(error.toString())
            Resource.Error(Failure.GenericFailure)
        }
    }

    override suspend fun postItem(
        id: Int,
        request: ToDoItemRequest
    ): Resource<Failure, List<ItemModel>> {
        return try {
            datasource.postItem(id, request)
        } catch (error: Exception) {
            logger(error.toString())
            Resource.Error(Failure.GenericFailure)
        }
    }

    override suspend fun putItem(
        id: Int,
        itemId: Int,
        request: ToDoItemRequest
    ): Resource<Failure, List<ItemModel>> {
        return try {
            datasource.putItem(id, itemId, request)
        } catch (error: Exception) {
            logger(error.toString())
            Resource.Error(Failure.GenericFailure)
        }
    }

    override suspend fun deleteItem(id: Int, itemId: Int): Resource<Failure, List<ItemModel>> {
        return try {
            datasource.deleteItem(id, itemId)
        } catch (error: Exception) {
            logger(error.toString())
            Resource.Error(Failure.GenericFailure)
        }
    }
}