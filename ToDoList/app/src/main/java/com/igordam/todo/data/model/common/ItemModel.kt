package com.igordam.todo.data.model.common

import kotlinx.serialization.Serializable

@Serializable
data class ItemModel(
    val id: Int,
    val item: String? = null,
    val itens: List<ItemModel>? = null,
    val order: Int
)
