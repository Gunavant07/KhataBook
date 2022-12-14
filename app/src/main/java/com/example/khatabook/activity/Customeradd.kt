package com.example.khatabook.activity

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import com.example.khatabook.DataClass.ModelData
import com.example.khatabook.R
import com.example.khatabook.adapter.CmAdapter
import com.example.khatabook.databinding.ActivityCustomeraddBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Customeradd : AppCompatActivity() {

    lateinit var binding: ActivityCustomeraddBinding
    lateinit var dbHelper: DBHelper
    lateinit var cmadapter:CmAdapter

    lateinit var list: ArrayList<ModelData>

    var cal = Calendar.getInstance()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCustomeraddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper= DBHelper(this)
        initClick()
        DatePick()
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
                this@Customeradd,
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
        binding.txtdate.text = sdf.format(cal.time)
    }

    private fun initClick() {
        binding.btndone.setOnClickListener {

            var status =0;
             if(binding.rg1.checkedRadioButtonId == R.id.rdgive)
             {
                  status =0 ;
             }
            else
             {
                 status=1;
             }

            dbHelper.insertData(binding.edtname.text.toString(),binding.edtmobile.text.toString(),
                binding.edtitem.text.toString(), binding.edtquantity.text.toString(),
                binding.edtamount.text.toString(),binding.txtdate.text.toString(),status.toString())

            list =dbHelper.ReadData()
            finish()
        }
    }
}