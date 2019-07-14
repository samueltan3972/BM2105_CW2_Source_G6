package example.bm2105_cw2_source_g6.viewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import example.bm2105_cw2_source_g6.R;

public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
    public ImageView order_detail_image;


    public OrderDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        order_detail_image = itemView.findViewById(R.id.order_detail_food_image);
    }
}
