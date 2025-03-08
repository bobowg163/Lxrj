package com.example.lxrj.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lxrj.R
import com.example.lxrj.base.CraneDrawer
import com.example.lxrj.base.CraneTabBar
import com.example.lxrj.base.CraneTabs
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
    widthSize: WindowWidthSizeClass,
    onExploreItemClicked: OnExploreItemClicked,
    onDateSelectionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val topAppBarState = rememberTopAppBarState()
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.statusBarsPadding(),
        drawerContent = {
            CraneDrawer()
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.cd_drawer)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            topAppBarState.contentOffset
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    },
                    actions = {
                        HomeTabBar(modifier = Modifier.padding(8.dp), openDrawer = {}, tabSelected =CraneScreen.Fly, onTabSelected = {} )
                    }
                )
            }
        ) { innerPadding ->
            Column(modifier = modifier.padding(innerPadding)) {
                
            }
        }
    }
}


@Composable
fun HomeTabBar(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    tabSelected: CraneScreen,
    onTabSelected: (CraneScreen) -> Unit
) {
    CraneTabBar(
        modifier = modifier
            .wrapContentWidth()
            .sizeIn(maxWidth = 500.dp),
        onMenuClicked = openDrawer
    ) { tabBarModifier ->
        CraneTabs(
            modifier = tabBarModifier,
            titles = CraneScreen.entries.map { it.name },
            tabSelected = tabSelected,
            onTabSelected = { newTab ->
                onTabSelected(CraneScreen.entries[newTab.ordinal])
            })
    }
}

private const val TAB_SWITCH_ANIM_DURATION = 300

@Composable
private fun SearchContent (
    widthSize: WindowWidthSizeClass,
    tabSelected: CraneScreen,
    viewModel: MainViewModel,
    onPeopleChanged: (Int) -> Unit,
    onDateSelectionClicked: () -> Unit,
    onExploreItemClicked: OnExploreItemClicked
) {
    val selectedDates = viewModel.calendarState.calendarUiState.value.selectedDatesFormatted
    AnimatedContent(
        targetState = tabSelected,
        transitionSpec = {
            fadeIn(animationSpec = tween(TAB_SWITCH_ANIM_DURATION, easing = EaseIn)).togetherWith(
                fadeOut(
                    animationSpec = tween(TAB_SWITCH_ANIM_DURATION, easing = EaseOut)
                )
            ).using(
                SizeTransform(
                    sizeAnimationSpec = {_,_ ->
                        tween(TAB_SWITCH_ANIM_DURATION, easing = EaseInOut)
                    }
                )
            )
        },
        label = "SearchContent"
    ) {targetState ->
        when(targetState){
            CraneScreen.Fly -> FlySearchContent(
                widthSize = widthSize,
                datesSelected = selectedDates,
                searchUpdates = FlySearchContentUpdates(
                    onPeopleChanged = onPeopleChanged,
                    onToDestinationChanged = viewModel::toDestinationChanged,
                    onDateSelectionClicked = onDateSelectionClicked,
                    onExploreItemClicked = onExploreItemClicked
                )
            )
            CraneScreen.Sleep -> TODO()
            CraneScreen.Eat -> TODO()
        }
    }
}


data class FlySearchContentUpdates(
    val onPeopleChanged: (Int) -> Unit,
    val onToDestinationChanged: (String) -> Unit,
    val onDateSelectionClicked: () -> Unit,
    val onExploreItemClicked: OnExploreItemClicked
)

data class SleepSearchContentUpdates(
    val onPeopleChanged: (Int) -> Unit,
    val onDateSelectionClicked: () -> Unit,
    val onExploreItemClicked: OnExploreItemClicked
)

data class EatSearchContentUpdates(
    val onPeopleChanged: (Int) -> Unit,
    val onDateSelectionClicked: () -> Unit,
    val onExploreItemClicked: OnExploreItemClicked
)
