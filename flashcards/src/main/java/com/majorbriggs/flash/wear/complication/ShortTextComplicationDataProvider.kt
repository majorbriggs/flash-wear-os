package com.majorbriggs.flash.wear.complication

import android.app.PendingIntent
import androidx.wear.watchface.complications.data.ComplicationData
import androidx.wear.watchface.complications.data.LongTextComplicationData
import androidx.wear.watchface.complications.data.PlainComplicationText
import androidx.wear.watchface.complications.data.RangedValueComplicationData
import com.majorbriggs.flash.wear.data.FlashCardRepository
import javax.inject.Inject

class LongTextComplicationDataProvider @Inject constructor(
    private val flashCardRepository: FlashCardRepository,
) {

    suspend fun create(pendingIntent: PendingIntent): ComplicationData {
        val wordOfTheDay = flashCardRepository.getWordOfTheDay()
        return LongTextComplicationData.Builder(
            text = PlainComplicationText.Builder("${wordOfTheDay.wordToLearn} - ${wordOfTheDay.translation}").build(),
            contentDescription = PlainComplicationText.Builder("Word of the day").build()
        ).setTapAction(
            pendingIntent
        ).build()
    }

    fun createPreview(): ComplicationData {
        return LongTextComplicationData.Builder(
            text = PlainComplicationText.Builder("Hola - Hello").build(),
            contentDescription = PlainComplicationText.Builder("Word of the day").build(),
        ).build()
    }
}
