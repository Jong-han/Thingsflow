package com.jh.thingsflow.ui.main

import androidx.activity.viewModels
import com.jh.thingsflow.BR
import com.jh.thingsflow.R
import com.jh.thingsflow.base.BaseActivity
import com.jh.thingsflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override val bindingVariable: Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViewAndEvent() {
    }
}