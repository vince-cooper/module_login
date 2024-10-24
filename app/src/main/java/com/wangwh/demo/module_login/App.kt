package com.wangwh.demo.common_module

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * 作者: wenhui.w
 * 日期: 2024-10-24 16:04
 * 描述:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }
}