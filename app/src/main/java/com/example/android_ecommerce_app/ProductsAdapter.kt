package com.example.android_ecommerce_app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_ecommerce_app.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*

//inthis class we are extends the recycler view from the adapter
class ProductsAdapter(
    private val products: Product,
    private val onClickProduct: (title: String, photoUrl: String, photoView: View) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    // this is the following funtions are that are the implementation of the adpater recycler view
    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {

        // this funtion is allow us to show the products show from the our data
        val product = products[position]
        Picasso.get().load(product.photoUrl).into(holder.image)
        holder.title.text = product.title
        holder.price.text = product.price.toString()
        if (product.isOnSale) {
            holder.saleImageView.visibility = View.VISIBLE
        } else {
            holder.saleImageView.visibility = View.GONE
        }

        holder.image.setOnClickListener {
            onClickProduct.invoke(product.title, product.photoUrl, holder.image)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        // this funtion here allow the user to whenever a ptoduct is clicked it should go directly each details

        var holder= ViewHolder(view)
        view.setOnClickListener{
            var intent = Intent(parent.context , ProductDetails::class.java)
            intent.putExtra("name" , products[holder.adapterPosition].title)
            parent.context.startActivities(arrayOf(intent))

        }
        return holder
    }
    // this funtion are the numbers of the products
    override fun getItemCount() = products.size

    // this a class iof view holder in adapter
    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val saleImageView = itemView.saleImageView
    }
}