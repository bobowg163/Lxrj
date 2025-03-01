package com.example.lxrj.calendar.model

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf

/*
项目:Lxrj
包名：com.example.lxrj.calendar.model
作者: bobo
发布日期及时间: 2025/2/25 星期二  21:11
*/
class CalendarState {
    val calendarUiState = mutableStateOf(CalendarUiState())



    companion object{
        const val DAYS_IN_WEEK = 7
    }
}