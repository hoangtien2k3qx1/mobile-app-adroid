package com.hoangtien2k3.food_order_app.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoangtien2k3.food_order_app.Domain.FoodDomain
import com.hoangtien2k3.food_order_app.R

class PopularAdaptor(private val popularFoodDomainList: ArrayList<FoodDomain>) : RecyclerView.Adapter<PopularAdaptor.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val fee: TextView = itemView.findViewById(R.id.fee)
        val pic: ImageView = itemView.findViewById(R.id.pic)
        val addBtn: TextView = itemView.findViewById(R.id.addBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.viewholder_popular, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return popularFoodDomainList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = popularFoodDomainList[position].title
        holder.fee.text = "%f".format(popularFoodDomainList[position].fee)

        val drawableResourceId = holder.itemView.context.resources.getIdentifier(popularFoodDomainList[position].pic, "drawable", holder.itemView.context.packageName)

        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.pic)
    }
}
