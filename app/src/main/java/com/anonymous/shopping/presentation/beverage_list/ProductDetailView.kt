package com.anonymous.shopping.presentation.beverage_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.anonymous.shopping.R
import com.anonymous.shopping.commons.Constant
import com.anonymous.shopping.presentation.MainViewModel

@Composable
fun ProductDetailView(id: String?, navController: NavController, viewModel: MainViewModel) {

    id?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            viewModel.getProductDetail(id)
            val productDetail by viewModel.productDetail.observeAsState()
            productDetail?.let { item ->
                var isFavoriteClicked by remember {
                    mutableStateOf(item.isFavorite)
                }

                Column(
                    Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = {
                            isFavoriteClicked = if (isFavoriteClicked == 1) 0 else 1
                            viewModel.updateFavorite(isFavoriteClicked, item.id)
                        },
                    ) {
                        Row {
                            Image(
                                painter = painterResource(
                                    id = if (isFavoriteClicked == 1) R.drawable.ic_favorite
                                    else R.drawable.ic_favorite_border
                                ),
                                contentDescription = "favorite button",
                                colorFilter = ColorFilter.tint(Color.Red)
                            )
                        }
                    }
                    Image(
                        painter = rememberImagePainter(item.imageURL),
                        modifier = Modifier
                            .size(160.dp)
                            .testTag(Constant.productImageTag),
                        contentDescription = "product image"
                    )
                    Text(
                        text = item.title,
                        modifier = Modifier.testTag(Constant.productTitleTag),
                        style = TextStyle(fontSize = 22.sp)
                    )
                    Text(
                        text = item.saleUnitPrice.toString(),
                        modifier = Modifier.testTag(Constant.productPriceTag),
                        style = TextStyle(fontSize = 18.sp)
                    )

                }
            }
        }
    }
}