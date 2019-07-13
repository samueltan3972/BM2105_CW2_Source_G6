package example.bm2105_cw2_source_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.bm2105_cw2_source_g6.Common.Common;
import example.bm2105_cw2_source_g6.database.DatabaseHelper;

public class EdittPage extends AppCompatActivity {

    EditText mEditUsername;
    EditText mEditContact;
    DatabaseHelper databaseHelper;
    Button mSubmitPersonal;
    Button mEditPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editt_page);
        mEditContact = (EditText) findViewById(R.id.editcontact);
        mEditUsername = (EditText) findViewById(R.id.editusername1);
        databaseHelper = new DatabaseHelper(this);
        mEditPersonal = (Button) findViewById(R.id.btnEditPersonal);
        mSubmitPersonal = (Button) findViewById(R.id.btnSubmitEditPersonal);

        mSubmitPersonal.setEnabled(false);
        mEditContact.setEnabled(false);
        mEditUsername.setEnabled(false);
        mEditPersonal.setText(Common.currentUser.getUserName());
        mEditContact.setText(Common.currentUser.getContactNum());





    }

    public void Edit(View view) {
        mSubmitPersonal.setEnabled(true);
        mEditContact.setEnabled(true);
    }

    public void Submit(View view) {
        String contact = mEditContact.getText().toString();
        databaseHelper.EditContact(contact);
        mSubmitPersonal.setEnabled(false);
        mEditContact.setEnabled(false);
    }
}
