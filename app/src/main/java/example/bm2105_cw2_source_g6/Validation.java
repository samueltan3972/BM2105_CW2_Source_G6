package example.bm2105_cw2_source_g6;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.database.DatabaseHelper;
import example.bm2105_cw2_source_g6.database.model.User;

import static android.content.Context.MODE_PRIVATE;

public class Validation {

    private Context context;
    private DatabaseHelper databaseHelper;
    public static final String SESSION_NAME = "userObject";

    public Validation(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public boolean InputDataofResgister(String username,
                                        String password, String contact){

       long id = databaseHelper.insertUser(username, contact,password);


       if(id == -1){
           return false;
       }

       return true;

    }

    public boolean validateLogin(String username, String password) {
        User userObject = databaseHelper.getUser(username);

        if (username.isEmpty() || password.isEmpty()) {
            return false;
        } else {
            if (userObject.getUserName().isEmpty() ||
                    userObject.getPassword().isEmpty()) {
                return false;
            } else {
                if (username.equals(userObject.getUserName()) &&
                        password.equals(userObject.getPassword())) {

                    Common.currentUser = userObject;

                    // Save the user object to shared preference, act like session
                    SharedPreferences mPrefs = context.getSharedPreferences(SESSION_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();

                    Gson gson = new Gson();
                    String json = gson.toJson(userObject);
                    prefsEditor.putString("userObject", json);
                    prefsEditor.commit();

                    return true;
                } else {
                    Toast.makeText(context, userObject.getPassword(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }


        }
    }


}
