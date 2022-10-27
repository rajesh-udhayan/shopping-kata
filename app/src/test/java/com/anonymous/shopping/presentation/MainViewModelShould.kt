package com.anonymous.shopping.presentation

import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.ProductsRepository
import com.anonymous.shopping.utils.BaseUnitTest
import com.anonymous.shopping.utils.getValueForTest
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelShould: BaseUnitTest() {

    private lateinit var viewModel: MainViewModel
    private val repository: ProductsRepository = mockk()
    private val expected = mockk<List<Product>>()

    @Test
    fun getProductsFromRepository() = runTest{
        viewModel = mockSuccessfulResponse()
        viewModel.getProducts()

        coVerify { repository.getProducts() }
    }

    @Test
    fun emitsProductsFromRepository()  = runTest{
        viewModel = mockSuccessfulResponse()
        viewModel.getProducts()

        assertThat(expected).isEqualTo(viewModel.products.getValueForTest())
    }

    @Test
    fun shouldUpdateProgressStateOnceProductListLoaded(){
        viewModel = mockSuccessfulResponse()

        viewModel.getProducts()

        assertThat(viewModel.isLoaded.value).isTrue()
    }

    private fun mockSuccessfulResponse(): MainViewModel {
        runTest {
            coEvery { repository.getProducts() } returns flow{
                emit(expected)
            }
        }
        return MainViewModel(repository)
    }
}