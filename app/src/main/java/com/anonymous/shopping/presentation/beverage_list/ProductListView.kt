package com.anonymous.shopping.presentation.beverage_list

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.anonymous.shopping.R
import com.anonymous.shopping.commons.Constant.beverageListTag
import com.anonymous.shopping.commons.Constant.noProductTag
import com.anonymous.shopping.commons.Constant.productImageTag
import com.anonymous.shopping.commons.Constant.productPriceTag
import com.anonymous.shopping.commons.Constant.productTitleTag
import com.anonymous.shopping.commons.Constant.progressLoaderTag
import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.presentation.MainViewModel
import com.anonymous.shopping.presentation.navigation.Screen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductListView(navController: NavController,
                    viewModel: MainViewModel, productList: List<Product>?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                })
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()
            ) {
            productList?.let { products ->
                if(products.isEmpty()){
                    Text(text = "No products available",
                        modifier = Modifier.testTag(noProductTag)
                            .align(Alignment.Center),
                        style = TextStyle(fontSize = 22.sp))
                } else {
                    LazyColumn(
                        modifier = Modifier.testTag(beverageListTag),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
                    ) {

                        items(items = products, itemContent = { item ->
                            var isFavoriteClicked by remember {
                                mutableStateOf(item.isFavorite)
                            }

                            Card(modifier = Modifier.fillMaxWidth(),
                                elevation = 4.dp) {
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clickable {
                                            navController.navigate(
                                                Screen.ProductDetail.withArgs(
                                                    item.id
                                                )
                                            )
                                        },
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
                                            .testTag(productImageTag),
                                        contentDescription = "product image"
                                    )
                                    Text(
                                        text = item.title,
                                        modifier = Modifier.testTag(productTitleTag),
                                        style = TextStyle(fontSize = 20.sp),
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "Price: $ ${item.saleUnitPrice.toString()}",
                                        modifier = Modifier.testTag(productPriceTag),
                                        style = TextStyle(fontSize = 18.sp)
                                    )

                                    Row {
                                        val context = LocalContext.current
                                        Button(onClick = {
                                            Toast.makeText(context, "Feature in progress", Toast.LENGTH_LONG).show()
                                        }) {
                                            Text(text = "Add to cart")
                                        }
                                    }
                                }
                            }
                        })
                    }
                }
            }
                if (!viewModel.isLoaded.value) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .testTag(progressLoaderTag)
                            .align(Alignment.Center)
                    )
                }
        }
    }
}