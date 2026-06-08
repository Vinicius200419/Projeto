package com.receitas

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.receitas.databinding.ActivityDetailBinding
import com.receitas.viewmodel.RecipeViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recipeId = intent.getIntExtra("recipe_id", -1)
        if (recipeId != -1) {
            loadRecipeDetails(recipeId)
        }
    }

    private fun loadRecipeDetails(recipeId: Int) {
        lifecycleScope.launch {
            val recipe = viewModel.getRecipeById(recipeId)
            recipe?.let {
                with(binding) {
                    toolbarTitle.text = it.nome
                    recipeName.text = it.nome
                    recipeTime.text = "${it.tempo} minutos"
                    recipeServings.text = "${it.serve} porções"
                    recipeDifficulty.text = it.dificuldade

                    if (it.imagem.isNotEmpty()) {
                        Picasso.get()
                            .load(it.imagem)
                            .fit()
                            .centerCrop()
                            .into(recipeImage)
                    }

                    ingredientsText.text = it.ingredientes
                    instructionsText.text = it.modoPreparo
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
