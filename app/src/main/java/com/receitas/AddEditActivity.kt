package com.receitas

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.receitas.databinding.ActivityAddEditBinding
import com.receitas.models.Recipe
import com.receitas.viewmodel.RecipeViewModel
import kotlinx.coroutines.launch

class AddEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditBinding
    private val viewModel: RecipeViewModel by viewModels()
    private var recipeId: Int = -1
    private var isEditing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recipeId = intent.getIntExtra("recipe_id", -1)
        isEditing = recipeId != -1

        if (isEditing) {
            binding.toolbarTitle.text = "Editar Receita"
            loadRecipeData(recipeId)
        } else {
            binding.toolbarTitle.text = "Adicionar Receita"
        }

        setupButtons()
    }

    private fun loadRecipeData(id: Int) {
        lifecycleScope.launch {
            val recipe = viewModel.getRecipeById(id)
            recipe?.let {
                with(binding) {
                    recipeName.setText(it.nome)
                    recipeTime.setText(it.tempo.toString())
                    recipeServings.setText(it.serve.toString())
                    recipeDifficulty.setText(it.dificuldade)
                    recipeImage.setText(it.imagem)
                    ingredientsText.setText(it.ingredientes)
                    instructionsText.setText(it.modoPreparo)
                }
            }
        }
    }

    private fun setupButtons() {
        binding.saveBtn.setOnClickListener {
            saveRecipe()
        }

        binding.cancelBtn.setOnClickListener {
            finish()
        }
    }

    private fun saveRecipe() {
        with(binding) {
            val nome = recipeName.text.toString().trim()
            val tempo = recipeTime.text.toString().trim().toIntOrNull() ?: 0
            val serve = recipeServings.text.toString().trim().toIntOrNull() ?: 1
            val dificuldade = recipeDifficulty.text.toString().trim()
            val imagem = recipeImage.text.toString().trim()
            val ingredientes = ingredientsText.text.toString().trim()
            val modoPreparo = instructionsText.text.toString().trim()

            if (nome.isEmpty() || ingredientes.isEmpty() || modoPreparo.isEmpty()) {
                androidx.appcompat.app.AlertDialog.Builder(this@AddEditActivity)
                    .setTitle("Campos Obrigatórios")
                    .setMessage("Por favor, preencha todos os campos obrigatórios.")
                    .setPositiveButton("OK", null)
                    .show()
                return@setupButtons
            }

            val recipe = Recipe(
                id = if (isEditing) recipeId else 0,
                nome = nome,
                tempo = tempo,
                serve = serve,
                dificuldade = dificuldade,
                imagem = imagem,
                ingredientes = ingredientes,
                modoPreparo = modoPreparo
            )

            if (isEditing) {
                viewModel.updateRecipe(recipe)
            } else {
                viewModel.addRecipe(recipe)
            }

            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
