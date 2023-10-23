package com.majorbriggs.flash.wear.complication

import android.app.PendingIntent
import android.content.Context
import android.graphics.drawable.Icon
import androidx.wear.watchface.complications.data.ComplicationData
import androidx.wear.watchface.complications.data.MonochromaticImage
import androidx.wear.watchface.complications.data.PlainComplicationText
import androidx.wear.watchface.complications.data.RangedValueComplicationData
import androidx.wear.watchface.complications.data.SmallImage
import androidx.wear.watchface.complications.data.SmallImageType
import com.majorbriggs.flash.wear.R
import com.majorbriggs.flash.wear.data.FlashCardRepository
import javax.inject.Inject

class RangeValueComplicationDataProvider @Inject constructor(
    private val flashCardRepository: FlashCardRepository,
) {

    suspend fun create(context: Context, intent: PendingIntent): ComplicationData {
        val flashCartViewed = flashCardRepository.getFlashCardsViewed()
        return RangedValueComplicationData.Builder(
            value = flashCartViewed.toFloat(),
            min = 0.0f,
            max = 30f,
            contentDescription = PlainComplicationText.Builder("Flashcards viewed").build(),
        ).setMonochromaticImage(
            MonochromaticImage.Builder(
                Icon.createWithResource(context, R.drawable.ic_icon),
            ).build()
        )
            .setText(
                PlainComplicationText.Builder(flashCartViewed.toString()).build()
            ).setTapAction(
                intent
            ).build()
    }

    fun createPreview(): ComplicationData {
        return RangedValueComplicationData.Builder(
            value = 10f,
            min = 0.0f,
            max = 30f,
            contentDescription = PlainComplicationText.Builder("Flashcards viewed").build(),
        ).setText(
            PlainComplicationText.Builder("10").build()
        ).build()
    }
}
