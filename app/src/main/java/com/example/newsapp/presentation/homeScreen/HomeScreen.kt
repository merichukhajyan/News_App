package com.example.newsapp.presentation.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.presentation.Components.SearchHeader
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = koinViewModel()
) {
    val state = newsViewModel.state.value
    if (state.isLoading) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onSurface)
        }

    }
    if (state.error != "") {
        Text(text = state.error)
    }
    if (state.news != null && state.news!!.articles.isNotEmpty()) {
        Scaffold(
            topBar = {
                SearchHeader(newsViewModel)
            }
        ) {
                paddingValue ->

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(25.dp),
                contentPadding = PaddingValues(
                    horizontal = paddingValue.calculateStartPadding(
                        LayoutDirection.Ltr
                    ), vertical = paddingValue.calculateTopPadding()
                )
            ) {
                items(state.news!!.articles) { item ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.urlToImage)
                                .crossfade(true)
                                .build(),
                            contentDescription = item.title,
                            modifier = Modifier
                                .width(150.dp)
                                .height(100.dp),
                            contentScale = ContentScale.Crop,
                        )
                        Column() {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.typography.titleMedium.color.copy(alpha = 0.6f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = item.description ?: "",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(15.dp))

                            Text(
                                text = item.author ?: "",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.typography.titleSmall.color.copy(alpha = 0.6f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )


                        }
                    }
                }
            }
        }
    }
}