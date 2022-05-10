package com.rzerocorp.melidemo.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rzerocorp.melidemo.data.interactors.ProductsInteractorImp
import com.rzerocorp.melidemo.data.models.Product
import com.rzerocorp.melidemo.data.models.responses.SearchResponse
import com.rzerocorp.melidemo.data.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productsInteractor: ProductsInteractorImp) : ViewModel() {
    private val resultMutable: MutableLiveData<SearchResponse> = MutableLiveData()
    val result: LiveData<SearchResponse> get() = resultMutable

    private val productMutable: MutableLiveData<Product> = MutableLiveData()
    val product: LiveData<Product> get() = productMutable

    fun fetchProducts2(query: String): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(Constants.PAGE_LIMIT, Constants.PAGE_LIMIT),
            pagingSourceFactory = {ProductsDataSource(productsInteractor, query)}
        ).flow.cachedIn(viewModelScope)
    }

    fun getProductByID(id: String) {
        viewModelScope.launch {
            productMutable.postValue(productsInteractor.getProductByID(id))
        }
    }
}