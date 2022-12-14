package com.example.khatabook.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.khatabook.DataClass.ModelData
import com.example.khatabook.adapter.CmAdapter
import com.example.khatabook.databinding.ActivityCustomerdetailBinding
import com.example.khatabook.fragment.homefm.customer
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Attributes.Name
import kotlin.collections.ArrayList

class Customerdetail : AppCompatActivity() {

    lateinit var l1: ArrayList<ModelData>
    lateinit var cmadapter: CmAdapter
    lateinit var dbHelper: DBHelper
    lateinit var Name: String
    lateinit var Id: String
    lateinit var Gave: String
    lateinit var Got: String
    lateinit var Mobile: String
    lateinit var Item: String
    lateinit var Quantity: String
    lateinit var Date: String
    var cal = Calendar.getInstance()


    lateinit var binding: ActivityCustomerdetailBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerdetailBinding.inflate(layoutInflater)
        dbHelper = DBHelper(this)
        getData()
        DatePick()
        initClick()
        initSet()
        setContentView(binding.root)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun DatePick() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        binding.imgCalendar.setOnClickListener {
            DatePickerDialog(
                this@Customerdetail,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateDateInView() {
        val myFormat = "dd/MM/yy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.txtdate1.text = sdf.format(cal.time)
    }

    fun getData() {
        Id = intent.getStringExtra("Id").toString()
        Name = intent.getStringExtra("Name").toString()
        Date = intent.getStringExtra("Date").toString()
        Gave = intent.getStringExtra("Give").toString()
        Got = intent.getStringExtra("Got").toString()
        Mobile = intent.getStringExtra("Mobile").toString()
        Item = intent.getStringExtra("Item").toString()
        Quantity = intent.getStringExtra("Quantity").toString()
    }

    fun initSet() {
        binding.edtname1.setText(Name).toString()
        binding.edtamount1.setText(Gave)
        binding.edtmobile1.setText(Mobile)
        binding.edtitem1.setText(Item)
        binding.edtquantity1.setText(Quantity)
        binding.txtdate1.setText(Date).toString()
    }

    private fun initClick() {

        binding.btndone.setOnClickListener {

            val dbHelper = DBHelper(this)
            dbHelper.update(Id,
                binding.edtname1.text.toString(),
                binding.edtmobile1.text.toString(),
                binding.edtitem1.text.toString(),
                binding.edtquantity1.text.toString(),
                binding.edtamount1.text.toString(),
                binding.txtdate1.text.toString()
            )
            l1 = dbHelper.ReadData()
            finish()
        }
    }
}