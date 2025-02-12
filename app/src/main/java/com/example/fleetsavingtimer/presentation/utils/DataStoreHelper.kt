package com.example.fleetsavingtimer.presentation.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


/***
 * TODO: Move this class to data layer and create usecases on domain layer
 */
object DataStoreHelper {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private fun getKey(key: String) = stringPreferencesKey(key)

    suspend fun save(context: Context, key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[getKey(key)] = value
        }
    }

    suspend fun get(context: Context, key: String): String? {
        return context.dataStore.data.first()[getKey(key)]
    }
}