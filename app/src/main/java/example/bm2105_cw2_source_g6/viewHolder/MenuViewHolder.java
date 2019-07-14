package example.bm2105_cw2_source_g6.viewHolder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import example.bm2105_cw2_source_g6.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView imageView;

    public MenuViewHolder(View itemView){
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.product_image);
    }

    @Override
    public void onClick(View view){

    }
}
