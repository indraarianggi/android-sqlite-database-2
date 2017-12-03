package com.example.indraarianggi.inventorikaryawan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.indraarianggi.inventorikaryawan.adapterLogin.UserDatabaseAdapter;

public class SignupActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword, edtConfPassword;
    Button btnSignUp;

    UserDatabaseAdapter userDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Get instance of Database Adapter
        userDatabaseAdapter = new UserDatabaseAdapter(this);
        userDatabaseAdapter = userDatabaseAdapter.open();

        // Get references of views.
        edtUsername = (EditText)findViewById(R.id.edt_username);
        edtPassword = (EditText)findViewById(R.id.edt_password);
        edtConfPassword = (EditText)findViewById(R.id.edt_confirm_password);
        btnSignUp = (Button)findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUserName = edtUsername.getText().toString();
                String txtPassword = edtPassword.getText().toString();
                String txtConfPassword = edtConfPassword.getText().toString();

                // Check id any of fields are vaccant
                if (txtUserName.equals("") || txtPassword.equals("") || txtConfPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }

                // Check if both password matches
                if (!txtPassword.equals(txtConfPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    // Save the data in database.
                    userDatabaseAdapter.insertEntry(txtUserName, txtPassword);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database.
        userDatabaseAdapter.close();
    }
}
