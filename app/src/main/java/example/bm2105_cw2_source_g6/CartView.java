package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.adapter.CartViewRecyclerAdapter;
import example.bm2105_cw2_source_g6.adapter.ProductRecyclerAdapter;
import info.hoang8f.widget.FButton;

public class CartView extends AppCompatActivity {

    private TextView cart_title;
    private TextView no_item_text;
    private TextView total_price_text;
    private FButton placeOrder_btn;
    private RecyclerView recycler_cart;
    private RecyclerView.Adapter cartViewAdapter;

    public static Activity activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        activity = this;

        cart_title = findViewById(R.id.cart_title);
        cart_title.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));

        no_item_text = findViewById(R.id.cart_no_item);
        total_price_text = findViewById(R.id.cart_total_price);
        placeOrder_btn = findViewById(R.id.btn_placeOrder);

        if(Common.cart.getCartItemNum() != 0)
            no_item_text.setVisibility(View.INVISIBLE);

        if(Common.cart.getCartItemNum() == 0) {
            total_price_text.setVisibility(View.INVISIBLE);
            placeOrder_btn.setVisibility(View.INVISIBLE);
        }

        recycler_cart = findViewById(R.id.recycler_cart);
        recycler_cart.setHasFixedSize(true);
        recycler_cart.setLayoutManager(new LinearLayoutManager(this));
        cartViewAdapter = new CartViewRecyclerAdapter();
        recycler_cart.setAdapter(cartViewAdapter);

        setTotalPriceText();
    }

    public void setTotalPriceText(){
        total_price_text.setText("Total: RM "+ Common.cart.calcTotalPrice());
    }

    public void placeOrder(View view) {
        Common.cart.placeOrder(this);
        finish();

        Toast.makeText(this, "The order has been placed", Toast.LENGTH_SHORT).show();
    }
}
