package com.receitas

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.receitas.adapter.RecipeAdapter
import com.receitas.databinding.ActivityMainBinding
import com.receitas.viewmodel.RecipeViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: RecipeViewModel by viewModels()
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        observeRecipes()
    }

    private fun setupUI() {
        adapter = RecipeAdapter(
            onItemClick = { recipe ->
                startDetailActivity(recipe.id)
            },
            onEditClick = { recipe ->
                startEditActivity(recipe.id)
            },
            onDeleteClick = { recipe ->
                showDeleteConfirmation(recipe)
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        binding.addBtn.setOnClickListener {
            startAddActivity()
        }
    }

    private fun observeRecipes() {
        lifecycleScope.launch {
            viewModel.recipes.collect { recipes ->
                if (recipes.isEmpty()) {
                    binding.emptyView.visibility = android.view.View.VISIBLE
                    binding.recyclerView.visibility = android.view.View.GONE
                } else {
                    binding.emptyView.visibility = android.view.View.GONE
                    binding.recyclerView.visibility = android.view.View.VISIBLE
                    adapter.submitList(recipes)
                }
            }
        }
    }

    private fun startAddActivity() {
        startActivity(Intent(this, AddEditActivity::class.java))
    }

    private fun startEditActivity(id: Int) {
        val intent = Intent(this, AddEditActivity::class.java)
        intent.putExtra("recipe_id", id)
        startActivity(intent)
    }

    private fun startDetailActivity(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("recipe_id", id)
        startActivity(intent)
    }

    private fun showDeleteConfirmation(recipe: com.receitas.models.Recipe) {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Confirmar Exclusão")
            .setMessage("Deseja excluir \"${recipe.nome}\"?")
            .setPositiveButton("Sim") { _, _ ->
                viewModel.deleteRecipe(recipe)
            }
            .setNegativeButton("Não", null)
            .show()
    }
}
