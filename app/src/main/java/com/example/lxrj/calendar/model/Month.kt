package com.example.lxrj.calendar.model

import java.time.YearMonth

/*
项目:Lxrj
包名：com.example.lxrj.calendar.model
作者: bobo
发布日期及时间: 2025/3/1 星期六  12:26
*/
data class Month(
    val yearMonth: YearMonth,
    val weeks: List<Week>
)
