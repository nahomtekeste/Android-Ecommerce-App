package com.example.android_ecommerce_app

import android.os.Bundle

import android.util.Log.d

import android.view.LayoutInflater

import android.view.View

import android.view.ViewGroup
import androidx.room.Room
import com.example.android_ecommerce_app.database.AppDatabase
import kotlinx.android.synthetic.main.activity_admin_fragment.*


class AdminFragment : androidx.fragment.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_admin_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton.setOnClickListener {

            val title = productTitle.text
            d("Nahom", "button pressed :) with text of $title")
            doAsync {
                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build().also {
                    it.productDao().insertAll(ProductFromDatabase(null, title.toString(), 12.34))
                }
                uiThread {
                    d("Nahom", "redirecting to home screen")
                }
            }
        }
    }
}
