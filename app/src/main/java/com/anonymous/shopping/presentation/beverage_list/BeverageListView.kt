package com.anonymous.shopping.presentation.beverage_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.anonymous.shopping.presentation.common_views.TopBar

@Composable
fun BeverageListView() {
    Scaffold(
        topBar = { TopBar() },
    ) {
        Column {
            CircularProgressIndicator(
                modifier = Modifier.testTag("progress_loader_tag")
            )
        }
    }
}