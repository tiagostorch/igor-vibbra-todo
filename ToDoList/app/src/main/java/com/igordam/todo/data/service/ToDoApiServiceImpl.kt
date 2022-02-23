package com.igordam.todo.data.service

import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.common.ItemModel
import com.igordam.todo.data.model.request.ToDoItemRequest
import com.igordam.todo.data.model.request.ToDoListRequest
import com.igordam.todo.data.model.response.ToDoListResponse
import com.igordam.todo.data.model.response.UpdateToDoListResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class ToDoApiServiceImpl(
    private val client: HttpClient
) : ToDoApiService {

    //region [Lists]
    override suspend fun getLists(): Resource<Failure, List<ToDoListResponse>> {
        return try {
            Resource.Success(
                client.get { url(HttpRoutes.BASE_URL) }
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ResponseException) {
            // 6xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        }
    }

    override suspend fun getListById(id: Int): Resource<Failure, ToDoListResponse> {
        return try {
            Resource.Success(
                client.get { url(
                    "${HttpRoutes.BASE_URL}/$id"
                ) }
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ResponseException) {
            // 6xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        }
    }

    override suspend fun postLists(request: ToDoListRequest): Resource<Failure, UpdateToDoListResponse> {
        return try {
            Resource.Success(
                client.post<UpdateToDoListResponse> {
                    url(HttpRoutes.BASE_URL)
                    body = request
                }
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ResponseException) {
            // 6xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        }
    }

    override suspend fun putLists(id: Int, request: ToDoListRequest): Resource<Failure, UpdateToDoListResponse> {
        return try {
            Resource.Success(
                client.post<UpdateToDoListResponse> {
                    url("${HttpRoutes.BASE_URL}/$id")
                    body = request
                }
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ResponseException) {
            // 6xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        }
    }

    override suspend fun deleteLists(id: Int): Resource<Failure, Int> {
        return try {
            Resource.Success(
                client.delete {
                    url("${HttpRoutes.BASE_URL}/$id")
                }
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ResponseException) {
            // 6xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        }
    }
    //endregion

    //region [Itens]
    override suspend fun getToDoItems(id: Int): Resource<Failure, List<ItemModel>> {
        return try {
            Resource.Success(
                client.get { url(
                    "${HttpRoutes.BASE_ITEMS_URL}/$id"
                ) }
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ResponseException) {
            // 6xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        }
    }

    override suspend fun postItem(
        id: Int,
        request: ToDoItemRequest
    ): Resource<Failure, List<ItemModel>> {
        return try {
            Resource.Success(
                client.post<List<ItemModel>> {
                    url("${HttpRoutes.BASE_ITEMS_URL}/$id")
                    body = request
                }
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ResponseException) {
            // 6xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        }
    }

    override suspend fun putItem(
        id: Int,
        itemId: Int,
        request: ToDoItemRequest
    ): Resource<Failure, List<ItemModel>> {
        return try {
            Resource.Success(
                client.post<List<ItemModel>> {
                    url("${HttpRoutes.BASE_URL}/$id/$itemId")
                    body = request
                }
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ResponseException) {
            // 6xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        }
    }

    override suspend fun deleteItem(id: Int, itemId: Int): Resource<Failure, List<ItemModel>> {
        return try {
            Resource.Success(
                client.delete {
                    url("${HttpRoutes.BASE_URL}/$id/$itemId")
                }
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ResponseException) {
            // 6xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        }
    }
    //endregion
}