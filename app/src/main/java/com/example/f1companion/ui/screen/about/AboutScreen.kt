package com.example.f1companion.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.f1companion.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.padding(48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = stringResource(id = R.string.profile_photo),
            modifier = Modifier
                .padding(vertical = 16.dp)
                .height(220.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Text(
            text = stringResource(id = R.string.creator_name),
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.creator_email),
            style = MaterialTheme.typography.h2,
        )
    }
}