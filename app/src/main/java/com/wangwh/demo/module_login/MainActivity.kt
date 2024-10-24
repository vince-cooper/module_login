package com.wangwh.demo.module_login

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.wangwh.commons.module.base.BaseActivity
import com.wangwh.commons.module.router.RouterIndex
import com.wangwh.demo.module_login.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            ARouter.getInstance().build(RouterIndex.P_LOGIN).navigation()
        }
    }
}