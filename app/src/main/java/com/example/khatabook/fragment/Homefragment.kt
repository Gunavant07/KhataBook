package com.example.khatabook.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.khatabook.R
import com.example.khatabook.adapter.Tabfmadapter
import com.example.khatabook.databinding.FragmentHomefragmentBinding
import com.google.android.material.tabs.TabLayout


class Homefragment : Fragment() {
    lateinit var binding: FragmentHomefragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,): View? {
        binding= FragmentHomefragmentBinding.bind(inflater.inflate(R.layout.fragment_homefragment,container,false))

        binding.tabbar.addTab(binding.tabbar.newTab().setText("Customers"))
        binding.tabbar.addTab(binding.tabbar.newTab().setText("Suppliers"))

        var tabfmAdapter =Tabfmadapter(childFragmentManager)
        binding.viewpager1.adapter=tabfmAdapter
        binding.viewpager1.setOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                binding.tabbar
            )
        )

        binding.tabbar.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewpager1.currentItem=tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        return binding.root


    }


}
