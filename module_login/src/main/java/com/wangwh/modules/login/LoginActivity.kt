package com.wangwh.modules.login

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wangwh.commons.module.base.BaseActivity
import com.wangwh.commons.module.router.RouterIndex
import com.wangwh.modules.login.databinding.ModuleLoginActivityLoginBinding

/**
 * 作者: wenhui.w
 * 日期: 2024-10-24 14:31
 * 描述:
 */
@Route(path = RouterIndex.P_LOGIN, name = "登录页面")
class LoginActivity : BaseActivity() {

    private lateinit var binding: ModuleLoginActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModuleLoginActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.myBtn.setOnClickListener {
            ARouter.getInstance().build(RouterIndex.P_MY).navigation()
            finish()
        }

        binding.appBtn.setOnClickListener {
            ARouter.getInstance().build(RouterIndex.P_MAIN).navigation()
            finish()
        }
    }
}