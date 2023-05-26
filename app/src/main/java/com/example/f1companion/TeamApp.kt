package com.example.f1companion

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.f1companion.navigation.Screen
import com.example.f1companion.ui.screen.about.AboutScreen
import com.example.f1companion.ui.screen.detail.DetailScreen
import com.example.f1companion.ui.screen.favorite.FavoriteScreen
import com.example.f1companion.ui.screen.home.HomeScreen

@Composable
fun TeamApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if(currentRoute != Screen.Home.route){
                DetailAppBar(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
            else{
                HomeAppBar(
                    onFavoriteClick = {
                        navController.navigate(Screen.Favourite.route)
                    },
                    onAboutClick = {
                        navController.navigate(Screen.About.route)
                    }
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route){
                HomeScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.DetailTeam.createRoute(it))
                    }
                )
            }
            composable(Screen.Favourite.route){
                FavoriteScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.DetailTeam.createRoute(it))
                    }
                )
            }
            composable(Screen.About.route){
                AboutScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(
                route = Screen.DetailTeam.route,
                arguments = listOf(
                    //Set the argument type to be received
                    navArgument("teamId"){
                        type = NavType.LongType
                    }
                )
            ){
                val context = LocalContext.current
                val id = it.arguments?.getLong("teamId") ?: -1L
                DetailScreen(
                    teamId = id,
                    onShareClick = { pageLink ->
                        shareTeamPage(context, pageLink)
                    }
                )
            }
        }
    }
}

private fun shareTeamPage(context: Context, pageLink : String){
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.action_share))
        putExtra(Intent.EXTRA_TEXT, pageLink)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.action_share)
        )
    )
}

@Composable
fun HomeAppBar(
    modifier: Modifier = Modifier,
    onFavoriteClick : () -> Unit,
    onAboutClick : () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_title),
                style = MaterialTheme.typography.h1.copy(
                    color = Color.Black
                )
            )
        },
        actions = {
            IconButton(
                onClick = onFavoriteClick
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.icon_favorite_page),
                    tint = MaterialTheme.colors.secondary
                )
            }
            IconButton(
                onClick = onAboutClick
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = stringResource(id = R.string.icon_about),
                    tint = MaterialTheme.colors.secondary
                )
            }
        },
        modifier = modifier.shadow(5.dp)
    )
}

@Composable
fun DetailAppBar(
    modifier: Modifier = Modifier,
    onBackClick : () -> Unit
){
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.icon_back),
                    tint = MaterialTheme.colors.secondary
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.app_title),
                style = MaterialTheme.typography.h1.copy(
                    color = Color.Black
                )
            )
        },
        modifier = modifier.shadow(5.dp)
    )
}