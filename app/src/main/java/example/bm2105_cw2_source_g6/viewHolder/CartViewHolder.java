package example.bm2105_cw2_source_g6.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import example.bm2105_cw2_source_g6.CartView;
import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView cart_food_image;
    public TextView cart_food_name;
    public TextView cart_price;
    public ElegantNumberButton cart_btn_counter;

    private Context mContext;

    public CartViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        cart_food_image = itemView.findViewById(R.id.cart_food_image);
        cart_food_name = itemView.findViewById(R.id.cart_food_name);
        cart_price = itemView.findViewById(R.id.cart_price);
        cart_btn_counter =  itemView.findViewById(R.id.cart_btn_counter);

        this.mContext = context;

        cart_btn_counter.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantity = cart_btn_counter.getNumber();
                Common.cart.setItemQuantity(cart_food_name.getText().toString(), Integer.parseInt(quantity));

                if (mContext instanceof CartView) {
                    ((CartView)mContext).setTotalPriceText();
                }
            }
        });
    }

    @Override
    public void onClick(View view){

    }
}
