package com.anonymous.shopping.presentation.beverage_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anonymous.shopping.commons.Constant.appName
import com.anonymous.shopping.commons.Constant.progressLoaderTag
import com.anonymous.shopping.presentation.theme.ShoppingTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BeverageListFeature {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            ShoppingTheme {
                BeverageListView()
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
}