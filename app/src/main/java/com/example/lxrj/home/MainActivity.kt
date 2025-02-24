package com.example.lxrj.home

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lxrj.ui.theme.LxrjTheme
import dagger.hilt.android.AndroidEntryPoint

/*
项目:Lxrj
包名：com.example.lxrj.home
作者: bobo
发布日期及时间: 2025/2/23 星期日  20:14
*/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        super.onCreate(savedInstanceState)

        setContent {
            LxrjTheme {

            }
        }
    }
}

