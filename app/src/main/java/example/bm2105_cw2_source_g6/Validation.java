package example.bm2105_cw2_source_g6;

import android.content.Context;
import android.widget.EditText;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.database.DatabaseHelper;
import example.bm2105_cw2_source_g6.database.model.User;

public class Validation {

    private Context context;
    private DatabaseHelper databaseHelper;

    public Validation(Context context) {
        this.context = context;
    }

    public void InputDataofResgister(EditText username,
                              EditText password, EditText contact){
        String user =  username.getText().toString().trim();
        String passwords =  password.getText().toString().trim();
        String contacts =  contact.getText().toString().trim();

        databaseHelper.insertUser(user,passwords,contacts);

    }

    public boolean validateLogin(EditText username, EditText password) {
        String user = username.getText().toString().trim();
        String passwords = password.getText().toString().trim();
        User userObject = databaseHelper.getUser(user);
        if (user.isEmpty() || passwords.isEmpty()) {
            return false;
        } else {
            if (userObject.getUserName() == null ||
                    userObject.getPassword() == null) {
                return false;
            } else {
                if (user == userObject.getUserName() &&
                        passwords == userObject.getPassword()) {
                    Common.currentUser = userObject;

                    return true;
                } else {
                    return false;
                }
            }


        }
    }


}
