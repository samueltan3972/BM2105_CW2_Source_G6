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

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.R;
import example.bm2105_cw2_source_g6.viewHolder.CartViewHolder;

public class CartViewRecyclerAdapter extends RecyclerView.Adapter<CartViewHolder> {
    Context context;

    public CartViewRecyclerAdapter() {
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);
        CartViewHolder vh = new CartViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        InputStream is;

        try {
            is = context.getAssets().open(Common.cart.getOrderDetailsList().get(position).getProduct().getProduct_image());
        } catch (IOException e){
            is = null;
        }

        holder.cart_food_image.setImageDrawable(Drawable.createFromStream(is, ""));
        holder.cart_food_name.setText(Common.cart.getOrderDetailsList().get(position).getProduct().getProduct_name());
        holder.cart_price.setText("RM " + roundOffTo2DecPlaces(Common.cart.getOrderDetailsList().get(position).getProduct().getProduct_price()));
        holder.cart_btn_counter.setNumber("" + Common.cart.getOrderDetailsList().get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return Common.cart.getCartItemNum();
    }

    private String roundOffTo2DecPlaces(double val)
    {
        return String.format("%.2f", val);
    }
}
