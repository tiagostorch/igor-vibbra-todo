package com.igordam.todo.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ToDoListRequest(
    val id: Int,
    val name: String
)
