package com.igordam.todo.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ToDoItemRequest(
    val id: Int,
    val item: String,
    val order: Int
)
