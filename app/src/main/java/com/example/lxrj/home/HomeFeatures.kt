package com.example.lxrj.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
项目:Lxrj
包名：com.example.lxrj.home
作者: bobo
发布日期及时间: 2025/3/8 星期六  11:54
*/

@Composable
fun FlySearchContent(
    widthSize: WindowWidthSizeClass,
    datesSelected: String,
    searchUpdates: FlySearchContentUpdates
) {
    val columns = when (widthSize) {
        WindowWidthSizeClass.Compact -> 1
        WindowWidthSizeClass.Medium -> 2
        WindowWidthSizeClass.Expanded -> 4
        else -> 1
    }
    CraneSearch(
        columns = columns
    ) {
        item {
//            PeopleUserInput(onPeopleChanged = eatUpdates.onPeopleChanged)
        }
//        item {
//            DatesUserInput(
//                datesSelected,
//                onDateSelectionClicked = eatUpdates.onDateSelectionClicked
//            )
//        }
//        item {
//            SimpleUserInput(
//                caption = stringResource(R.string.input_select_time),
//                vectorImageId = R.drawable.ic_time
//            )
//        }
//        item {
//            SimpleUserInput(
//                caption = stringResource(R.string.input_select_location),
//                vectorImageId = R.drawable.ic_restaurant
//            )
//        }
    }
}

@Composable
private fun CraneSearch(
    columns: Int,
    content: LazyGridScope.() -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 12.dp),
        columns = GridCells.Fixed(columns),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = content
    )
}