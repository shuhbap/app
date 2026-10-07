package com.zolvex.studio.ui.templates

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zolvex.studio.data.model.Template
import com.zolvex.studio.data.repository.LocalTemplateRepository
import com.zolvex.studio.data.repository.TemplateRepository

class TemplatesViewModel : ViewModel() {

    private val repository: TemplateRepository = LocalTemplateRepository()

    val allTemplates: List<Template> = repository.getTemplates()
    val categories: List<String> = listOf("All") + repository.getCategories()

    var query by mutableStateOf("")
        private set
    var selectedCategory by mutableStateOf("All")
        private set
    var favorites by mutableStateOf(emptySet<String>())
        private set

    val filteredTemplates: List<Template>
        get() = allTemplates.filter { t ->
            (selectedCategory == "All" || t.category == selectedCategory) &&
                (query.isBlank() ||
                    t.name.contains(query, ignoreCase = true) ||
                    t.category.contains(query, ignoreCase = true))
        }

    fun onQueryChange(value: String) { query = value }

    fun onCategorySelected(category: String) { selectedCategory = category }

    fun toggleFavorite(id: String) {
        favorites = if (id in favorites) favorites - id else favorites + id
    }

    fun templateById(id: String): Template? = repository.getTemplate(id)
}
