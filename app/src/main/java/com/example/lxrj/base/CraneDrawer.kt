package com.example.lxrj.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lxrj.R
import com.example.lxrj.ui.theme.LxrjTheme

/*
项目:Lxrj
包名：com.example.lxrj.base
作者: bobo
发布日期及时间: 2025/3/7 星期五  15:54
*/

private val screens = listOf(
    R.string.screen_title_find_trips,
    R.string.screen_title_my_trips,
    R.string.screen_title_saved_trips,
    R.string.screen_title_price_alerts,
    R.string.screen_title_my_account
)

@Composable
fun CraneDrawer(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 24.dp, top = 48.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_crane_drawer),
                contentDescription = stringResource(R.string.cd_drawer)
            )
            for (screen in screens) {

                Spacer(Modifier.height(24.dp))
                Text(
                    text = stringResource(id = screen),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

}

@Preview
@Composable
private fun CraneDrawerPriview() {
    LxrjTheme {
        CraneDrawer()
    }
}