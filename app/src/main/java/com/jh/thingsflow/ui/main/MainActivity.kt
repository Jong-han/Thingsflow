package com.jh.thingsflow.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jh.thingsflow.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}