package com.example.mysignupactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogInInfo extends AppCompatActivity implements View.OnClickListener {

    private EditText phoneNumber, password;
    private Button back, logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_info);

        phoneNumber = (EditText) findViewById(R.id.editPhoneNumber2Id);
        password = (EditText) findViewById(R.id.editPassword2Id);

        back = (Button)  findViewById(R.id.backButton2Id);
        logIn = (Button)  findViewById(R.id.logInButtonId);

        back.setOnClickListener(this);
        logIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.backButton2Id)){
            Toast.makeText(LogInInfo.this, "Back Button is clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LogInInfo.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if(v == findViewById(R.id.logInButtonId)){
            String phoneNumberString = phoneNumber.getText().toString();
            String passwordString = password.getText().toString();
            //Toast.makeText(LogInInfo.this, "Log in Button is clicked", Toast.LENGTH_SHORT).show();

            logInCheck(phoneNumberString, passwordString);

            //Log.v("info", phoneNumberString + "\n" + passwordString );
        }
    }

    public void logInCheck(String fileName, String password){
        try {
            FileInputStream fileInputStream = openFileInput(fileName+".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            //StringBuffer stringBuffer = new StringBuffer();
            line = bufferedReader.readLine();
            Log.v("info", "line: "+line.toString());
            Log.v("info", "password: "+password);

            if( password.equals(line) ){
                Toast.makeText(LogInInfo.this, "Log In successful", Toast.LENGTH_SHORT).show();
                Log.v("info", "Log in successful" );
                String district = bufferedReader.readLine();
                Log.v("info", "district : "+district);

                Intent intent = new Intent(LogInInfo.this, Profile.class);
                intent.putExtra("district",district.toString());
                intent.putExtra("filename",fileName.toString());
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(LogInInfo.this, "Wrong password", Toast.LENGTH_LONG).show();
                Log.v("info", "Wrong password" );
            }


        } catch (FileNotFoundException e) {
            Toast.makeText(LogInInfo.this, "Invalid Input\nPlease try again", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}