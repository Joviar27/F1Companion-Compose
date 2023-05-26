package com.example.f1companion.model

data class Team (
    val id : Long,
    val name : String,
    val thumbnailPic : Int,
    val shortOverview : String ,

    val fullName: String,
    val headerPic : Int,
    val bannerPic : Int,
    val longOverview : String,

    val galleryPic1 : Int,
    val galleryPic2 : Int,
    val galleryPic3 : Int,

    val pageLink : String,

    val bookmarked : Boolean = false
)