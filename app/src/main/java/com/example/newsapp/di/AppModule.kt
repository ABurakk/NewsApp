package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.data.News
import com.example.newsapp.repository.DefaultNewsRepository
import com.example.newsapp.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context
    )=context


    @Singleton
    @Provides
    fun provideMainRepository()= DefaultNewsRepository() as NewsRepository


}