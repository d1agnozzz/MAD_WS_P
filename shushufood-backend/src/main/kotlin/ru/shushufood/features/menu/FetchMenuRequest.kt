package ru.shushufood.features.menu

import kotlinx.serialization.Serializable

@Serializable
data class FetchMenuRequest(
    val searchQuery: String
)
