package com.rzerocorp.melidemo.data.interactors

import com.rzerocorp.melidemo.data.models.Product
import com.rzerocorp.melidemo.data.models.responses.SearchResponse
import com.rzerocorp.melidemo.data.repositories.products.ProductsRepositoryImp
import javax.inject.Inject

class ProductsInteractorImp @Inject constructor(
    private val productsRepositoryImp: ProductsRepositoryImp
    ): ProductsInteractor {

    override suspend fun searchByQuery(query: String, offset: Int): SearchResponse =
        productsRepositoryImp.searchByQuery(query, offset)

    override suspend fun getProductByID(id: String): Product {
        val product = productsRepositoryImp.getProductById(id)
        product.description = productsRepositoryImp.getProductDescription(id)
        product.seller = productsRepositoryImp.getSellerAddress(product.seller_id.toString())
        return product
    }
}