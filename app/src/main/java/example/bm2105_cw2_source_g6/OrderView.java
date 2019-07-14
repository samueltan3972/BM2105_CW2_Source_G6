package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.adapter.OrderViewRycyclerAdapter;
import example.bm2105_cw2_source_g6.database.DatabaseHelper;
import example.bm2105_cw2_source_g6.database.model.Order;

public class OrderView extends AppCompatActivity {

    private TextView orderlist_title;
    private TextView order_no_item_text;
    private RecyclerView recycler_order;
    private RecyclerView.Adapter orderViewAdapter;

    private DatabaseHelper databaseHelper;
    private ArrayList<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
         orderlist_title =findViewById(R.id.orderlist_title);
         order_no_item_text = findViewById(R.id.orderlist_no_item);

         orderlist_title.setTypeface(Typeface.createFromAsset(getAssets(),
                 "open-sans-extrabold.ttf"));

        databaseHelper = new DatabaseHelper(this);
        orderList = databaseHelper.getOrder();

        if(orderList.size() != 0)
            order_no_item_text.setVisibility(View.INVISIBLE);

        recycler_order = findViewById(R.id.recycler_orderlist);
        recycler_order.setHasFixedSize(true);
        recycler_order.setLayoutManager(new LinearLayoutManager(this));
        orderViewAdapter = new OrderViewRycyclerAdapter(orderList);
        recycler_order.setAdapter(orderViewAdapter);

    }
}
