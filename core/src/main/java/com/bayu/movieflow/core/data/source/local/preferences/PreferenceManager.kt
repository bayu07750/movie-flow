package com.bayu.movieflow.core.data.source.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.bayu.movieflow.core.extensions.dataStore
import com.bayu.movieflow.core.constant.Constant
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext context: Context,
) {

    private val dataStore: DataStore<Preferences> = context.dataStore

    suspend fun setPreferenceInt(key: Preferences.Key<Int>, value: Int) {
        withContext(Dispatchers.IO) {
            dataStore.edit {
                it[key] = value
            }
        }
    }

    fun getPreferenceInt(key: Preferences.Key<Int>): Flow<Int> {
        return dataStore.data.map {
            it[key] ?: 0
        }
    }

    companion object {
        val KEY_SELECTED_GENRE =
            intPreferencesKey(Constant.Preference.KEY_SELECTED_GENRE)
    }

}