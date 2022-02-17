package com.bayu.movieflow.core.extensions

import android.content.Context
import android.view.View
import androidx.core.view.isVisible
import androidx.datastore.preferences.preferencesDataStore
import com.bayu.movieflow.core.constant.Constant

val Context.dataStore by preferencesDataStore(Constant.Preference.NAME)

fun View.hide() {
    this.isVisible = false
}

fun View.visible() {
    this.isVisible = true
}