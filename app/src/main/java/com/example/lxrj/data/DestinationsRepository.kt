package com.example.lxrj.data

import javax.inject.Inject

/*
项目:Lxrj
包名：com.example.lxrj.data
作者: bobo
发布日期及时间: 2025/2/25 星期二  19:07
*/

class DestinationsRepository @Inject constructor(
    private val destinationsLocalDataSource: DestinationsLocalDataSource
) {
    val destinations: List<ExploreModel> = destinationsLocalDataSource.craneDestinations
    val hotels: List<ExploreModel> = destinationsLocalDataSource.craneHotels
    val restaurants: List<ExploreModel> = destinationsLocalDataSource.craneRestaurants
    val cities: List<City> = listCities
    fun getDestination(cityName: String): City? {
        return cities.firstOrNull { it.name == cityName }
    }
}