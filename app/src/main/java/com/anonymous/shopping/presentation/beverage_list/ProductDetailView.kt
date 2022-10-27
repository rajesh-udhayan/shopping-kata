package com.anonymous.shopping.presentation.beverage_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    IconButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = {
                            isFavoriteClicked = if (isFavoriteClicked == 1) 0 else 1
                            viewModel.updateFavorite(isFavoriteClicked, item.id)
                        },
                    ) {
                            Image(
                                painter = painterResource(
                                    id = if (isFavoriteClicked == 1) R.drawable.ic_favorite
                                    else R.drawable.ic_favorite_border
                                ),
                                contentDescription = "favorite button",
                                colorFilter = ColorFilter.tint(Color.Red),
                            )
                    }
                    Image(
                        painter = rememberImagePainter(item.imageURL),
                        modifier = Modifier
                            .size(160.dp)
                            .testTag(Constant.productImageTag)
                            .align(Alignment.CenterHorizontally),
                        contentDescription = "product image"
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = item.title,
                        modifier = Modifier.testTag(Constant.productTitleTag),
                        style = TextStyle(fontSize = 22.sp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Price: $ ${item.saleUnitPrice.toString()}",
                        modifier = Modifier.testTag(Constant.productPriceTag),
                        style = TextStyle(fontSize = 18.sp)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    RatingBar(rating = item.ratingCount.toFloat(), spaceBetween = 3.dp)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "Rating: ${item.ratingCount.toString()}",
                        modifier = Modifier.testTag(Constant.productRatingTag),
                        style = TextStyle(fontSize = 18.sp)
                    )
                }
            }
        }
    }
}