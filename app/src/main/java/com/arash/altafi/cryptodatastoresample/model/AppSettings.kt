package com.arash.altafi.cryptodatastoresample.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val language: Language = Language.ENGLISH,
    val user: User = User()
)

@Serializable
data class User(
    val username: String? = null,
    val address: String? = null,
    val phone: Long? = null
)

enum class Language {
    ENGLISH,
    GERMAN,
    PERSIAN
}