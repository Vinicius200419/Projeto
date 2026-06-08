package com.receitas.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receitas")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val tempo: Int,
    val serve: Int,
    val dificuldade: String,
    val imagem: String = "",
    val ingredientes: String,
    val modoPreparo: String,
    val dataCriacao: Long = System.currentTimeMillis()
)
