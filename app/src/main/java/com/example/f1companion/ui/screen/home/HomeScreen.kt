package com.example.f1companion.ui.screen.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.f1companion.model.Team
import com.example.f1companion.ui.ViewModelFactory
import com.example.f1companion.ui.common.UiState
import com.example.f1companion.ui.component.ItemTeam
import com.example.f1companion.ui.component.SearchBar
import com.example.f1companion.ui.theme.F1CompanionTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel : HomeViewModel =
        viewModel(
            factory = ViewModelFactory.getInstance(
                context = LocalContext.current
            )
        ),
    navigateToDetail : (Long) -> Unit,
) {
    val query by viewModel.query

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState){
                is UiState.Loading ->{
                    viewModel.getAllTeam()
                }
                is UiState.Error -> {
                    Log.d(TAG, uiState.errorMessage)
                }
                is UiState.Success ->{
                    HomeContent(
                        team = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                        onFavoriteClick = {
                            viewModel.updateFavorite(it)
                        },
                        onQueryChange = viewModel :: search,
                        query = query
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    team : List<Team>,
    modifier: Modifier = Modifier,
    navigateToDetail : (Long) -> Unit,
    onFavoriteClick: (Long) -> Unit,
    onQueryChange : (String) -> Unit,
    query : String
){
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        item {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
            )
        }
        items(
            team,
            key = {
                it.id
            }
        ){ team ->
            ItemTeam(
                id = team.id,
                title = team.name,
                description = team.shortOverview,
                thumbnail = team.thumbnailPic,
                modifier = modifier.clickable {
                    navigateToDetail(team.id)
                }.animateItemPlacement(tween(durationMillis = 300)),
                onFavoriteClick = onFavoriteClick,
                isBookmarked = team.bookmarked
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    F1CompanionTheme {
        HomeScreen(
            navigateToDetail ={},
        )
    }
}