package com.receitas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.receitas.database.RecipeDatabase
import com.receitas.models.Recipe
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()

    val recipes = recipeDao.getAllRecipes()

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeDao.insertRecipe(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeDao.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeDao.deleteRecipe(recipe)
        }
    }

    fun deleteRecipeById(id: Int) {
        viewModelScope.launch {
            recipeDao.deleteRecipeById(id)
        }
    }

    suspend fun getRecipeById(id: Int): Recipe? {
        return recipeDao.getRecipeById(id)
    }
}
