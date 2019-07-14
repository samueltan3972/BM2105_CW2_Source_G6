package example.bm2105_cw2_source_g6.database.model;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.database.DatabaseHelper;

public class Cart {
    private ArrayList<OrderDetail> orderDetailsList;

    public Cart(){
        orderDetailsList = new ArrayList<OrderDetail>();
    }

    public void addToCart(Product product, int quantity){
//        for(OrderDetail od : orderDetailsList){
//            if(od.getProduct().equals(product)){
//                od.addQuantity(quantity);
//                return ;
//            }
//        }
        orderDetailsList.add(new OrderDetail(product, quantity));
    }

    public void removeItem(int position){
        orderDetailsList.remove(position);
    }

    public void setItemQuantity(String product_name, int quantity){
        for(OrderDetail od : orderDetailsList) {
            if (od.getProduct().getProduct_name().equals(product_name)) {
                od.setQuantity(quantity);
                return ;
            }
        }
    }

    public double calcTotalPrice() {
        double totalPrice = 0;

        for(OrderDetail od : orderDetailsList){
            totalPrice += od.getProduct().getProduct_price() * od.getQuantity();
        }

        return totalPrice;
    }

    public void placeOrder(Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        // Convert the order details list to json
        Gson gson = new Gson();
        String json = gson.toJson(orderDetailsList);

        databaseHelper.placeOrder(Common.currentUser.getUserName(), json, this.calcTotalPrice());
    }

    public int getCartItemNum(){
        return orderDetailsList.size();
    }

    public ArrayList<OrderDetail> getOrderDetailsList() {
        return orderDetailsList;
    }
}
