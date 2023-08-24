package com.hoangtien2k3.food_order.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hoangtien2k3.food_order.Domain.CategoryDomain;
import com.hoangtien2k3.food_order.Domain.FoodDomain;
import com.hoangtien2k3.food_order.R;

import java.util.ArrayList;

public class RecommentedAdapter extends RecyclerView.Adapter<RecommentedAdapter.ViewHolder> {
    ArrayList<FoodDomain> RecommendedDomains;

    public RecommentedAdapter(ArrayList<FoodDomain> RecommendedDomains) {
        this.RecommendedDomains = RecommendedDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recommented, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(RecommendedDomains.get(position).getTitle());
        holder.fee.setText(RecommendedDomains.get(position).getTitle());

        int drawableResourceId = holder
                .itemView
                .getContext()
                .getResources()
                .getIdentifier(RecommendedDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return RecommendedDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic;
        ImageView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            fee = itemView.findViewById(R.id.fee);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }

}
