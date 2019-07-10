package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    }

    public void submit(View view) {
        validation.validateLogin(musername,mpassword);

    }
}
