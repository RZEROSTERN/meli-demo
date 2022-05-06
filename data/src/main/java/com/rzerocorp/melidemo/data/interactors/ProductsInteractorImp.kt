package com.rzerocorp.melidemo.data.interactors

import com.rzerocorp.melidemo.data.models.responses.SearchResponse
import com.rzerocorp.melidemo.data.repositories.products.ProductsRepositoryImp
import javax.inject.Inject

class ProductsInteractorImp @Inject constructor(
    private val productsRepositoryImp: ProductsRepositoryImp
    ): ProductsInteractor {

    override suspend fun searchByQuery(): SearchResponse =
        productsRepositoryImp.searchByQuery()
}