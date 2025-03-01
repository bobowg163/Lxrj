package com.example.lxrj.di

import androidx.core.location.LocationRequestCompat.Quality
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

/*
项目:Lxrj
包名：com.example.lxrj.di
作者: bobo
发布日期及时间: 2025/2/25 星期二  20:49
*/

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

}


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher