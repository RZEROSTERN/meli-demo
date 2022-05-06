package com.rzerocorp.melidemo.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rzerocorp.melidemo.data.interactors.ProductsInteractorImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productsInteractor: ProductsInteractorImp) : ViewModel() {
    fun fetchProducts() {
        viewModelScope.launch {
            productsInteractor.searchByQuery()
        }
    }
}