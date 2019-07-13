package example.bm2105_cw2_source_g6;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.database.DatabaseHelper;
import example.bm2105_cw2_source_g6.database.model.User;

public class Validation {

    private Context context;
    private DatabaseHelper databaseHelper;

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
        Log.d("debug", "debug2");
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
                    return true;
                } else {
                    Toast.makeText(context, userObject.getPassword(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }


        }
    }


}
