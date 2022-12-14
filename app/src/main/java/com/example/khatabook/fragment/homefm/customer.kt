package com.example.khatabook.fragment.homefm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.DataClass.ModelData
import com.example.khatabook.R
import com.example.khatabook.activity.Customeradd
import com.example.khatabook.activity.DBHelper
import com.example.khatabook.adapter.CmAdapter
import com.example.khatabook.databinding.FragmentCustomerBinding
import java.util.*


class customer : Fragment() {

    lateinit var binding: FragmentCustomerBinding
    lateinit var cmadapter: CmAdapter
    var l1 = (arrayListOf<ModelData>())
    var l3 = (arrayListOf<ModelData>())
    lateinit var dbHelper: DBHelper
    var sum: Int = 0
    var sum1: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?, ): View? {
        binding = FragmentCustomerBinding.bind(inflater.inflate(R.layout.fragment_customer, container, false))
        dbHelper = DBHelper(this@customer.activity)
        initOnClick()
        rvSetup(l1)
        return binding.root

    }

    private fun rvSetup(l1: ArrayList<ModelData>) {
        cmadapter = CmAdapter(activity, this, l1,l3)
        var lm = LinearLayoutManager(activity)
        binding.recycleview.layoutManager = lm
        binding.recycleview.adapter = cmadapter
    }

    private fun initOnClick() {
        binding.cmadd.setOnClickListener {
            val intent = Intent(activity, Customeradd::class.java)
            startActivity(intent)
        }

        binding.search.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                l3.clear()
                for (i in l1) {
                    if (i.name.lowercase().contains(newText?.lowercase(Locale.getDefault())!!))
                    {
                        l3.add(i)
                    }
            }

                cmadapter.UpdateData(l3)
                return false
        }

        })
        binding.filter.setOnClickListener {


        }

    }

    override fun onStart() {
        super.onStart()
        l1 = dbHelper.ReadData()
        rvSetup(l1)
        addition()

    }

    fun addition() {
        sum=0
        sum1=0

        for (i in l1) {
            if (i.status == "0") {
                sum += i.amount.toInt()
            } else {
                sum1 += i.amount.toInt()
            }
        }
        binding.givemoney.setText("$sum").toString()
        binding.gotmoney.setText("$sum1").toString()
    }
}