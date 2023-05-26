package com.example.f1companion.model

import android.content.Context
import com.example.f1companion.R

object TeamDataSource {
    fun getTeamList(context : Context) : ArrayList<Team>{
        val id  = context.resources.getStringArray(R.array.id_team)
        val name = context.resources.getStringArray(R.array.thumbnail_title)
        val shortOverview = context.resources.getStringArray(R.array.thumbnail_overview)
        val thumbnailPic = thumbnailList
        val fullName = context.resources.getStringArray(R.array.detail_title)
        val headerPic = headerList
        val bannerPic = bannerList
        val longOverview = context.resources.getStringArray(R.array.detail_overview)
        val galleryPic1 = pic1List
        val galleryPic2 = pic2List
        val galleryPic3 = pick3List
        val pageLink = context.resources.getStringArray(R.array.page_link)

        val teamList = ArrayList<Team>()

        for (index in id.indices) {
            teamList.add(
                Team(
                    id = id[index].toLong(),
                    name = name[index],
                    thumbnailPic = thumbnailPic[index],
                    shortOverview = shortOverview[index],
                    fullName = fullName[index],
                    headerPic = headerPic[index],
                    bannerPic = bannerPic[index],
                    longOverview = longOverview[index],
                    galleryPic1 = galleryPic1[index],
                    galleryPic2 = galleryPic2[index],
                    galleryPic3 = galleryPic3[index],
                    pageLink = pageLink[index]
                )
            )
        }
        return teamList
    }

    private val thumbnailList = listOf(
        R.drawable.am_thumbnail_edited,
        R.drawable.alp_thumbnail_edited,
        R.drawable.af_thumbnail_edited,
        R.drawable.at_thumbnail_edited,
        R.drawable.fer_thumbnail_edited,
        R.drawable.has_thumbnail_edited,
        R.drawable.mcl_thumbnail_edited,
        R.drawable.mer_thumbnail_edited,
        R.drawable.rbr_thumbnail_edited,
        R.drawable.wil_thumbnail_edited
    )

    private val headerList = listOf(
        R.drawable.am_header,
        R.drawable.alp_header,
        R.drawable.af_header,
        R.drawable.at_header,
        R.drawable.fer_header,
        R.drawable.has_header,
        R.drawable.mcl_header,
        R.drawable.mer_header,
        R.drawable.rbr_header,
        R.drawable.wil_header
    )

    private val bannerList = listOf(
        R.drawable.am_banner,
        R.drawable.alp_banner,
        R.drawable.af_banner,
        R.drawable.at_banner,
        R.drawable.fer_banner,
        R.drawable.has_banner,
        R.drawable.mcl_banner,
        R.drawable.mer_banner,
        R.drawable.rbr_banner,
        R.drawable.wil_banner
    )

    private val pic1List = listOf(
        R.drawable.am_pic1,
        R.drawable.alp_pic1,
        R.drawable.af_pic1,
        R.drawable.at_pic1,
        R.drawable.fer_pic1,
        R.drawable.has_pic1,
        R.drawable.mcl_pic1,
        R.drawable.mer_pic1,
        R.drawable.rbr_pic1,
        R.drawable.wil_pic1
    )

    private val pic2List = listOf(
        R.drawable.am_pic2,
        R.drawable.alp_pic2,
        R.drawable.af_pic2,
        R.drawable.at_pic2,
        R.drawable.fer_pic2,
        R.drawable.has_pic2,
        R.drawable.mcl_pic2,
        R.drawable.mer_pic2,
        R.drawable.rbr_pic2,
        R.drawable.wil_pic2
    )

    private val pick3List = listOf(
        R.drawable.am_pic3,
        R.drawable.alp_pic3,
        R.drawable.af_pic3,
        R.drawable.at_pic3,
        R.drawable.fer_pic3,
        R.drawable.has_pic3,
        R.drawable.mcl_pic3,
        R.drawable.mer_pic3,
        R.drawable.rbr_pic3,
        R.drawable.wil_pic3
    )
}