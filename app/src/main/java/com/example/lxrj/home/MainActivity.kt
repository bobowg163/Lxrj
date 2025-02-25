package com.example.lxrj.home

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lxrj.ui.theme.LxrjTheme
import dagger.hilt.android.AndroidEntryPoint

/*
项目:Lxrj
包名：com.example.lxrj.home
作者: bobo
发布日期及时间: 2025/2/23 星期日  20:14
*/

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        super.onCreate(savedInstanceState)
        setContent {
            LxrjTheme {
                val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.Home.route) {
                    composable(Routes.Home.route) {

                    }
                }
            }
        }
    }
}


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    widthSize:WindowWidthSizeClass,
    onExploreItemClicked:OnExploreItemClicked,
    onDateSelectionClicked:() -> Unit,
//    mainViewModel: MainViewModel
) {

}


sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Calender : Routes("calender")
}

enum class SplashState { Show, Completed }