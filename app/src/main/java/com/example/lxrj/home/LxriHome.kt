package com.example.lxrj.home

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lxrj.data.ExploreModel

/*
项目:Lxrj
包名：com.example.lxrj.home
作者: bobo
发布日期及时间: 2025/2/25 星期二  18:36
*/

typealias OnExploreItemClicked = (ExploreModel) -> Unit

enum class LxriScreen {
    Fly, Sleep, Eat
}

@Composable
fun LxriHome(
    widthSize: WindowWidthSizeClass,
    onExploreItemClicked: OnExploreItemClicked,
    onDateSelectionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {

}
