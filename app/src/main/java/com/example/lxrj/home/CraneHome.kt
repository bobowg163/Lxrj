package com.example.lxrj.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.lxrj.data.ExploreModel

/*
项目:Lxrj
包名：com.example.lxrj.home
作者: bobo
发布日期及时间: 2025/3/6 星期四  21:29
*/

typealias onExploreItemClicked = (ExploreModel) -> Unit

enum class CraneScreen {
    Fly, Sleep, Eat
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CraneHome(
    widthSize:WindowWidthSizeClass,
    onExploreItemClicked:OnExploreItemClicked,
    onDateSelectionClicked:()-> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val topAppBarState = rememberTopAppBarState()
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

        }
    ) {
        Scaffold(
            topBar = {

            }
        ) {innerPadding ->
            Column (
                modifier = Modifier.padding(innerPadding)
            ){

            }
        }
    }
}

