package ru.avgoryunov.recipesapp

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.avgoryunov.recipesapp.databinding.ItemCategoryBinding

class CategoriesListAdapter(private val dataSet: List<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCategoryBinding.bind(itemView)
        val imageView: ImageView = binding.ivCategoryImage
        val titleTextView: TextView = binding.tvCategoryTitle
        val descriptionTextView: TextView = binding.tvCategoryDescription
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        val category = dataSet[position]
        viewHolder.titleTextView.text = category.title
        viewHolder.descriptionTextView.text = category.description

        val drawable = try {
            Drawable.createFromStream(
                viewHolder.itemView.context.assets.open(category.imageUrl),
                null
            )
        } catch (e: Exception) {
            Log.e("!!!", "Произошла ошибка", e)
            null
        }
        viewHolder.imageView.setImageDrawable(drawable)
    }

    override fun getItemCount() = dataSet.size
}