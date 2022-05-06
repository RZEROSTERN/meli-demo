package com.rzerocorp.melidemo.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rzerocorp.melidemo.data.interactors.ProductsInteractorImp
import com.rzerocorp.melidemo.data.models.responses.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productsInteractor: ProductsInteractorImp) : ViewModel() {
    private val resultMutable: MutableLiveData<SearchResponse> = MutableLiveData()
    val result: LiveData<SearchResponse> get() = resultMutable

    fun fetchProducts(query: String) {
        viewModelScope.launch {
            resultMutable.postValue(productsInteractor.searchByQuery(query))
        }
    }
}