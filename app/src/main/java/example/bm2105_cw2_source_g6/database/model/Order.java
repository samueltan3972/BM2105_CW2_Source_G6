package example.bm2105_cw2_source_g6.database.model;

public class Order {
    public static final String TABLE_NAME = "orders";

    public static final String COLUMN_ID = "order_id";
    public static final String COLUMN_CUSTOMER_NAME = "customer_name";
    public static final String COLUMN_ORDER_DETAIL = "order_details";
    public static final String COLUMN_TOTAL_PRICE = "order_total_price";
    public static final String COLUMN_DATETIME = "order_datetime";
    public static final String COLUMN_REVIEW = "order_review";

    private int order_id;
    private String customer_name;
    private String order_details;
    private double order_total_price;
    private String order_datetime;
    private String order_review;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_CUSTOMER_NAME + " TEXT,"
                    + COLUMN_ORDER_DETAIL + " TEXT,"
                    + COLUMN_TOTAL_PRICE + " REAL,"
                    + COLUMN_DATETIME + " TEXT,"
                    + COLUMN_REVIEW + " TEXT"
                    + ")";

    public Order() {
        this.order_id = 0;
        this.customer_name = "";
        this.order_details = "";
        this.order_total_price = 0;
        this.order_datetime = "";
        this.order_review = "";
    }

    public Order(int order_id, String customer_name, String order_details, double order_total_price, String order_datetime, String order_review) {
        this.order_id = order_id;
        this.customer_name = customer_name;
        this.order_details = order_details;
        this.order_total_price = order_total_price;
        this.order_datetime = order_datetime;
        this.order_review = order_review;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getOrder_details() {
        return order_details;
    }

    public void setOrder_details(String order_details) {
        this.order_details = order_details;
    }

    public double getOrder_total_price() {
        return order_total_price;
    }

    public void setOrder_total_price(double order_total_price) {
        this.order_total_price = order_total_price;
    }

    public String getOrder_datetime() {
        return order_datetime;
    }

    public void setOrder_datetime(String order_datetime) {
        this.order_datetime = order_datetime;
    }

    public String getOrder_review() {
        return order_review;
    }

    public void setOrder_review(String order_review) {
        this.order_review = order_review;
    }
}
