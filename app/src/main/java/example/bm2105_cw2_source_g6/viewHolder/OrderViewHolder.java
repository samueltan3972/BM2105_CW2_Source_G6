package example.bm2105_cw2_source_g6.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import example.bm2105_cw2_source_g6.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView order_id_text;
    public TextView order_datetime_text;
    public RecyclerView recycler_order_detail;
    private RecyclerView.Adapter orderDetailAdapter;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        order_id_text = itemView.findViewById(R.id.order_id);
        order_datetime_text = itemView.findViewById(R.id.order_datetime);
        recycler_order_detail = itemView.findViewById(R.id.recycler_order_detail);

        recycler_order_detail.setHasFixedSize(true);
        recycler_order_detail.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
    }

    @Override
    public void onClick(View v) {


    }
}
