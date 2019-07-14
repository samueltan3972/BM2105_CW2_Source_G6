package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.adapter.CartViewRecyclerAdapter;
import example.bm2105_cw2_source_g6.adapter.ProductRecyclerAdapter;

public class CartView extends AppCompatActivity {

    private TextView cart_title;
    private TextView no_item_text;
    private RecyclerView recycler_cart;
    private RecyclerView.Adapter cartViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        cart_title = findViewById(R.id.cart_title);
        cart_title.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));

        no_item_text = findViewById(R.id.cart_no_item);

        if(Common.cart.getCartItemNum() != 0)
            no_item_text.setVisibility(View.INVISIBLE);

        recycler_cart = findViewById(R.id.recycler_cart);
        recycler_cart.setHasFixedSize(true);
        recycler_cart.setLayoutManager(new LinearLayoutManager(this));
        cartViewAdapter = new CartViewRecyclerAdapter();
        recycler_cart.setAdapter(cartViewAdapter);
    }
}
