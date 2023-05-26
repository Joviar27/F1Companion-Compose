package com.example.f1companion.di

import android.content.Context
import com.example.f1companion.data.TeamRepository

object Injection {
    fun provideRepository(context : Context) : TeamRepository{
        return TeamRepository.getInstance(context)
    }
}