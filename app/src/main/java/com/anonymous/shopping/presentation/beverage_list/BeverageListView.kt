package com.anonymous.shopping.presentation.beverage_list

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.anonymous.shopping.R

@Composable
fun BeverageListView() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        }
    )
}