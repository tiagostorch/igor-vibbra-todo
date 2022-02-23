package com.igordam.todo.mocks

import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.model.common.ItemModel
import com.igordam.todo.data.model.request.ToDoItemRequest
import com.igordam.todo.data.model.request.ToDoListRequest
import com.igordam.todo.data.model.response.ToDoListResponse
import com.igordam.todo.data.model.response.UpdateToDoListResponse

class Mocks {

    val toDoList = listOf(
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

    val toDoListById = ToDoListResponse(
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

    val postToDoRequest = ToDoListRequest(
        id = 1,
        name = "Teste"
    )

    val postToDoResponse = UpdateToDoListResponse(
        id = 1,
        name = "Teste",
        permalink = "/list/test"
    )

    val itemList = listOf(
        ItemModel(
            id = 1,
            item = "todo",
            order = 2
        )
    )

    val postItemRequest = ToDoItemRequest(
        id = 1,
        item = "Item",
        order = 2
    )

}