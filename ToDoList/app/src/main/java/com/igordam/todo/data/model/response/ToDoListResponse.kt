package com.igordam.todo.data.model.response

import com.igordam.todo.data.model.common.ItemModel
import kotlinx.serialization.Serializable

@Serializable
data class ToDoListResponse(
    val id: Int,
    val name: String,
    val permalink: String,
    val itens: List<ItemModel>
)
