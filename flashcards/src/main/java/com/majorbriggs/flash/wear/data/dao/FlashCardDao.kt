package com.majorbriggs.flash.wear.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.majorbriggs.flash.wear.data.model.FlashCardDto

@Dao
interface FlashCardDao {

    @Insert
    suspend fun insert(flashCard: FlashCardDto)

    @Insert
    suspend fun insertAll(flashCards: List<FlashCardDto>)

    @Query("SELECT * FROM flashCards ORDER BY lastRepetition DESC")
    suspend fun getAllFlashCards(): List<FlashCardDto>

    @Query("SELECT * FROM flashCards WHERE id = :id")
    suspend fun getFlashCard(id: Int): FlashCardDto
}
