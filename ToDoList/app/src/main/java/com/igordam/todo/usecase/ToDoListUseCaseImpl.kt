package com.igordam.todo.usecase

import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.request.ToDoListRequest
import com.igordam.todo.data.model.response.ToDoListResponse
import com.igordam.todo.data.model.response.UpdateToDoListResponse
import com.igordam.todo.data.repository.ToDoListRepository

class ToDoListUseCaseImpl(private val repository: ToDoListRepository): ToDoListUseCase {
    override suspend fun getLists(): Resource<Failure, List<ToDoListResponse>> =
        repository.getLists()

    override suspend fun getListById(id: Int): Resource<Failure, ToDoListResponse> =
        repository.getListById(id)

    override suspend fun postLists(
        request: ToDoListRequest
    ): Resource<Failure, UpdateToDoListResponse> =
        repository.postLists(request)

    override suspend fun putLists(
        id: Int,
        request: ToDoListRequest
    ): Resource<Failure, UpdateToDoListResponse> =
        repository.putLists(id, request)

    override suspend fun deleteLists(id: Int): Resource<Failure, Int> =
        repository.deleteLists(id)
}