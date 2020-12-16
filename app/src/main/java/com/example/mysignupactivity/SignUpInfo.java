package com.example.mysignupactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SignUpInfo extends AppCompatActivity implements View.OnClickListener {

    private EditText district, phoneNumber, password;
    private Button back, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_info);

        district = (EditText)  findViewById(R.id.editDistrictId);
        phoneNumber = (EditText)  findViewById(R.id.editPhoneNumberId);
        password = (EditText)  findViewById(R.id.editPasswordId);

        back = (Button)  findViewById(R.id.backButtonId);
        signUp = (Button)  findViewById(R.id.signUpButtonId);

        back.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == findViewById(R.id.backButtonId)){
            Toast.makeText(SignUpInfo.this, "Back Button is clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpInfo.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if(v == findViewById(R.id.signUpButtonId)){
            String districtString = district.getText().toString();
            String phoneNumberString = phoneNumber.getText().toString();
            String passwordString = password.getText().toString();
            Toast.makeText(SignUpInfo.this, "Sign Up Button is clicked", Toast.LENGTH_SHORT).show();
            Log.v("info",  districtString + "\n" + phoneNumberString + "\n" + passwordString );

            if(phoneNumberString!=null){
                writeToFile( phoneNumberString+".txt",passwordString  + "\n"+districtString);
                Intent intent = new Intent(SignUpInfo.this, Profile.class);

                intent.putExtra("district",districtString.toString());
                intent.putExtra("filename",phoneNumberString.toString());

                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(SignUpInfo.this, "please Enter your phone number", Toast.LENGTH_LONG).show();

            }
        }

    }

    public void writeToFile(String fileName, String text){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
            Toast.makeText(SignUpInfo.this, "Data is saved", Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}