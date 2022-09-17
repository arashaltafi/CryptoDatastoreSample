package com.arash.altafi.cryptodatastoresample.repository

import android.content.Context
import androidx.datastore.dataStore
import com.arash.altafi.cryptodatastoresample.model.AppSettings
import com.arash.altafi.cryptodatastoresample.model.Language
import com.arash.altafi.cryptodatastoresample.model.User
import kotlinx.coroutines.flow.*

class ImplRepository(private val context: Context) : Abstract {

    private val Context.dataStore by dataStore(
        fileName = DataStore_NAME,
        serializer = AppSettingSerializer(CryptoManager())
    )

    companion object {
        const val DataStore_NAME = "app-setting.json"
    }

    override suspend fun saveAppSettings(language: Language, user: User) {
        context.dataStore.updateData {
            it.copy(
                language = language,
                user = user
            )
        }
    }

    override suspend fun getAppSettings(): Flow<AppSettings> {
        return context.dataStore.data.catch {
            emit(AppSettings())
        }
    }

}