package com.example.f1companion.data

import android.content.Context
import com.example.f1companion.model.Team
import com.example.f1companion.model.TeamDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TeamRepository(
    context: Context
) {
    private val teamList = mutableListOf<Team>()

    init{
        val teamFromSource = TeamDataSource.getTeamList(context)
        if(teamList.isEmpty()){
            teamFromSource.forEach{
                teamList.add(it)
            }
        }
    }

    fun getAllTeam() : Flow<List<Team>> {
        return flowOf(teamList)
    }

    fun getTeamByID(id: Long): Team{
        return teamList.first {
            it.id == id
        }
    }

    fun updateFavorite(id : Long){
        val index = teamList.indexOfFirst {
            it.id==id
        }
        val team = teamList[index]
        val currentStatus = team.bookmarked
        teamList[index] = team.copy(
            bookmarked = !currentStatus
        )
    }

    fun getAllFavorite() : Flow<List<Team>> {
        val favoriteList = teamList.filter {
            it.bookmarked
        }
        return flowOf(favoriteList)
    }

    fun searchTeam(query : String) : Flow<List<Team>>{
        val result = teamList.filter {
            it.name.contains(query, ignoreCase = true)
        }
        return flowOf(result)
    }


    companion object{
        @Volatile
        private var instance : TeamRepository? = null

        fun getInstance(context : Context) : TeamRepository =
            instance ?: synchronized(this){
                instance ?: TeamRepository(context = context)
            }.also { instance=it }
    }
}