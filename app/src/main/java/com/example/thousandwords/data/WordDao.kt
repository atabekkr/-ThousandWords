package com.example.thousandwords.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WordDao {

    @Query("SELECT * FROM QUESTIONS")
    fun getAllWords(): List<Word>

    @Query("SELECT * FROM QUESTIONS WHERE id=:topic_Id")
    fun getQuestion(topic_Id: Int): Word

    @Query("SELECT translation FROM QUESTIONS")
    fun getAllAnswers(): List<String>

    @Query("SELECT question FROM QUESTIONS")
    fun getAllQuestios(): List<String>
}