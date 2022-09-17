package com.arash.altafi.cryptodatastoresample.repository

import com.arash.altafi.cryptodatastoresample.model.AppSettings
import com.arash.altafi.cryptodatastoresample.model.Language
import com.arash.altafi.cryptodatastoresample.model.User
import kotlinx.coroutines.flow.Flow

interface Abstract {
    suspend fun saveAppSettings(language: Language, user: User)
    suspend fun getAppSettings(): Flow<AppSettings>
}