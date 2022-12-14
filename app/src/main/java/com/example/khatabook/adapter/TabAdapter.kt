package com.example.khatabook.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.khatabook.fragment.Homefragment
import com.example.khatabook.fragment.Items
import com.example.khatabook.fragment.Money
import com.example.khatabook.fragment.More

class TabAdapter(fm: FragmentManager) :FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        val f1= when(position)
        {
            0->Homefragment()
            1->Money()
            2->Items()
            3->More()
            else->Homefragment()
        }
        return f1
    }
}