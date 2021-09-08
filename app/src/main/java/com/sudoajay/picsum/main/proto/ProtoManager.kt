package com.sudoajay.picsum.main.proto

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.sudoajay.picsum.StatePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProtoManager constructor (var context: Context){

    private var TAG = "ProtoManagerTAG"
    private val Context.stateDataStore: DataStore<StatePreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = StatePreferencesSerializer
    )

    val dataStoreStatePreferences : DataStore<StatePreferences> = context.stateDataStore

    suspend fun setDataBase(isDataBase: Boolean) {
        dataStoreStatePreferences.updateData { preferences ->
            preferences.toBuilder()
                .setDatabase(isDataBase)
                .build()
        }
    }

    suspend fun setJsonConverter(jsonConverter: String)  {
        dataStoreStatePreferences.updateData { preferences ->
            preferences.toBuilder()
                .setJsonConverter(jsonConverter)
                .build()
        }
    }

    companion object {
        private const val DATA_STORE_FILE_NAME = "state_prefs.pb"
    }

}





