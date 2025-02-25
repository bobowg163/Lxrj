package com.example.lxrj.data

import androidx.compose.runtime.Immutable

/*
项目:Lxrj
包名：com.example.lxrj.data
作者: bobo
发布日期及时间: 2025/2/25 星期二  18:39
*/

@Immutable
data class City(
    val name: String,
    val country: String,
    val latitude: String,
    val longitude: String,
) {
    val nameToDisplay = "$name, $country"
}

@Immutable
data class ExploreModel(
    val city: City,
    val description: String,
    val imageUrl: String
)