package com.example.mysignupactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    private EditText currentPrice;
    private Button result, newCultivation;
    private TextView resultText;

    private  double land, totalCost, yield ;
    private int riceType;

    boolean drumSeeder, ureaSG;

    String district, filename, Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        currentPrice = (EditText) findViewById(R.id.editCurrentPriceId);
        result = (Button) findViewById(R.id.newCultivationButtonId);
        newCultivation = (Button) findViewById(R.id.resultButtonId);
        resultText = (TextView) findViewById(R.id.resultTextId);

        result.setOnClickListener(this);
        newCultivation.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == findViewById(R.id.resultButtonId)){
            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {

                land = bundle.getDouble("land");
                riceType = bundle.getInt("riceType");
                totalCost = bundle.getDouble("totalCost");
                drumSeeder = bundle.getBoolean("drumSeeder");
                ureaSG = bundle.getBoolean("ureaSG");

                district = bundle.getString("district");
                filename = bundle.getString("filename");
                Log.v("info", "(MyActivity3) district : "+district+"\n(MyActivity3) filename : "+filename);

            }

            String currentprice =  currentPrice.getText().toString();
            double price = Double.parseDouble(currentprice);

            //Toast.makeText(MainActivity3.this, "Result Button is clicked", Toast.LENGTH_SHORT).show();
            Log.v("check", "\nland: "+land+"\n ricetype: "+riceType+"\n total cost: "+totalCost);
            Log.v("check", "\n"+drumSeeder+"\n"+ureaSG);

            if(riceType == 1) {
                yield=find_yield("district1.txt",district);
                if(yield==0)yield = 1012.5;
            }
            else if(riceType == 2) {
                yield=find_yield("district2.txt",district);
                if(yield==0)yield = 1032.75;
            }
            else if(riceType == 3) {
                yield=find_yield("district3.txt",district);
                if(yield==0)yield = 1680.75;
            }

            double production = yield*land;


            if(drumSeeder) production = production+(production*.15);
            if(ureaSG) production = production+(production*.175);
            price = production*price;


            Result = "Land: "+land+" acre\nProduction: "+(float)production+" kg\nTotal cost: "+(float)totalCost+" taka\nPrice: "+(float)price+" taka\n\nProfit/Loss: "+(float)(price-totalCost)+" taka\n";
            resultText.setText(Result);

            //String history = readFromFile(filename+"result");

            //Log.v("check", "(MyActivity3): "+history);

            //StringBuffer stringBuffer = new StringBuffer();
            //stringBuffer.append(result+"\n\n");
            //stringBuffer.append(history);

            //writeToFile( filename+"result.txt",stringBuffer.toString());
            writeToFile( filename+"result.txt",Result);

        }

        if(v == findViewById(R.id.newCultivationButtonId)){
            Intent intent = new Intent(MainActivity3.this, Profile.class);
            intent.putExtra("district",district.toString());
            intent.putExtra("filename",filename.toString());
            startActivity(intent);
            finish();
        }


    }

    public void writeToFile(String fileName, String text){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
            Toast.makeText(MainActivity3.this, "Data is saved", Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public double find_yield(String filename, String dist){
        FileInputStream fileInputStream = null;
        double y;
        try {
            fileInputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String di, yl;


            while((di = bufferedReader.readLine()) != null){
                yl =  bufferedReader.readLine();
                if(di.equals(dist)){
                    y = Double.parseDouble(yl);
                    return y*405;
                }
            }

        } catch (FileNotFoundException e) {
            return 0;//e.printStackTrace();
        } catch (IOException e) {
            return 0;//e.printStackTrace();
        }

        return 0;
    }


}
