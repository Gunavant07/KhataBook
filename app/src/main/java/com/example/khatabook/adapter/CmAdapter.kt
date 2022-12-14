package com.example.khatabook.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.khatabook.activity.Customerdetail
import com.example.khatabook.activity.DBHelper
import com.example.khatabook.DataClass.ModelData
import com.example.khatabook.R
import com.example.khatabook.activity.Viewdetails
import com.example.khatabook.adapter.CmAdapter.ViewData
import com.example.khatabook.fragment.homefm.customer
import kotlin.collections.ArrayList

class CmAdapter(val activity: FragmentActivity?, val a1: customer, var l1: ArrayList<ModelData>, val l3: ArrayList<ModelData>) :
    RecyclerView.Adapter<ViewData>() {

    class ViewData(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var txtname = itemview.findViewById<TextView>(R.id.txtname)
        var txtmobile = itemview.findViewById<TextView>(R.id.txtmobile)
        var txtdate = itemview.findViewById<TextView>(R.id.txtdate)
        var txtgave = itemview.findViewById<TextView>(R.id.txtgave)
        var txtgot = itemview.findViewById<TextView>(R.id.txtgot)
        var edit = itemview.findViewById<Button>(R.id.edit)
        var delete = itemview.findViewById<Button>(R.id.delete)
        var viewdetail = itemview.findViewById<RelativeLayout>(R.id.viewdetail)
        lateinit var dbHelper: DBHelper
        lateinit var customer: customer

    }

    fun UpdateData(l3: ArrayList<ModelData>) {
        l1=l3
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {

        var view = LayoutInflater.from(activity).inflate(R.layout.customerlist, parent, false)
        return ViewData(view)
    }
    override fun onBindViewHolder(holder: ViewData, position: Int) {

        holder.txtname.setText(l1[position].name).toString()
        holder.txtmobile.setText(l1[position].mobile).toString()
        holder.txtdate.setText(l1[position].date).toString()

        if (l1[position].status == "0")
        {
            holder.txtgot.text = "₹";
            holder.txtgave.text = l1[position].amount
        }
        else if (l1[position].status == "1") {
            holder.txtgave.text = "₹";
            holder.txtgot.text = l1[position].amount
        }
        holder.viewdetail.setOnClickListener {
            var intent =Intent(activity,Viewdetails::class.java)

            intent.putExtra("Name", l1[position].name)
            intent.putExtra("Mobile", l1[position].mobile)
            intent.putExtra("Item", l1[position].item)
            intent.putExtra("Quantity", l1[position].quantity)
            intent.putExtra("Date", l1[position].date)
            activity?.startActivity(intent)
        }

        val db = DBHelper(activity)


        holder.edit.setOnClickListener {

            var intent = Intent(activity, Customerdetail::class.java)

            intent.putExtra("Id", l1[position].id)
            intent.putExtra("Name", l1[position].name)
            intent.putExtra("Mobile", l1[position].mobile)
            intent.putExtra("Item", l1[position].item)
            intent.putExtra("Quantity", l1[position].quantity)
            intent.putExtra("Give", l1[position].amount)
            intent.putExtra("Got", l1[position].amount)
            intent.putExtra("Date", l1[position].date)
            activity?.startActivity(intent)
        }
        holder.delete.setOnClickListener {
            db.delete(l1[position].id)
            l1.removeAt(position)
            a1.addition()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return l1.size
    }
}
