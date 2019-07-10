package example.bm2105_cw2_source_g6.database.model;

public class User {
    public static final String TABLE_NAME = "user";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "username";
    public static final String COLUMN_CONTACT_NUM = "contact_number";
    public static final String COLUMN_PASSWORD = "password";

    private int id;
    private String username;
    private String contact_number;
    private String password;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT UNIQUE,"
                    + COLUMN_CONTACT_NUM + " TEXT,"
                    + COLUMN_PASSWORD + " TEXT"
                    + ")";

    public User() {
    }

    public User(int id, String username, String contact_number, String password) {
        this.id = id;
        this.username = username;
        this.contact_number = contact_number;
        this.password = password;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setContactNum(String contact_number) {
        this.contact_number = contact_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
