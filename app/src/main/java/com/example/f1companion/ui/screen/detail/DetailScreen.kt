package com.example.f1companion.ui.screen.detail

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.f1companion.R
import com.example.f1companion.ui.ViewModelFactory
import com.example.f1companion.ui.common.UiState

@Composable
fun DetailScreen(
    teamId : Long,
    viewModel : DetailViewModel =
        viewModel(
            factory = ViewModelFactory.getInstance(
                context = LocalContext.current
            )
        ),
    onShareClick : (String) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading ->{
                viewModel.getTeamById(teamId)
            }
            is UiState.Error -> {
                Log.d(ContentValues.TAG, uiState.errorMessage)
            }
            is UiState.Success ->{
                val data = uiState.data
                DetailContent(
                    headerImage = data.headerPic,
                    teamLogo = data.bannerPic,
                    teamName = data.fullName,
                    description = data.longOverview,
                    teamImage = data.galleryPic1,
                    helmet1Image = data.galleryPic2,
                    helmet2Image = data.galleryPic3,
                    pageLink = data.pageLink,
                    onShareClick = onShareClick
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    headerImage : Int,
    teamLogo : Int,
    teamName : String,
    description : String,
    teamImage : Int,
    helmet1Image : Int,
    helmet2Image : Int,
    pageLink : String,
    onShareClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .height(330.dp)
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = headerImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
            )
            Image(
                painter = painterResource(id = teamLogo),
                contentDescription = null,
                modifier = modifier
                    .padding(16.dp)
                    .height(60.dp)
                    .width(110.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .align(Alignment.TopEnd),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = {
                    onShareClick(pageLink)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primaryVariant)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = stringResource(id = R.string.icon_share),
                    tint = Color.White,
                )
            }
        }
        Text(
            text = teamName,
            style = MaterialTheme.typography.h1,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.h5,
            maxLines = 20,
            textAlign = TextAlign.Justify
        )
        Text(
            text = stringResource(id = R.string.mini_gallery),
            style = MaterialTheme.typography.h1,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(215.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Image(
                painter = painterResource(id = teamImage),
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(id = helmet1Image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop

                )
                Image(
                    painter = painterResource(id = helmet2Image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}