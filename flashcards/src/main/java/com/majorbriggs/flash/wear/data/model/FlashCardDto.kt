package com.majorbriggs.flash.wear.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "flashcards")
data class FlashCardDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val wordToLearn: String,
    val translation: String,
    val lastRepetition: Date
)
