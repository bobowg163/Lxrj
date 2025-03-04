package com.example.lxrj.calendar.model

import androidx.compose.runtime.mutableStateOf
import com.example.lxrj.util.getNumberWeeks
import java.time.LocalDate
import java.time.Period
import java.time.YearMonth

/*
项目:Lxrj
包名：com.example.lxrj.calendar.model
作者: bobo
发布日期及时间: 2025/2/25 星期二  21:11
*/
class CalendarState {
    val calendarUiState = mutableStateOf(CalendarUiState())
    private val listMonths:List<Month>
    private val calendarStartDate:LocalDate = LocalDate.now().withMonth(1).withDayOfMonth(1)
    private val calendarEndDate:LocalDate = LocalDate.now().plusYears(2).withMonth(12).withDayOfMonth(31)
    private val periodBetweenCalenderStartEnd:Period = Period.between(calendarStartDate,calendarEndDate)

    fun setSelectedDay(newDate:LocalDate){
        calendarUiState.value = updateSelectedDay(newDate)
    }



    init {
        val tempListMonths = mutableListOf<Month>()
        var startYearMonth = YearMonth.from(calendarStartDate)
        for (numberMonth in 0 until  periodBetweenCalenderStartEnd.toTotalMonths()){
            val numberWeeks = startYearMonth.getNumberWeeks()
            val listWeekItems = mutableListOf<Week>()
            for (week in 0 until numberWeeks){
                listWeekItems.add(Week(
                    number = week,
                    yearMonth = startYearMonth
                ))
            }
            val month = Month(startYearMonth, listWeekItems)
            tempListMonths.add(month)
            startYearMonth = startYearMonth.plusMonths(1)
        }
        listMonths = tempListMonths.toList()
    }

    private fun updateSelectedDay(newDate: LocalDate): CalendarUiState {
        val currentState = calendarUiState.value
        val selectedStartDate = currentState.selectedStartDate
        val selectedEndDate = currentState.selectedEndDate

        return when {
            selectedStartDate == null && selectedEndDate == null -> {
                currentState.setDates(newDate, null)
            }
            selectedStartDate != null && selectedEndDate != null -> {
                val animationDirection = if (newDate.isBefore(selectedStartDate)) {
                   AnimateDirection.BACKWARDS
                } else {
                    AnimateDirection.FORWARDS
                }
                this.calendarUiState.value = currentState.copy(
                    selectedStartDate = null,
                    selectedEndDate = null,
                    animateDirection = animationDirection
                )
                updateSelectedDay(newDate = newDate)
            }
            selectedStartDate == null -> {
                if (newDate.isBefore(selectedEndDate)) {
                    currentState.copy(animateDirection = AnimateDirection.BACKWARDS)
                        .setDates(newDate, selectedEndDate)
                } else if (newDate.isAfter(selectedEndDate)) {
                    currentState.copy(animateDirection = AnimateDirection.FORWARDS)
                        .setDates(selectedEndDate, newDate)
                } else {
                    currentState
                }
            }
            else -> {
                if (newDate.isBefore(selectedStartDate)) {
                    currentState.copy(animateDirection = AnimateDirection.BACKWARDS)
                        .setDates(newDate, selectedStartDate)
                } else if (newDate.isAfter(selectedStartDate)) {
                    currentState.copy(animateDirection = AnimateDirection.FORWARDS)
                        .setDates(selectedStartDate, newDate)
                } else {
                    currentState
                }
            }
        }
    }

    companion object{
        const val DAYS_IN_WEEK = 7
    }
}