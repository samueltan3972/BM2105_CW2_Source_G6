package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button msignup;
    Button mRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegister = (Button)findViewById(R.id.btnReg);
        msignup = (Button)findViewById(R.id.btnSignin);
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
