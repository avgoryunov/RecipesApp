package ru.avgoryunov.recipesapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.avgoryunov.recipesapp.databinding.FragmentListRecipesBinding

class RecipesListFragment : Fragment() {
    private var _binding: FragmentListRecipesBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentListRecipesBinding must not be null")

    private var categoryId: Int? = null
    private var categoryName: String? = null
    private var categoryImageUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            categoryId = args.getInt(CategoriesListFragment.ARG_CATEGORY_ID)
            categoryName = args.getString(CategoriesListFragment.ARG_CATEGORY_NAME)
            categoryImageUrl = args.getString(CategoriesListFragment.ARG_CATEGORY_IMAGE_URL)
        }

        initUI()
    }

    private fun initUI() {
        binding.tvCategoryTitle.text = categoryName

        val drawable = try {
            categoryImageUrl?.let { imageUrl ->
                requireContext().assets.open(imageUrl)
                    .use { stream -> Drawable.createFromStream(stream, null) }
            }
        } catch (e: Exception) {
            Log.e("!!!", "Ошибка загрузки изображения категории", e)
            null
        }
        binding.ivCategoryImage.setImageDrawable(drawable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}