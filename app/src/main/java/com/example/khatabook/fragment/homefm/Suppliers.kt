package com.example.khatabook.fragment.homefm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.khatabook.R
import com.example.khatabook.activity.Supplieradd
import com.example.khatabook.databinding.FragmentSuppliersBinding


class Suppliers : Fragment() {

    lateinit var binding :FragmentSuppliersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentSuppliersBinding.bind(inflater.inflate(R.layout.fragment_suppliers,container,false))
        initOnClick()
        return binding.root
    }

    private fun initOnClick() {
        binding.spadd.setOnClickListener {
            val intent =Intent(activity, Supplieradd::class.java)
            startActivity(intent)
        }
    }


}