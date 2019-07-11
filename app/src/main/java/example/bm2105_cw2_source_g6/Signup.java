package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    private EditText mpassword;
    private EditText musername;
    private Button msubmitofSign;
    private Validation validation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mpassword =(EditText)findViewById(R.id.password);
        musername =(EditText)findViewById(R.id.username);
        msubmitofSign = (Button) findViewById(R.id.btnsubmitofsignin);
        validation = new Validation(this);
    }

    public void submit(View view) {
        String user = musername.getText().toString().trim();
        String passwords = mpassword.getText().toString().trim();

        if(validation.validateLogin(user,passwords)){
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid username / password", Toast.LENGTH_SHORT).show();
        }


    }
}
