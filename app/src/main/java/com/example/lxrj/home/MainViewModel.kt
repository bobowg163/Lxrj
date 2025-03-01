package com.example.lxrj.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lxrj.calendar.model.CalendarState
import com.example.lxrj.data.DestinationsRepository
import com.example.lxrj.data.ExploreModel
import com.example.lxrj.di.DefaultDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/*
项目:Lxrj
包名：com.example.lxrj.home
作者: bobo
发布日期及时间: 2025/2/25 星期二  20:45
*/

const val MAX_PEOPLE = 4

@HiltViewModel
class MainViewModel @Inject constructor(
    private val destinationsRepository: DestinationsRepository,
    @DefaultDispatcher
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    val shownSplash = mutableStateOf(SplashState.Show)
    val hotels:List<ExploreModel> = destinationsRepository.hotels
    val restaurants:List<ExploreModel> = destinationsRepository.restaurants
    val calendarState = CalendarState()

}