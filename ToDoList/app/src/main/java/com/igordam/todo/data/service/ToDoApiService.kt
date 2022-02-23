package com.igordam.todo.data.service

import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.common.ItemModel
import com.igordam.todo.data.model.request.ToDoItemRequest
import com.igordam.todo.data.model.request.ToDoListRequest
import com.igordam.todo.data.model.response.ToDoListResponse
import com.igordam.todo.data.model.response.UpdateToDoListResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface ToDoApiService {

    //region [Lists]
    suspend fun getLists(): Resource<Failure, List<ToDoListResponse>>
    suspend fun getListById(id: Int): Resource<Failure, ToDoListResponse>
    suspend fun postLists(request: ToDoListRequest): Resource<Failure, UpdateToDoListResponse>
    suspend fun putLists(id: Int, request: ToDoListRequest): Resource<Failure, UpdateToDoListResponse>
    suspend fun deleteLists(id: Int): Resource<Failure, Int>
    //endregion

    //region [Itens]
    suspend fun getToDoItems(id: Int): Resource<Failure, List<ItemModel>>
    suspend fun postItem(id: Int, request: ToDoItemRequest): Resource<Failure, List<ItemModel>>
    suspend fun putItem(id: Int, itemId: Int, request: ToDoItemRequest): Resource<Failure, List<ItemModel>>
    suspend fun deleteItem(id: Int, itemId: Int): Resource<Failure, List<ItemModel>>
    //endregion

    companion object {
        fun create(): ToDoApiService {
            return ToDoApiServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) { level = LogLevel.ALL }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}