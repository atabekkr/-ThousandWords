package com.example.thousandwords.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Word(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "question")val word: String?,
    val translation: String
)
