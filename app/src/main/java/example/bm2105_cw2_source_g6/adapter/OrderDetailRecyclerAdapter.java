package example.bm2105_cw2_source_g6.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import example.bm2105_cw2_source_g6.database.model.OrderDetail;
import example.bm2105_cw2_source_g6.viewHolder.OrderDetailViewHolder;

public class OrderDetailRecyclerAdapter  extends RecyclerView.Adapter<OrderDetailViewHolder> {
    private ArrayList<OrderDetail> orderDetailsList;

    public OrderDetailRecyclerAdapter(ArrayList<OrderDetail> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }
}
