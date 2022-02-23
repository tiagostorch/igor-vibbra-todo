package com.igordam.todo.data.repository

import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.request.ToDoListRequest
import com.igordam.todo.data.model.response.ToDoListResponse
import com.igordam.todo.data.model.response.UpdateToDoListResponse

interface ToDoListRepository {

    suspend fun getLists(): Resource<Failure, List<ToDoListResponse>>
    suspend fun getListById(id: Int): Resource<Failure, ToDoListResponse>
    suspend fun postLists(request: ToDoListRequest): Resource<Failure, UpdateToDoListResponse>
    suspend fun putLists(id: Int, request: ToDoListRequest): Resource<Failure, UpdateToDoListResponse>
    suspend fun deleteLists(id: Int): Resource<Failure, Int>

}