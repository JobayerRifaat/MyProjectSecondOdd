package com.example.mysignupactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private Button newCultivation, history, help, logOut;
    String filename, district;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        newCultivation = (Button) findViewById(R.id.newCultivationButtonId);
        history = (Button) findViewById(R.id.historyButtonId);
        help = (Button) findViewById(R.id.helpButtonId);
        logOut = (Button) findViewById(R.id.logOutButtonId);

        newCultivation.setOnClickListener(this);
        history.setOnClickListener(this);
        help.setOnClickListener(this);
        logOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            district = bundle.getString("district");
            filename = bundle.getString("filename");
            //Log.v("info", "(Profile) district : "+district+"\n(Profile) filename : "+filename);
        }


        if(v.getId() == R.id.newCultivationButtonId){

            Toast.makeText(Profile.this, "Start New Cultivation Button is clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, Cultivation.class);
            intent.putExtra("district",district.toString());
            intent.putExtra("filename",filename.toString());
            startActivity(intent);
            //finish();

        }

        else if(v.getId() == R.id.historyButtonId){

            Toast.makeText(Profile.this, "History Button is clicked", Toast.LENGTH_SHORT).show();
            String history = readFromFile(filename+"result");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("History");
            builder.setMessage(history);
            builder.setCancelable(true);
            builder.show();

            Log.v("check", "(Profile) filename : "+filename+"\nhistory : "+history);


        }

        else if(v.getId() == R.id.helpButtonId){

            intent = new Intent(Profile.this, HelpWebView.class);
            startActivity(intent);

            Toast.makeText(Profile.this, "Help Button is clicked", Toast.LENGTH_SHORT).show();

        }

        else if(v.getId() == R.id.logOutButtonId){

            Toast.makeText(Profile.this, "LogOut Button is clicked", Toast.LENGTH_SHORT).show();
            intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

    }

    public String readFromFile(String filename){
        FileInputStream fileInputStream = null;

        StringBuffer stringBuffer = new StringBuffer();
        try {
            fileInputStream = openFileInput(filename+".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;


            while((line = bufferedReader.readLine()) != null){

                stringBuffer.append(line+"\n");
            }

        } catch (FileNotFoundException e) {
            return  "Not cultivated yet";//e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();

    }

}