package com.example.android_ecommerce_app.repos

import com.example.android_ecommerce_app.model.Product
import com.google.gson.Gson
import java.net.URL

class ProductsRepository {
    fun getAllProducts(): Single<List<Product>> {
        return Single.create<List<Product>> {
            it.onSuccess(fetchProducts())
        }
    }
    fun searchForProducts(term: String): Single<List<Product>> {
        return Single.create<List<Product>> {
            val filteredProducts = fetchProducts().filter { it.title.contains(term, true) }
            it.onSuccess(filteredProducts)
        }
    }

    fun getProductByName(name: String): Single<Product> {
        return Single.create<Product> {
            val product = fetchProducts().first { it.title == name }
            it.onSuccess(product)
        }
    }

    fun fetchProducts(): List<Product> {
        val json = URL("https://gist.githubusercontent.com/danielmalone/df34a33a06e985d85f2ba7f6e635c600/raw/df0aeaea2def4aa1c9b0f266d032b6733c3fbaaa/shopping_products.json").readText()
        return Gson().fromJson(json, Array<Product>::class.java).toList()
    }
}