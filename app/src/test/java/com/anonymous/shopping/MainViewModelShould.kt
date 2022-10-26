package com.anonymous.shopping

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anonymous.shopping.data.repository.ProductsRepository
import com.anonymous.shopping.presentation.MainViewModel
import com.anonymous.shopping.utils.MainCoroutineScopeRule
import com.anonymous.shopping.utils.getValueForTest
import io.mockk.*
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

    @Test
    fun getProductsFromRepository(){
        every { repository.getProducts() } just Runs
        viewModel = MainViewModel(repository)
        viewModel.productList.getValueForTest()

        verify { repository.getProducts() }
    }
}