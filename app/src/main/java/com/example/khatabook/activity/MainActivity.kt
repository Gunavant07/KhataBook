package com.example.khatabook.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.khatabook.R
import com.example.khatabook.adapter.TabAdapter
import com.example.khatabook.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var bind :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.tabbar.addTab(bind.tabbar.newTab().setIcon(R.drawable.group))
        bind.tabbar.addTab(bind.tabbar.newTab().setIcon(R.drawable.rupee))
        bind.tabbar.addTab(bind.tabbar.newTab().setIcon(R.drawable.product))
        bind.tabbar.addTab(bind.tabbar.newTab().setIcon(R.drawable.more))

        var tabAdapter = TabAdapter(supportFragmentManager)
        bind.viewpager.adapter=tabAdapter
        bind.viewpager.setOnPageChangeListener(

            TabLayout.TabLayoutOnPageChangeListener(
                bind.tabbar
            )
        )
        bind.tabbar.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                    bind.viewpager.currentItem=tab!!.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }


        })
    }
}