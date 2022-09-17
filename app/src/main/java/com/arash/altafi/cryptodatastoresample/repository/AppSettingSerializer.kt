package com.arash.altafi.cryptodatastoresample.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.Serializer
import com.arash.altafi.cryptodatastoresample.model.AppSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@RequiresApi(Build.VERSION_CODES.M)
class AppSettingSerializer(private val cryptoManager: CryptoManager) : Serializer<AppSettings> {

    override val defaultValue: AppSettings
        get() = AppSettings()

    override suspend fun readFrom(input: InputStream): AppSettings {
        val decryptedBytes = cryptoManager.decrypt(input)
        return try {
            Json.decodeFromString(
                deserializer = AppSettings.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppSettings, output: OutputStream) {
        cryptoManager.encrypt(
            bytes = Json.encodeToString(
                serializer = AppSettings.serializer(),
                value = t
            ).encodeToByteArray(),
            outputStream = output
        )
    }

}