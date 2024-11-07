package com.coding.kotlin_soundspace.service.model

data class User (
    val id: String = "",
    val email: String = "",
    val provider: String = "",
    val displayName: String = "",
    val isAnonymous: Boolean = true
)