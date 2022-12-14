package com.example.khatabook.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat.START
import com.example.khatabook.adapter.CmAdapter
import com.example.khatabook.databinding.ActivityViewdetailsBinding
import com.example.khatabook.fragment.Homefragment
import com.google.android.material.navigation.NavigationView

class Viewdetails : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{

    lateinit var Name: String
    lateinit var Id: String
    lateinit var Gave: String
    lateinit var Got: String
    lateinit var cmAdapter: CmAdapter
    lateinit var Mobile: String
    lateinit var Item: String
    lateinit var Quantity: String
    lateinit var Date: String
    lateinit var binding: ActivityViewdetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewdetailsBinding.inflate(layoutInflater)
        getData()
        initSet()
        initClick()
        setContentView(binding.root)


    }

    private fun initClick() {
        binding.back.setOnClickListener {
            var intent = Intent(this,Homefragment::class.java)
            finish()
        }
        binding.call.setOnClickListener {

            val uri = Uri.parse("tell:+91$Mobile")
            Log.e("TAG", "onClick:========$uri")
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = uri
            startActivity(intent)
        }
        binding.menu.setOnClickListener {
            binding.drawer.open()
        }

    }
    fun getData() {

        Name = intent.getStringExtra("Name").toString()
        Mobile = intent.getStringExtra("Mobile").toString()
        Item = intent.getStringExtra("Item").toString()
        Quantity = intent.getStringExtra("Quantity").toString()
        Date = intent.getStringExtra("Date").toString()
    }

    fun initSet() {
        binding.txtname.setText(Name).toString()
        binding.mobile.setText(Mobile).toString()
        binding.item.setText(Item).toString()
        binding.quantity.setText(Quantity).toString()
        binding.date.setText(Date).toString()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}