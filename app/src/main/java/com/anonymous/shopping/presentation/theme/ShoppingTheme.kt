package com.anonymous.shopping.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val ColorPalette = lightColors(
    primary = Blue700,
    primaryVariant = Blue400,
    secondary = Red400,
)

@Composable
fun ShoppingTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}