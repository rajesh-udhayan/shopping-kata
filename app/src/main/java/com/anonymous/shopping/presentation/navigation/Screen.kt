package com.anonymous.shopping.presentation.navigation

import com.anonymous.shopping.R

sealed class Screen(var title: String, var icon: Int, var screen_route: String)
{
    object Home: Screen("Home", R.drawable.ic_home,"home")
    object Favorite: Screen("Favorite", R.drawable.ic_favorite,"favorite")
    object ProductDetail: Screen("ProductDetail",0,"productDetail")

    /**
     * Helper to pass arguments with navigation
     */
    fun withArgs(vararg args: String): String {
        return buildString {
            append(screen_route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}