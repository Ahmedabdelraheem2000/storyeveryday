package com.halawy.Story.ui.StoryActivity.ActivityStory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.halawy.Story.R

class ActivityStory : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitystory)
        val naveHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = naveHostFragment.navController

    }

}