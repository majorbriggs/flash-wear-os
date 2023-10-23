package com.majorbriggs.flash.wear.di

import android.app.Application
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import androidx.wear.tiles.TileService
import androidx.wear.tiles.TileUpdateRequester
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceUpdateRequester
import com.majorbriggs.flash.wear.complication.FlashCardComplicationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FlashWearModule {

    @Provides
    fun provideNotificationManager(application: Application): NotificationManager {
        return application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @Provides
    fun provideTileUpdateRequester(application: Application): TileUpdateRequester {
        return TileService.getUpdater(application.applicationContext)
    }

    @Provides
    fun provideComplicationUpdateRequester(application: Application): ComplicationDataSourceUpdateRequester {
        return ComplicationDataSourceUpdateRequester.create(
            application.applicationContext,
            ComponentName(application.applicationContext, FlashCardComplicationService::class.java)
        )
    }
}
