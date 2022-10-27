package com.anonymous.shopping.presentation.navigation

import com.anonymous.shopping.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String)
{
    object Home: BottomNavItem("Home", R.drawable.ic_home,"home")
    object Favorite: BottomNavItem("Favorite", R.drawable.ic_favorite,"favorite")
}