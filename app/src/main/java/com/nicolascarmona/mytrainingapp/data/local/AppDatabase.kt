package com.nicolascarmona.mytrainingapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicolascarmona.mytrainingapp.data.model.HistorialEjercicio

@Database(entities = [HistorialEjercicio::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun historialDao(): HistorialDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "entrenamiento_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}