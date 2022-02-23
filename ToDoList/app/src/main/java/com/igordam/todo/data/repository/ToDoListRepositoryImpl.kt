package com.igordam.todo.data.repository

import com.igordam.todo.core.extensions.logger
import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.datasource.ToDoListDataSource
import com.igordam.todo.data.model.request.ToDoListRequest
import com.igordam.todo.data.model.response.ToDoListResponse
import com.igordam.todo.data.model.response.UpdateToDoListResponse

class ToDoListRepositoryImpl(
    private val datasource: ToDoListDataSource
): ToDoListRepository {

    override suspend fun getLists(): Resource<Failure, List<ToDoListResponse>> {
        return try {
            datasource.getLists()
        } catch (error: Exception) {
            logger(error.toString())
            Resource.Error(Failure.GenericFailure)
        }
    }

    override suspend fun getListById(id: Int): Resource<Failure, ToDoListResponse> {
        return try {
            datasource.getListById(id)
        } catch (error: Exception) {
            logger(error.toString())
            Resource.Error(Failure.GenericFailure)
        }
    }

    override suspend fun postLists(
        request: ToDoListRequest
    ): Resource<Failure, UpdateToDoListResponse> {
        return try {
            datasource.postLists(request)
        } catch (error: Exception) {
            logger(error.toString())
            Resource.Error(Failure.GenericFailure)
        }
    }

    override suspend fun putLists(
        id: Int,
        request: ToDoListRequest
    ): Resource<Failure, UpdateToDoListResponse> {
        return try {
            datasource.putLists(id, request)
        } catch (error: Exception) {
            logger(error.toString())
            Resource.Error(Failure.GenericFailure)
        }
    }

    override suspend fun deleteLists(id: Int): Resource<Failure, Int> {
        return try {
            datasource.deleteLists(id)
        } catch (error: Exception) {
            logger(error.toString())
            Resource.Error(Failure.GenericFailure)
        }
    }

}