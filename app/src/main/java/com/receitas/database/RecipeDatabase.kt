package com.receitas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.receitas.models.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var instance: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "receitas.db"
                ).build()
                instance = db
                db
            }
        }
    }
}
