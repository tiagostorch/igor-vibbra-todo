package com.igordam.todo.data.datasource

import com.igordam.todo.core.network.Failure
import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.common.ItemModel
import com.igordam.todo.data.model.request.ToDoListRequest
import com.igordam.todo.data.model.response.ToDoListResponse
import com.igordam.todo.data.model.response.UpdateToDoListResponse
import com.igordam.todo.data.service.ToDoApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class MockedToDoListDataSourceImpl: ToDoListDataSource {

    override suspend fun getLists(): Resource<Failure, List<ToDoListResponse>> =
        Resource.Success(
            listOf(
                ToDoListResponse(
                    id = 1,
                    name = "Teste",
                    permalink = "/list/test",
                    itens = listOf(
                        ItemModel(
                            id = 1,
                            item = "todo",
                            order = 2
                        )
                    )
                )
            )
        )

    override suspend fun getListById(id: Int): Resource<Failure, ToDoListResponse> =
        Resource.Success(
            ToDoListResponse(
                id = 1,
                name = "Teste",
                permalink = "/list/test",
                itens = listOf(
                    ItemModel(
                        id = 1,
                        item = "todo",
                        order = 2
                    )
                )
            )
        )

    override suspend fun postLists(
        request: ToDoListRequest
    ): Resource<Failure, UpdateToDoListResponse> =
        Resource.Success(
            UpdateToDoListResponse(
                id = 1,
                name = "Teste",
                permalink = "/list/test"
            )
        )

    override suspend fun putLists(
        id: Int,
        request: ToDoListRequest
    ): Resource<Failure, UpdateToDoListResponse> =
        Resource.Success(
            UpdateToDoListResponse(
                id = 1,
                name = "Teste",
                permalink = "/list/test"
            )
        )

    override suspend fun deleteLists(id: Int): Resource<Failure, Int> =
        Resource.Success(200)
}