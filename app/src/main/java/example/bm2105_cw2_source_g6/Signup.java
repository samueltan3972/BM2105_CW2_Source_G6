package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    EditText mpassword;
    EditText musername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mpassword =(EditText)findViewById(R.id.password);
        musername =(EditText)findViewById(R.id.username);

    }
}
