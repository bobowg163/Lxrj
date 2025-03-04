package com.example.lxrj.util

import com.example.lxrj.calendar.CALENDAR_STARTS_ON
import java.time.YearMonth
import java.time.temporal.WeekFields

/*
项目:Lxrj
包名：com.example.lxrj.util
作者: bobo
发布日期及时间: 2025/3/1 星期六  11:29
*/
fun YearMonth.getNumberWeeks(weekFields:WeekFields = CALENDAR_STARTS_ON ):Int{
    val firstWeekNumber = this.atDay(1)[weekFields.weekOfMonth()]
    val lastWeekNumber = this.atEndOfMonth()[weekFields.weekOfMonth()]
    return lastWeekNumber - firstWeekNumber + 1
}