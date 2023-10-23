package com.majorbriggs.flash.wear.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.majorbriggs.flash.wear.data.dao.FlashCardDao
import com.majorbriggs.flash.wear.data.db.converter.DateConverter
import com.majorbriggs.flash.wear.data.model.FlashCardDto
import com.majorbriggs.flash.wear.data.static.WordBank
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

@Database(entities = [FlashCardDto::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class FlashDatabase : RoomDatabase() {

    abstract fun flashCardDao(): FlashCardDao

    companion object {

        @Volatile
        private var INSTANCE: FlashDatabase? = null

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                prepopulateDatabase(INSTANCE!!)
            }
        }

        private fun prepopulateDatabase(db: FlashDatabase) {
            val dao = db.flashCardDao()
            val flashCards = WordBank.allWords.map {
                FlashCardDto(
                    wordToLearn = it.wordToLearn,
                    translation = it.translation,
                    lastRepetition = Date.from(Instant.ofEpochMilli(0))
                )
            }
            // Use Kotlin Coroutines or any other background method to insert
            CoroutineScope(Dispatchers.IO).launch {
                dao.insertAll(flashCards)
            }
        }

        fun getDatabase(context: Context): FlashDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlashDatabase::class.java,
                    "flash_database"
                ).addCallback(roomCallback).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
