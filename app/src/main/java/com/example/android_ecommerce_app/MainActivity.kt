package com.example.android_ecommerce_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_ecommerce_app.model.Product

import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val  product = arrayListOf<Product>()

        for(i in 0..100){
            product.add(Product("Nahom " , "Organic apply" ,"https://www.pinterest.com/pin/499477414902291913/\n", "1.99", "True"))
        }


        recycler_view.apply{
            layoutManager = GridLayoutManager(this@MainActivity, "2")
            adapter = ProductsAdapter(Product())
        }
    }
}

private fun RecyclerView.GridLayoutManager(
    mainActivity: MainActivity,
    s: String
): GridLayoutManager {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}



