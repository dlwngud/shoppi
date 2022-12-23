package com.dlwngud.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dlwngud.app.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 기본 color를 참조하지 않고 원래 색으로 표시
        binding.navigationMain.itemIconTintList = null

        val navController = supportFragmentManager.findFragmentById(binding.containerMain.id)?.findNavController()
        navController?.let {
            binding.navigationMain.setupWithNavController(it)
        }
    }

}