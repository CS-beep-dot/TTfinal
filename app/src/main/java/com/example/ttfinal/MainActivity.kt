package com.example.ttfinal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ttfinal.adapter.VideoListAdapter
import com.example.ttfinal.databinding.ActivityMainBinding
import com.example.ttfinal.model.VideoModel
import com.example.ttfinal.util.UiUtil
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomNavBar.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_menu_home -> {
                    UiUtil.showToast(this, "Home")
                    //home act
                }
                R.id.bottom_menu_add_video -> {
                    UiUtil.showToast(this, "Add Video")
                    //add vid acti
                }
                R.id.bottom_menu_profile -> {
                    UiUtil.showToast(this, "Profile")
                    //profile act
                }
            }
            false
        }
        setupViewPager()
    }

    private fun setupViewPager(){
        val options = FirestoreRecyclerOptions.Builder<VideoModel>()
            .setQuery(
                Firebase.firestore.collection("videos"),
                VideoModel::class.java
            ).build()
        adapter = VideoListAdapter(options)
        binding.viewPager.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.startListening()
    }
}