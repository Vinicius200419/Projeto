package com.receitas.database

import androidx.room.*
import com.receitas.models.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM receitas ORDER BY dataCriacao DESC")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM receitas WHERE id = :id")
    suspend fun getRecipeById(id: Int): Recipe?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe): Long

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("DELETE FROM receitas WHERE id = :id")
    suspend fun deleteRecipeById(id: Int)
}
