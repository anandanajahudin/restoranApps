package com.racodex.melly.screens.bookmarks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.racodex.melly.models.Product
import com.racodex.melly.repositories.ProductsRepository
import com.racodex.melly.sealed.UiState
import com.racodex.melly.utils.getStructuredBookmarkItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val productRepository: ProductsRepository,
) : ViewModel() {
    private val _bookmarkState = mutableStateOf<UiState>(UiState.Loading)
    val bookmarkState: State<UiState> = _bookmarkState
    val bookmarkItems: MutableList<Product> = mutableStateListOf()

    init {
        viewModelScope.launch {
            /** Getting Bookmark items when this view model is created  */
            getBookmarkItems()
        }
    }

    private suspend fun getBookmarkItems() {
        if (bookmarkItems.isNotEmpty()) return

        _bookmarkState.value = UiState.Loading
        productRepository.getLocalBookmarks().distinctUntilChanged().collect {
            it.getStructuredBookmarkItems().let { updates ->
                if (updates.isEmpty() || updates.any { product -> product in bookmarkItems }) {
                    /** There are a bookmark products, clear it */
                    bookmarkItems.clear()
                }
                _bookmarkState.value = UiState.Success
                bookmarkItems.addAll(updates)
            }
        }
    }
}