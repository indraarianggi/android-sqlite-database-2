package com.example.indraarianggi.inventorikaryawan;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.indraarianggi.inventorikaryawan.adapterLogin.UserDatabaseAdapter;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp;
    UserDatabaseAdapter userDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of SQLite Database.
        userDatabaseAdapter = new UserDatabaseAdapter(this);
        userDatabaseAdapter = userDatabaseAdapter.open();

        // Get the references od buttons.
        btnSignIn = (Button)findViewById(R.id.btn_signin);
        btnSignUp = (Button)findViewById(R.id.btn_signup);

        // Set OnClick listener on SignUp button.
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignUp = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intentSignUp);
            }
        });
    }

    // Method to handle click event of SignIn button
    // fungsi method yang ada dialog
    public void signIn(View view) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");

        // Get the references of views.
        final EditText edtUsername = (EditText)dialog.findViewById(R.id.edt_username);
        final EditText edtPassword = (EditText)dialog.findViewById(R.id.edt_password);
        Button btnSignIn = (Button)dialog.findViewById(R.id.btn_signin);

        // Set OnClick Listener.
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the username and password
                String txtUserName = edtUsername.getText().toString();
                String txtPassword = edtPassword.getText().toString();

                // Fetch the password from database for respective username.
                String storedPassword = userDatabaseAdapter.getSingleEntry(txtUserName);

                // Check if the stored password matches with password entered by user.
                if (txtPassword.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Username or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database.
        userDatabaseAdapter.close();
    }
}
