package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import example.bm2105_cw2_source_g6.Common.Common;

public class OrderView extends AppCompatActivity {

    private TextView orderlist_title;
    private TextView order_no_item_text;
    private RecyclerView recycler_order;
    private RecyclerView.Adapter orderViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
         orderlist_title =findViewById(R.id.orderlist_title);
         order_no_item_text = findViewById(R.id.orderlist_no_item);

         orderlist_title.setTypeface(Typeface.createFromAsset(getAssets(),
                 "open-sans-extrabold.ttf"));

        if(Common.cart.getCartItemNum() != 0)
            order_no_item_text.setVisibility(View.INVISIBLE);

        recycler_order = findViewById(R.id.recycler_orderlist);




    }
}
