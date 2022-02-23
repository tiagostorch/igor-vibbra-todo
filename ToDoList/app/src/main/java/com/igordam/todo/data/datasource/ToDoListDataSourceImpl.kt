package com.igordam.todo.data.datasource

import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.request.ToDoListRequest
import com.igordam.todo.data.model.response.ToDoListResponse
import com.igordam.todo.data.model.response.UpdateToDoListResponse
import com.igordam.todo.data.service.ToDoApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class ToDoListDataSourceImpl: ToDoListDataSource, KoinComponent {

    private val apiService: ToDoApiService by inject()

    override suspend fun getLists(): Resource<Failure, List<ToDoListResponse>> =
        apiService.getLists()

    override suspend fun getListById(id: Int): Resource<Failure, ToDoListResponse> =
        apiService.getListById(id)

    override suspend fun postLists(
        request: ToDoListRequest
    ): Resource<Failure, UpdateToDoListResponse> =
        apiService.postLists(request)

    override suspend fun putLists(
        id: Int,
        request: ToDoListRequest
    ): Resource<Failure, UpdateToDoListResponse> =
        apiService.putLists(id, request)

    override suspend fun deleteLists(id: Int): Resource<Failure, Int> =
        apiService.deleteLists(id)
}