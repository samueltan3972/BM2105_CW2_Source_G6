package example.bm2105_cw2_source_g6.database.model;

public class User {
    public static final String TABLE_NAME = "user";

    public static final String COLUMN_NAME = "username";
    public static final String COLUMN_CONTACT_NUM = "contact_number";
    public static final String COLUMN_PASSWORD = "password";

    private String username;
    private String contact_number;
    private String password;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_NAME + " TEXT PRIMARY KEY,"
                    + COLUMN_CONTACT_NUM + " TEXT,"
                    + COLUMN_PASSWORD + " TEXT"
                    + ")";

    public User() {
    }

    public User(String username, String contact_number, String password) {
        this.username = username;
        this.contact_number = contact_number;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public String getContactNum() {
        return contact_number;
    }

    public String getPassword() {
        return password;
    }

    public void setContactNum(String contact_number) {
        this.contact_number = contact_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
