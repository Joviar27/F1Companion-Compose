package com.example.f1companion.ui.screen.favorite

import android.content.ContentValues
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.f1companion.ui.theme.F1CompanionTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel : FavoriteViewModel =
        viewModel(
            factory = ViewModelFactory.getInstance(
                context = LocalContext.current
            )
        ),
    navigateToDetail : (Long) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState){
                is UiState.Loading ->{
                    viewModel.getAllFavorite()
                }
                is UiState.Error -> {
                    Log.d(ContentValues.TAG, uiState.errorMessage)
                }
                is UiState.Success ->{
                    FavoriteContent(
                        team = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                        onFavoriteClick = {
                            viewModel.updateFavorite(it)
                            viewModel.getAllFavorite()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteContent(
    team : List<Team>,
    modifier: Modifier = Modifier,
    navigateToDetail : (Long) -> Unit,
    onFavoriteClick: (Long) -> Unit,
){
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
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
        FavoriteScreen(
            navigateToDetail ={},
        )
    }
}