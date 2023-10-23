package com.majorbriggs.flash.wear.complication

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.wear.watchface.complications.data.ComplicationData
import androidx.wear.watchface.complications.data.ComplicationType
import androidx.wear.watchface.complications.datasource.ComplicationRequest
import androidx.wear.watchface.complications.datasource.SuspendingComplicationDataSourceService
import com.majorbriggs.flash.wear.presentation.FlashWearActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FlashCardComplicationService : SuspendingComplicationDataSourceService() {

    @Inject
    lateinit var rangeValueComplicationDataProvider: RangeValueComplicationDataProvider

    @Inject
    lateinit var longTextComplicationDataProvider: LongTextComplicationDataProvider

    override fun onComplicationActivated(
        complicationInstanceId: Int,
        type: ComplicationType,
    ) {
        Log.d("WordOfTheDayComplication", "onComplicationActivated(): $complicationInstanceId")
    }

    override fun getPreviewData(type: ComplicationType): ComplicationData? {
        Log.d("WordOfTheDayComplication", "getPreviewData()")
        return when (type) {
            ComplicationType.RANGED_VALUE -> rangeValueComplicationDataProvider.createPreview()
            ComplicationType.LONG_TEXT -> longTextComplicationDataProvider.createPreview()
            else -> null
        }
    }

    override suspend fun onComplicationRequest(request: ComplicationRequest): ComplicationData {
        val launchActivityIntent = Intent(this, FlashWearActivity::class.java)

        val activityPendingIntent = PendingIntent.getActivity(
            this, 0, launchActivityIntent, PendingIntent.FLAG_IMMUTABLE
        )
        return when (request.complicationType) {
            ComplicationType.RANGED_VALUE -> rangeValueComplicationDataProvider.create(this, activityPendingIntent)
            ComplicationType.LONG_TEXT -> longTextComplicationDataProvider.create(activityPendingIntent)
            else -> throw IllegalStateException("Not supported complication type: ${request.complicationType}")
        }
    }
}
