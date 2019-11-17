package com.example.android_ecommerce_app.database

import androidx.room.RoomDatabase
import com.example.android_ecommerce_app.model.Product


abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): Product

}