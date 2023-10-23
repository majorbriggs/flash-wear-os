package com.majorbriggs.flash.wear.data.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.majorbriggs.flash.wear.data.dao.FlashCardDao
import com.majorbriggs.flash.wear.data.dataStore
import com.majorbriggs.flash.wear.data.db.FlashDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideFlashDatabase(application: Application): FlashDatabase {
        return FlashDatabase.getDatabase(application)
    }

    @Provides
    fun provideFlashCardDao(sessionDatabase: FlashDatabase): FlashCardDao {
        return sessionDatabase.flashCardDao()
    }

    @Provides
    @Singleton
    fun provideDataStore(application: Application): DataStore<Preferences> {
        return application.dataStore
    }
}
