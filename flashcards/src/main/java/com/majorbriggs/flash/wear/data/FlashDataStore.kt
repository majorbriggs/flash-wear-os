/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.majorbriggs.flash.wear.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

/**
 * Simple data storage. Provides DataStore and single preference key to save data.
 */
const val PREFERENCES_FILENAME = "flash_data_store"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_FILENAME)
val WORD_OF_THE_DAY_KEY = stringPreferencesKey("word_of_the_day")
val WORD_OF_THE_DAY_TRANSLATION = stringPreferencesKey("word_of_the_day_translation")
val DAILY_FLASHCARDS_VIEWED = intPreferencesKey("daily_flashcards_viewed")
