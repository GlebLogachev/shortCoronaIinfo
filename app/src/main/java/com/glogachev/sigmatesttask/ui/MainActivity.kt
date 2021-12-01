package com.glogachev.sigmatesttask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glogachev.sigmatesttask.App
import com.glogachev.sigmatesttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        App.component.inject(this)
        setContentView(binding.root)
    }
}