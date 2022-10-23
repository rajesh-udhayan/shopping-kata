package com.anonymous.shopping.presentation.beverage_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anonymous.shopping.presentation.theme.ShoppingTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BeverageListFeature {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayScreenTitle(){
        composeTestRule.setContent {
            ShoppingTheme {
                BeverageListView()
            }
        }
        with(composeTestRule){
            onNodeWithText("Shopping").assertIsDisplayed()
        }
    }
}