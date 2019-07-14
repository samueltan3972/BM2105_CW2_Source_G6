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

import example.bm2105_cw2_source_g6.R;
import example.bm2105_cw2_source_g6.database.model.Product;
import example.bm2105_cw2_source_g6.viewHolder.MenuViewHolder;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<MenuViewHolder> {
    ArrayList<Product> productList;
    Context context;

    public ProductRecyclerAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        MenuViewHolder vh = new MenuViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        InputStream is;

        try {
            is = context.getAssets().open(productList.get(position).getProduct_image());
        } catch (IOException e){
            is = null;
        }

        holder.imageView.setImageDrawable(Drawable.createFromStream(is, ""));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
