package com.example.android_ecommerce_app

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.example.android_ecommerce_app.model.Product
import com.example.android_ecommerce_app.repos.ProductsRepository
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val categories = listOf("Jeans", "Socks", "Suits", "Skirts", "Dresses", "Jeans", "Socks", "Pants", "Jackets", "Daniel")
        root.categoriesRecyclerView.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity, androidx.recyclerview.widget.RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productsRepository = ProductsRepository().getAllProducts()
        loadRecyclerView(productsRepository)
        searchButton.setOnClickListener {
            loadRecyclerView(ProductsRepository().searchForProducts(searchTerm.text.toString()))
        }
    }


    fun loadRecyclerView(productsRepository: Single<List<Product>>) {
        val single = productsRepository
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                d("daniel", "success :)")
                recycler_view.apply {
                    layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(it) { extraTitle, extraImageUrl, photoView ->
                        val intent = Intent(activity, ProductDetails::class.java)
                        intent.putExtra("title", extraTitle)
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity as AppCompatActivity, photoView, "photoToAnimate")
                        startActivity(intent, options.toBundle())
                    }
                }
                progressBar.visibility = View.GONE
            }, {
                d("daniel", " error :( ${it.message}")
            })
    }
}