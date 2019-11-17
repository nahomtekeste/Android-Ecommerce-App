package com.example.android_ecommerce_app

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_ecommerce_app.repos.ProductsRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)#

        val title = intent.getStringExtra("title")
        val product = ProductsRepository().getProductByName(title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                product_name.text = it.title
                Picasso.get().load(it.photoUrl).into(photo)
                thePriceOfProduct.text = "$${it.price}"
            }, {})

        availability.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hey, $title is in stock!")

                .setPositiveButton("OK") { p0, p1 ->

                }
                .create()
                .show()
        }
    }
}