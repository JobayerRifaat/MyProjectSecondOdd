package com.example.mysignupactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cultivation extends AppCompatActivity implements View.OnClickListener {

    private Button datePickerButton;
    private TextView datePickerText;
    private DatePickerDialog datePickerDialog;
    private EditText landAmount;

    private Button back, next;

    int riceType=0;
    String filename, district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivation);

        datePickerButton = (Button) findViewById(R.id.datePickerButton);
        datePickerText = (TextView) findViewById(R.id.datePickerText);
        landAmount = (EditText) findViewById(R.id.editLandAmountId);

        back = (Button) findViewById(R.id.backButtonId);
        next = (Button) findViewById(R.id.nextButtonId);

        datePickerButton.setOnClickListener(this);
        back.setOnClickListener(this);
        next.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.datePickerButton)){

            DatePicker datePicker = new DatePicker(this);

            int currentYear = datePicker.getYear();
            int currentMonth = datePicker.getMonth();
            int currentDay = datePicker.getDayOfMonth();

            datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override

                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            //datePickerText.setText("Selected date : " + dayOfMonth + "/" + month + "/" + year);
                            if((month == 2 && dayOfMonth > 29) || (month == 3 && dayOfMonth < 21)){
                                datePickerText.setText("Aus Rice will be apropriate for you");

                                riceType = 1;


                            }

                            else if((month == 5 && dayOfMonth > 16) || (month == 6 && dayOfMonth < 31)){
                                datePickerText.setText("Aman Rice will be apropriate for you");

                                riceType = 2;
                            }

                            else if((month == 10 && dayOfMonth > 15) || (month == 3 && dayOfMonth < 31)){
                                datePickerText.setText("Boro Rice will be appropriate for you");

                                riceType = 3;
                            }

                            else
                                datePickerText.setText("This is not any apropriate season\nPlease select another date\n\nAus => (30 March - 20 April)\nAman => (15 June - 30 July)\nBoro => (15 November - 30 November)");
                        }

                    }, currentYear, currentMonth, currentDay);

            datePickerDialog.show();

        }

        else if(v == findViewById(R.id.nextButtonId)){
            String landamount="";
            landamount = landAmount.getText().toString();
            double land =0;
            land = Double.parseDouble(landamount);

            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {
                district = bundle.getString("district");
                filename = bundle.getString("filename");
                Log.v("info", "(cultivation) district : "+district+"\n(Cultivation) filename : "+filename);
            }

            if(riceType == 0||land==0){
                Toast.makeText(Cultivation.this, "Date is not picked", Toast.LENGTH_LONG).show();

            }else {
                Intent intent = new Intent(Cultivation.this, MainActivity2.class);

                intent.putExtra("land", land);
                intent.putExtra("riceType", riceType);
                intent.putExtra("district",district.toString());
                intent.putExtra("filename",filename.toString());

                startActivity(intent);
                finish();
            }

        }

        else if(v == findViewById(R.id.backButtonId)){

            Toast.makeText(Cultivation.this, "Back Button is clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Cultivation.this, Profile.class);
            startActivity(intent);
            //finish();

        }

    }
}