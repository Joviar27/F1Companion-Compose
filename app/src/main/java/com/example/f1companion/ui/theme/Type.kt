package com.example.f1companion.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default  ,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = MontserratExtraBold,
        fontSize = 18.sp
    ),
    h2 = TextStyle(
        fontFamily = MontserratBold,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = MontserratRegular,
        fontSize = 9.sp
    ),
    h5 = TextStyle(
        fontFamily = MontserratRegular,
        fontSize = 12.sp
    ),
    h4 = TextStyle(
        fontFamily = MontserratRegular,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)