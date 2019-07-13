package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.database.model.User;

public class MainActivity extends AppCompatActivity {

    Button msignup;
    Button mRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegister = (Button)findViewById(R.id.btnReg);
        msignup = (Button)findViewById(R.id.btnSignin);

        SharedPreferences mPrefs = getSharedPreferences(Validation.SESSION_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("userObject", "");

        if(!json.isEmpty()){
            Common.currentUser = gson.fromJson(json, User.class);

            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this,
                Register.class);
        startActivity(intent);

    }

    public void signin(View view) {
        Intent intent = new Intent(this,
                Signin.class);
        startActivity(intent);
    }

}
