package com.example.lxrj

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.example.lxrj.util.UnsplashSizingInterceptor
import dagger.hilt.android.HiltAndroidApp

/*
项目:Lxrj
包名：com.example.lxrj
作者: bobo
发布日期及时间: 2025/2/24 星期一  18:32
*/
@HiltAndroidApp
class LxrjApplication:Application() ,ImageLoaderFactory{
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this).components{
            add(UnsplashSizingInterceptor)
        }.build()
    }

}