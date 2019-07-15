package example.bm2105_cw2_source_g6.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.database.model.Order;
import example.bm2105_cw2_source_g6.database.model.OrderDetail;
import example.bm2105_cw2_source_g6.database.model.Product;
import example.bm2105_cw2_source_g6.database.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "inti_cafe_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Database
    @Override
    public void onCreate(SQLiteDatabase db){
        // create user table
        db.execSQL(User.CREATE_TABLE);
        db.execSQL(Product.CREATE_TABLE);
        db.execSQL(Order.CREATE_TABLE);

        db.execSQL(Product.INSERT_PRODUCT);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertUser(String username, String contact_num, String password) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(User.COLUMN_NAME, username);
        values.put(User.COLUMN_CONTACT_NUM, contact_num);
        values.put(User.COLUMN_PASSWORD, password);

        // insert row
        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public User getUser(String username) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_NAME, User.COLUMN_CONTACT_NUM, User.COLUMN_PASSWORD},
                User.COLUMN_NAME + "=?",
                new String[]{String.valueOf(username)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user;

        try {
            // prepare note object
            user = new User(
                    cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(User.COLUMN_CONTACT_NUM)),
                    cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));
        } catch (Exception e){
            user = new User();
        }

        // close the db connection
        cursor.close();

        return user;
    }

    public void EditContact( String contact){
        SQLiteDatabase db = this.getWritableDatabase();

        String strSQL = "UPDATE " + User.TABLE_NAME + " SET "+
                User.COLUMN_CONTACT_NUM +" = '"+ contact +
                "' WHERE " + User.COLUMN_ID + " = "+ Common.currentUser.getId();

        db.execSQL(strSQL);

    }

    public ArrayList<Product> getProduct(){
        ArrayList<Product> productList = new ArrayList<Product>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(Product.SELECT_QUERY, null);
        cursor.moveToFirst();

        Product product;

        if (cursor.getCount() > 0) {
            do {
                product = new Product(
                    cursor.getString(cursor.getColumnIndex(Product.COLUMN_CODE)),
                    cursor.getString(cursor.getColumnIndex(Product.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(Product.COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(Product.COLUMN_IMAGE)),
                    cursor.getDouble(cursor.getColumnIndex(Product.COLUMN_PRICE))
                );

                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productList;
    }

    public void placeOrder(String customer_name, String order_detail, double total_price){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        values.put(Order.COLUMN_CUSTOMER_NAME, customer_name);
        values.put(Order.COLUMN_ORDER_DETAIL, order_detail);
        values.put(Order.COLUMN_TOTAL_PRICE, total_price);
        values.put(Order.COLUMN_DATETIME, dateFormat.format(date));

        // insert row
        db.insert(Order.TABLE_NAME, null, values);

        // close db connection
        db.close();
    }

    public ArrayList<Order> getOrder(){

        ArrayList<Order> orderList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(Order.SELECT_QUERY,
                null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        Double d;

        Order order;
        ArrayList<OrderDetail> arrayList;

        if (cursor.getCount() > 0) {
            do {

               String json= cursor.getString(cursor.getColumnIndex(Order.COLUMN_ORDER_DETAIL));
                 arrayList = gson.fromJson(json, ArrayList.class);
                 d = Double.parseDouble(cursor.getString(cursor.getColumnIndex(Order.COLUMN_TOTAL_PRICE)));

                order = new Order(
                        cursor.getInt(Integer.valueOf(cursor.getString(cursor.getColumnIndex(Order.COLUMN_ID)))),
                        cursor.getString(cursor.getColumnIndex(Order.COLUMN_CUSTOMER_NAME)),
                        arrayList,
                        d,
                        cursor.getString(cursor.getColumnIndex(Order.COLUMN_DATETIME)),
                        cursor.getString(cursor.getColumnIndex(Order.COLUMN_REVIEW))

                );


                orderList.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return orderList;

    }

}
