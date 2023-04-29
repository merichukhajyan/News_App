package com.example.newsapp.presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.homeScreen.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchHeader(
    newsViewModel: NewsViewModel
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(all = 15.dp),
    ) {
        Text(
            text = "News",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary),
                value = newsViewModel.state.value.searchValue,
                onValueChange = { newsViewModel.changeSearchBarValue(it) },
                placeholder = {
                    Text(text = "search")
                },
                leadingIcon = {
                    TextButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                    }
                }
            )

            Button(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
            }

        }

    }
}