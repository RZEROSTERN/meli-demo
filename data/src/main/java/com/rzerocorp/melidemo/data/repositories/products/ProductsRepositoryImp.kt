package com.rzerocorp.melidemo.data.repositories.products

import com.rzerocorp.melidemo.data.models.Product
import com.rzerocorp.melidemo.data.models.ProductDescription
import com.rzerocorp.melidemo.data.models.Seller
import com.rzerocorp.melidemo.data.models.SellerAddress
import com.rzerocorp.melidemo.data.models.responses.SearchResponse
import com.rzerocorp.melidemo.data.network.RestApi
import javax.inject.Inject

class ProductsRepositoryImp @Inject constructor(private val restApi: RestApi): ProductsRepository {
    override suspend fun searchByQuery(query: String, offset: Int): SearchResponse =
        restApi.searchByQuery(query, offset)

    override suspend fun getProductById(id: String): Product = restApi.getProductByID(id)

    override suspend fun getProductDescription(id: String): ProductDescription =
        restApi.getProductDescription(id)
    override suspend fun getSellerAddress(id: String): Seller = restApi.getSellerInfo(id)
}