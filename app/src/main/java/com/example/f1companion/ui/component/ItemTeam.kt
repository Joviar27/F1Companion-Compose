package com.example.f1companion.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.f1companion.ui.theme.F1CompanionTheme
import com.example.f1companion.R

@Composable
fun ItemTeam(
    id : Long,
    title : String,
    description : String,
    thumbnail : Int,
    isBookmarked : Boolean,
    onFavoriteClick : (Long) -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(165.dp)
    ){

        var isBookmarked by remember { mutableStateOf(isBookmarked) }

        Image(
            painter = painterResource(id = thumbnail),
            contentDescription = null,
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        InfoBox(
            title = title,
            description = description,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        IconButton(
            onClick = {
                onFavoriteClick(id)
                isBookmarked = !isBookmarked
            },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomEnd)
        ) {
            if(isBookmarked){
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.icon_rm_favorite),
                    tint = Color.White
                )
            }else{
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.icon_add_favorite),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun InfoBox(
    title : String,
    description: String,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .width(210.dp)
            .height(75.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.dark_grey_square),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5F)
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .padding(top = 8.dp, end = 8.dp)
                    .fillMaxWidth()
                    .alpha(1.0F),
                style = MaterialTheme.typography.h1.copy(
                    color = Color.White
                ),
                textAlign = TextAlign.End
            )
            Text(
                text = description,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h6.copy(
                    color = Color.White
                ),
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemTeamPreview(){
    F1CompanionTheme {
        ItemTeam(
            id = 1,
            title = "Alpine F1 Team",
            description = "Silverstone-based F1 team debuting in 2021, formerly known as Racing Point. Finished 7th in the 2022 Constructor Championship",
            thumbnail = R.drawable.am_thumbnail_edited,
            isBookmarked = false,
            onFavoriteClick = {}
        )
    }
}

