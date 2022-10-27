package com.anonymous.shopping.presentation.beverage_list

import androidx.activity.viewModels
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.anonymous.shopping.commons.Constant.appName
import com.anonymous.shopping.commons.Constant.beverageListTag
import com.anonymous.shopping.commons.Constant.productImageTag
import com.anonymous.shopping.commons.Constant.productPriceTag
import com.anonymous.shopping.commons.Constant.productTitleTag
import com.anonymous.shopping.commons.Constant.progressLoaderTag
import com.anonymous.shopping.presentation.MainActivity
import com.anonymous.shopping.presentation.MainViewModel
import com.anonymous.shopping.presentation.theme.ShoppingTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BeverageListFeature {

    @get:Rule(order = 1)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp(){
        hiltTestRule.inject()
        composeTestRule.setContent {
            ShoppingTheme {
                val viewModel = composeTestRule.activity.viewModels<MainViewModel>().value
                MainScreenView(viewModel)
            }
        }
    }

    @Test
    fun displayScreenTitle(){
        composeTestRule.onNodeWithText(appName).assertIsDisplayed()
    }

    @Test
    fun displayProgressBar(){
        composeTestRule.onNodeWithTag(progressLoaderTag).assertIsDisplayed()
    }

    @Test
    fun displayFirstItemOfBeverage(){
        with(composeTestRule.onNodeWithTag(beverageListTag)
            .onChildren()
            .onFirst()){
            assert(hasTestTag(productTitleTag))
            assert(hasTestTag(productPriceTag))
            assert(hasTestTag(productImageTag))
        }
    }
}