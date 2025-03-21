package com.example.lxrj.calendar.model

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.abs

/*
项目:Lxrj
包名：com.example.lxrj.calendar.model
作者: bobo
发布日期及时间: 2025/2/25 星期二  21:12
*/
data class CalendarUiState(
    val selectedStartDate: LocalDate? = null,
    val selectedEndDate: LocalDate? = null,
    val animateDirection: AnimateDirection? = null
) {
    val numberSelectedDays: Float
        get() {
            if (selectedStartDate == null) return 0f
            if (selectedEndDate == null) return 1f
            return ChronoUnit.DAYS.between(selectedStartDate, selectedEndDate.plusDays(1)).toFloat()
        }
    val hasSelectedDates: Boolean
        get() {
            return selectedStartDate != null || selectedEndDate != null
        }

    val selectedDatesFormatted: String
        get() {
            if (selectedStartDate == null) return ""
            var output = selectedStartDate.format(SHORT_DATE_FORMAT)
            if (selectedEndDate != null) {
                output += "- ${selectedEndDate.format(SHORT_DATE_FORMAT)}"
            }
            return output
        }

    fun hasSelectedPeriodOverlap(start: LocalDate, end: LocalDate): Boolean {
        if (!hasSelectedDates) return false
        if (selectedStartDate == null && selectedEndDate == null) return false
        if (selectedStartDate == start || selectedStartDate == end) return true
        if (selectedEndDate == null) {
            return !selectedStartDate!!.isBefore(start) && !selectedStartDate.isAfter(end)
        }
        return !end.isBefore(selectedStartDate) && !start.isAfter(selectedEndDate)
    }

    fun isDateInSelectedPeriod(date: LocalDate): Boolean {
        if (selectedStartDate == null) return false
        if (selectedStartDate == date) return true
        if (selectedEndDate == null) return false
        if (date.isBefore(selectedStartDate) || date.isAfter(selectedEndDate)) return false
        return true
    }

    fun getNumberSelectedDaysInWeek(currentWeekStartDate: LocalDate, month: YearMonth): Int {
        var countSelected = 0
        var currentDate = currentWeekStartDate
        for (i in 0 until CalendarState.DAYS_IN_WEEK) {
            if (isDateInSelectedPeriod(currentDate) && currentDate.month == month.month) {
                countSelected++
            }
            currentDate = currentDate.plusDays(1)
        }
        return countSelected
    }

    fun selectedStartOffset(currentWeekStartDate: LocalDate, yearMonth: YearMonth): Int {
        return if (animateDirection == null || animateDirection.isForwards()) {
            var startDate = currentWeekStartDate
            var startOffset = 0
            for (i in 0 until CalendarState.DAYS_IN_WEEK) {
                if (!isDateInSelectedPeriod(startDate) || startDate.month != yearMonth.month) {
                    startOffset++
                } else {
                    break
                }
                startDate = startDate.plusDays(1)
            }
            startOffset
        } else {
            var startDate = currentWeekStartDate.plusDays(6)
            var startOffset = 0
            for (i in 0 until CalendarState.DAYS_IN_WEEK) {
                if (!isDateInSelectedPeriod(startDate) || startDate.month != yearMonth.month) {
                    startOffset++
                } else {
                    break
                }
                startDate = startDate.minusDays(1)
            }
            7 - startOffset
        }
    }

    fun isLeftHighlighted(beginningWeek: LocalDate?, month: YearMonth): Boolean {
        return if (beginningWeek != null) {
            if (month.month.value != beginningWeek.month.value) {
                false
            } else {
                val beginningWeekSelected = isDateInSelectedPeriod(beginningWeek)
                val lastDayPreviousWeek = beginningWeek.minusDays(1)
                isDateInSelectedPeriod(lastDayPreviousWeek) && beginningWeekSelected
            }
        } else {
            false
        }
    }

    fun isRightHighlighted(beginningWeek: LocalDate?,month: YearMonth):Boolean{
        val lastDayOfTheWeek = beginningWeek?.plusDays(6)
        return if (lastDayOfTheWeek != null) {
            if (month.month.value != lastDayOfTheWeek.month.value) {
                false
            } else {
                val lastDayOfTheWeekSelected = isDateInSelectedPeriod(lastDayOfTheWeek)
                val firstDayNextWeek = lastDayOfTheWeek.plusDays(1)
                isDateInSelectedPeriod(firstDayNextWeek) && lastDayOfTheWeekSelected
            }
        } else {
            false
        }
    }

    fun dayDelay(currentWeekStartDate: LocalDate):Int{
        if (selectedStartDate== null && selectedEndDate== null ) return 0
        val endWeek = currentWeekStartDate.plusDays(6)
        return if(animateDirection!= null && animateDirection.isBackwards()){
            if (selectedStartDate?.isBefore(currentWeekStartDate)==true||selectedEndDate?.isAfter(endWeek) == true){
                abs(ChronoUnit.DAYS.between(endWeek,selectedEndDate)).toInt()
            }else{
                0
            }
        }else {
            if (selectedStartDate?.isBefore(currentWeekStartDate)==true||selectedStartDate?.isAfter(endWeek)==true){
                abs(ChronoUnit.DAYS.between(currentWeekStartDate,selectedStartDate)).toInt()
            }else{
                0
            }
        }
    }

    fun monthOverlapSelectionDelay(
        currentWeekStartDate: LocalDate,
        week: Week
    ): Int {
        return if (animateDirection?.isBackwards() == true) {
            val endWeek = currentWeekStartDate.plusDays(6)
            val isStartInADifferentMonth = endWeek.month != week.yearMonth.month
            if (isStartInADifferentMonth) {
                var currentDate = endWeek
                var offset = 0
                for (i in 0 until CalendarState.DAYS_IN_WEEK) {
                    if (currentDate.month.value != week.yearMonth.month.value &&
                        isDateInSelectedPeriod(currentDate)
                    ) {
                        offset++
                    }
                    currentDate = currentDate.minusDays(1)
                }
                offset
            } else {
                0
            }
        } else {
            val isStartInADifferentMonth = currentWeekStartDate.month != week.yearMonth.month
            return if (isStartInADifferentMonth) {
                var currentDate = currentWeekStartDate
                var offset = 0
                for (i in 0 until CalendarState.DAYS_IN_WEEK) {
                    if (currentDate.month.value != week.yearMonth.month.value &&
                        isDateInSelectedPeriod(currentDate)
                    ) {
                        offset++
                    }
                    currentDate = currentDate.plusDays(1)
                }
                offset
            } else {
                0
            }
        }
    }

    fun setDates(newFrom: LocalDate?, newTo: LocalDate?): CalendarUiState {
        return if (newTo == null) {
            copy(selectedStartDate = newFrom)
        } else {
            copy(selectedStartDate = newFrom, selectedEndDate = newTo)
        }
    }

    companion object {
        private val SHORT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd")
    }
}

enum class AnimateDirection {
    FORWARDS, BACKWARDS;

    fun isBackwards() = this == BACKWARDS
    fun isForwards() = this == FORWARDS
}