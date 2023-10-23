package com.majorbriggs.flash.wear.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.majorbriggs.flash.wear.data.dao.FlashCardDao
import com.majorbriggs.flash.wear.domain.FlashCard
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlashCardRepository @Inject constructor(
    private val flashCardDao: FlashCardDao,
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun getAllFlashCards(): List<FlashCard> = flashCardDao.getAllFlashCards()
        .map {
            FlashCard(
                wordToLearn = it.wordToLearn,
                translation = it.translation,
            )
        }

    suspend fun increaseDailyCounter() {
        dataStore.edit { prefs ->
            val newValue = ((prefs[DAILY_FLASHCARDS_VIEWED] ?: 0) + 1) % 30
            // Hack until the daily counter reset is implemented
            prefs[DAILY_FLASHCARDS_VIEWED] = newValue
        }
    }

    suspend fun saveWordOfTheDay(wordToLearn: String, translation: String) {
        dataStore.edit {
            it[WORD_OF_THE_DAY_KEY] = wordToLearn
            it[WORD_OF_THE_DAY_TRANSLATION] = translation
        }
    }

    fun getFlashCardsViewed(): Int =
        runBlocking { dataStore.data.map { it[DAILY_FLASHCARDS_VIEWED] ?: 0 }.first() }

    suspend fun getWordOfTheDay(): FlashCard {
        return dataStore.data.map {
            val word = it[WORD_OF_THE_DAY_KEY] ?: "Select your favorite flashcard!"
            val translation = it[WORD_OF_THE_DAY_TRANSLATION] ?: ""
            FlashCard(word, translation)
        }.first()
    }
}
