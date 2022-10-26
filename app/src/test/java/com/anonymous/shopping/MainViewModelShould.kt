package com.anonymous.shopping

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.ProductsRepository
import com.anonymous.shopping.presentation.MainViewModel
import com.anonymous.shopping.utils.MainCoroutineScopeRule
import com.anonymous.shopping.utils.getValueForTest
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.times


class MainViewModelShould {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private val repository: ProductsRepository = mockk()
    private val products = mockk<List<Product>>()
    private val expected = Result.success(products)

    @Test
    fun getProductsFromRepository() = runBlocking{
        every { repository.getProducts() } returns flow {
            emit(expected)
        }
        viewModel = MainViewModel(repository)
        viewModel.productList.getValueForTest()

        verify { repository.getProducts() }
    }

    @Test
    fun emitsProductsFromRepository()  = runBlocking{
        every { repository.getProducts() } returns flow {
            emit(expected)
        }
        viewModel = MainViewModel(repository)

        assertThat(expected).isEqualTo(viewModel.productList.getValueForTest())
    }
}