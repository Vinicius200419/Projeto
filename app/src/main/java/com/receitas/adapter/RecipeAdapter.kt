package com.receitas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.receitas.models.Recipe
import com.receitas.databinding.ItemRecipeBinding
import com.squareup.picasso.Picasso

class RecipeAdapter(
    private val onItemClick: (Recipe) -> Unit,
    private val onEditClick: (Recipe) -> Unit,
    private val onDeleteClick: (Recipe) -> Unit
) : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding, onItemClick, onEditClick, onDeleteClick)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecipeViewHolder(
        private val binding: ItemRecipeBinding,
        private val onItemClick: (Recipe) -> Unit,
        private val onEditClick: (Recipe) -> Unit,
        private val onDeleteClick: (Recipe) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            with(binding) {
                recipeName.text = recipe.nome
                recipeTime.text = "${recipe.tempo} min"
                recipeServings.text = "Porções: ${recipe.serve}"
                recipeDifficulty.text = "Dificuldade: ${recipe.dificuldade}"

                // Carregar imagem
                if (recipe.imagem.isNotEmpty()) {
                    Picasso.get()
                        .load(recipe.imagem)
                        .fit()
                        .centerCrop()
                        .into(recipeImage)
                }

                root.setOnClickListener { onItemClick(recipe) }
                editBtn.setOnClickListener { onEditClick(recipe) }
                deleteBtn.setOnClickListener { onDeleteClick(recipe) }
            }
        }
    }

    class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}
