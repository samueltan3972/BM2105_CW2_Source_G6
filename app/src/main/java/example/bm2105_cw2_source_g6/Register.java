package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.bm2105_cw2_source_g6.database.DatabaseHelper;

public class Register extends AppCompatActivity {

    private EditText mpasswordReg;
    private EditText musernameReg;
    private EditText mcontactReg;
    private Button msubmitofReg;
    private DatabaseHelper databaseHelper;
    private Validation validate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mpasswordReg =(EditText)findViewById(R.id.password1);
        musernameReg =(EditText)findViewById(R.id.username1);
        mcontactReg = (EditText) findViewById(R.id.contact);
        msubmitofReg = (Button) findViewById(R.id.btnsubmitofregister);




    }

    public void submitofRegister(View view) {

        validate.InputDataofResgister(musernameReg,
                mpasswordReg,mcontactReg);

        this.finish();
    }
}
