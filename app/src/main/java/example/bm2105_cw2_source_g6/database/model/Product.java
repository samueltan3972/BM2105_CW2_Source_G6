package example.bm2105_cw2_source_g6.database.model;

public class Product {
    public static final String TABLE_NAME = "product";

    public static final String COLUMN_ID = "product_id";
    public static final String COLUMN_NAME = "product_name";
    public static final String COLUMN_IMAGE = "product_image";
    public static final String COLUMN_PRICE = "product_price";

    private int product_id;
    private String product_name;
    private String product_image;
    private double product_price;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_IMAGE + " TEXT,"
                    + COLUMN_PRICE + " REAL"
                    + ")";

    public Product() {
    }

    public Product(int product_id, String product_name, String product_image, double product_price){
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_image = product_image;
        this.product_price = product_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
}
