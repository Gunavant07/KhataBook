package com.example.khatabook.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.khatabook.fragment.homefm.Suppliers
import com.example.khatabook.fragment.homefm.customer

class Tabfmadapter(fm: FragmentManager?) :FragmentPagerAdapter(fm!!) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {

        val s1 =when(position)
        {
            0-> customer()
            1-> Suppliers()
            else -> customer()
        }
        return s1
    }
}