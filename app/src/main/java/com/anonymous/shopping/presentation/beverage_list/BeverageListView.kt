package com.anonymous.shopping.presentation.beverage_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.anonymous.shopping.R
import com.anonymous.shopping.commons.Constant.beverageListTag
import com.anonymous.shopping.commons.Constant.productImageTag
import com.anonymous.shopping.commons.Constant.progressLoaderTag
import com.anonymous.shopping.presentation.MainViewModel
import com.anonymous.shopping.presentation.common_views.TopBar
import com.anonymous.shopping.presentation.theme.Red400

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BeverageListView(viewModel: MainViewModel) {
    Scaffold(
        topBar = { TopBar() },
    ) {
        val productList by viewModel.productList.observeAsState()
        Box {
            productList?.let { products ->
                LazyColumn(modifier = Modifier.testTag(beverageListTag),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)) {

                    items(items = products, itemContent = { item ->
                        var isFavoriteClicked by remember {
                            mutableStateOf(item.isFavorite)
                        }

                        Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                            Column(
                                Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Image(
                                    painter = rememberImagePainter(item.imageURL),
                                    modifier = Modifier.size(160.dp)
                                        .testTag(productImageTag),
                                    contentDescription = "product image"
                                )
                                Text(text = item.title, style = TextStyle(fontSize = 22.sp))
                                Text(text = item.saleUnitPrice.toString(), style = TextStyle(fontSize = 18.sp))

                                Row {
                                    IconButton(
                                        onClick = {
                                            isFavoriteClicked = if(isFavoriteClicked == 1) 0 else 1
                                        },
                                        Modifier.background(Red400, RoundedCornerShape(4.dp))
                                    ) {
                                        Row {
                                            Image(
                                                painter = painterResource(
                                                    id = if (isFavoriteClicked == 1) R.drawable.ic_favorite
                                                    else R.drawable.ic_favorite_border
                                                ),
                                                contentDescription = "favorite button"
                                            )
                                            Text(text = "Favorite", color = Color.White)
                                        }
                                    }

                                    Button(onClick = { /*TODO*/ }) {
                                        Text(text = "Add to cart")
                                    }
                                }
                            }
                        }
                    })
                }
            }

            CircularProgressIndicator(
                modifier = Modifier.testTag(progressLoaderTag)
            )
        }
    }
}