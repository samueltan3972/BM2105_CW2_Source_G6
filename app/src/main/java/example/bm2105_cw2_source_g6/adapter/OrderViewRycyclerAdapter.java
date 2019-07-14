package example.bm2105_cw2_source_g6.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import example.bm2105_cw2_source_g6.OrderView;
import example.bm2105_cw2_source_g6.R;
import example.bm2105_cw2_source_g6.database.model.Order;
import example.bm2105_cw2_source_g6.database.model.Product;
import example.bm2105_cw2_source_g6.viewHolder.CartViewHolder;
import example.bm2105_cw2_source_g6.viewHolder.MenuViewHolder;
import example.bm2105_cw2_source_g6.viewHolder.OrderViewHolder;

public class OrderViewRycyclerAdapter extends RecyclerView.Adapter<OrderViewHolder>{
    ArrayList<Order> orderlist;
    Context context;

    public OrderViewRycyclerAdapter(ArrayList<Order> orderList) {
        this.orderlist = orderList;
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }
}


