package com.sudoajay.picsum.main.proto

import android.content.Context
import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.sudoajay.picsum.R
import com.sudoajay.picsum.StatePreferences
import dagger.Provides
import kotlinx.coroutines.flow.catch
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class ProtoManager constructor (var context: Context){


    private val Context.stateDataStore: DataStore<StatePreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = StatePreferencesSerializer
    )
    private val dataStoreStatePreferences : DataStore<StatePreferences> = context.stateDataStore



    fun getDatabase(): Flow<Boolean> {
        return  dataStoreStatePreferences.data.map { protoBuilder ->
            protoBuilder.database
        }
    }

    suspend fun setDatabase(isDarkMode: Boolean?) {
        val value =isDarkMode ?: false
        dataStoreStatePreferences.updateData { preferences ->
            preferences.toBuilder()
                .setDatabase(value)
                .build()
        }
    }

    fun getJsonConverter(): Flow<String> {
        return  dataStoreStatePreferences.data.map { protoBuilder ->
            protoBuilder.jsonConverter
        }
    }

    suspend fun setJsonConverter(jsonConverter: String?) {
        val value =jsonConverter ?: context.getString(R.string.jacksonJson_text)

        dataStoreStatePreferences.updateData { preferences ->
            preferences.toBuilder()
                .setJsonConverter(value)
                .build()
        }
    }

    companion object {
        private const val DATA_STORE_FILE_NAME = "state_prefs.pb"
    }

}





