package example.bm2105_cw2_source_g6.database.model;

public class Product {
    public static final String TABLE_NAME = "product";

    public static final String COLUMN_CODE = "product_code";
    public static final String COLUMN_NAME = "product_name";
    public static final String COLUMN_DESCRIPTION = "product_desc";
    public static final String COLUMN_IMAGE = "product_image";
    public static final String COLUMN_PRICE = "product_price";

    private String product_code;
    private String product_name;
    private String product_desc;
    private String product_image;
    private double product_price;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_CODE + " TEXT PRIMARY KEY,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_IMAGE + " TEXT,"
                    + COLUMN_PRICE + " REAL"
                    + ")";

    // Insert dummy product
    public static final String INSERT_PRODUCT =
            "INSERT INTO " + TABLE_NAME + "(" + COLUMN_CODE + ", " + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ", " + COLUMN_IMAGE + ", " + COLUMN_PRICE + ") "
            + "VALUES('F001', 'Baked Stuffed Lobster', 'Bake until the lobster meat is opaque and the crumbs are crisp and golden brown', 'raw/lobster.jpg', 45 ), "
            + "('F002', 'Braised Mushrooms and Abalone', 'Prepare this special Braised Mushrooms and Abalone for the Chinese New Year or a special occasion', 'raw/abalone.jpg', 40 ), "
            + "('F003', 'Bird nest soup', 'The best-known use of edible birds nest is bird nest soup, a delicacy in Chinese cuisine. When dissolved in water, the birds nests have a gelatinous texture used for soup or sweet soup (tong sui).', 'raw/bird_nest.jpg', 30 ), "
            + "('D001', 'Henri IV Dudognon Heritage Cognac Grande Champagne', 'Henri IV Dudognon Heritage Cognac Grande Champagne is a cognac named in honor of Henri IV. It is also called the DNA of all Cognac. It was first produced in 1776 and aged in oak barrels for 100 years.', 'raw/champagne.jpg', 80 ), "
            + "('D002', 'Dalmore 62', 'It is well known that scotch whisky, as well as being a fine drink, holds increasing allure as an investment, and the value is steadily rising.', 'raw/whisky.jpeg', 75 ) "
            ;

    // Select everything query
    public static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;

    public Product() {
        this.product_code = "";
        this.product_name = "";
        this.product_desc = "";
        this.product_image = "";
        this.product_price = 0.0;
    }

    public Product(String product_code, String product_name, String product_desc, String product_image, double product_price){
        this.product_code = product_code;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_image = product_image;
        this.product_price = product_price;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
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
