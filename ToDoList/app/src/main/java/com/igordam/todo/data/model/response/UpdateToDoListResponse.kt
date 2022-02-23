package com.igordam.todo.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UpdateToDoListResponse(
    val id: Int,
    val name: String,
    val permalink: String
)
