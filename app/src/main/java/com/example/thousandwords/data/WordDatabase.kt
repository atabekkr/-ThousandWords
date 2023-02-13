package com.example.thousandwords.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 3)
abstract class WordDatabase: RoomDatabase() {

    companion object {
        private var instance: WordDatabase? = null

        fun getInstance(context: Context): WordDatabase {
            instance?.let {
                return it
            }

            val db = Room.databaseBuilder(context, WordDatabase::class.java, "mock_data_base.db")
                .createFromAsset("mock_data_base.db")
                .allowMainThreadQueries()
                .build()

            instance = db
            return db
        }
    }

    abstract fun getWordDao(): WordDao
}