package com.rzerocorp.melidemo.data

import com.google.common.truth.Truth.assertThat
import com.rzerocorp.melidemo.data.network.RestApi
import com.rzerocorp.melidemo.data.utils.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MeLiAPIServiceTest {
    private lateinit var service: RestApi
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RestApi::class.java)
    }

    @Test
    fun getSearchedProducts_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("success_products.json")

            val responseBody = service.searchByQuery("nintendo", 1)
            val request = server.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/sites/" +
                    Constants.STORE_ID + "/search?q=nintendo&offset=1&limit=50")
        }
    }

    @Test
    fun getProductByID_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("success_product_by_id.json")

            val responseBody = service.getProductByID("MLM1344785573")
            val request = server.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/items/MLM1344785573")
        }
    }

    @Test
    fun getProductDescription_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("success_product_description.json")

            val responseBody = service.getProductDescription("MLM1344785573")
            val request = server.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/items/MLM1344785573/description")
        }
    }

    @Test
    fun getSeller_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("success_seller.json")

            val responseBody = service.getSellerInfo("188800745")
            val request = server.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/users/188800745")
        }
    }

    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            server.enqueue(mockResponse)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}