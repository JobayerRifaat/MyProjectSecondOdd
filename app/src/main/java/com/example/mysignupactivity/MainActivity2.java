package com.example.mysignupactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup radiogroup, radiogroup2;
    private RadioButton yesOrNo, yesOrNo2;
    private EditText seedingCost, fertilizerCost, irrigationCost, pestManagementCost;

    private Button next2, apply, apply2;

    private TextView seedAmount, fertilizerAmount;

    private  double land;
    private int riceType;
    String district, filename;

    boolean drumSeeder, ureaSG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        radiogroup = (RadioGroup) findViewById(R.id.radioButtonId);
        radiogroup2 = (RadioGroup) findViewById(R.id.radioButton2Id);
        seedingCost = (EditText) findViewById(R.id.editSeedingCostId);
        fertilizerCost = (EditText) findViewById(R.id.editFertilizerCostId);
        irrigationCost = (EditText) findViewById(R.id.editIrrigationCostId);
        pestManagementCost = (EditText) findViewById(R.id.editPestManagementCostId);

        next2 = (Button) findViewById(R.id.nextButton2Id);
        apply = (Button) findViewById(R.id.applyButtonId);
        apply2 = (Button) findViewById(R.id.applyButton2Id);

        seedAmount = (TextView) findViewById(R.id.seedAmountTextId);
        fertilizerAmount = (TextView) findViewById(R.id.fertilizerAmountTextId);

        apply.setOnClickListener(this);
        apply2.setOnClickListener(this);
        next2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.applyButtonId)){
            int selected = radiogroup.getCheckedRadioButtonId();
            yesOrNo =(RadioButton) findViewById(selected);

            //String value = yesOrNo.getText().toString();

            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {

                land = bundle.getDouble("land");
                riceType = bundle.getInt("riceType");

                if (yesOrNo == findViewById(R.id.yesButtonId)) {
                    drumSeeder = true;
                    seedAmount.setText("You will be needed roughly ( " + land * 20 + "-" + land * 25 + " ) kg seed");
                }
                else {
                    drumSeeder = false;
                    seedAmount.setText("You will be needed roughly ( " + land * 7 + "-" + land * 9 + " ) kg seed");
                }

                Log.v("check", "\nApply Button is clecked");
            }

        }
        else if(v == findViewById(R.id.applyButton2Id)){
            int selected = radiogroup2.getCheckedRadioButtonId();
            yesOrNo2 =(RadioButton) findViewById(selected);

            //String value = yesOrNo.getText().toString();

            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {

                land = bundle.getDouble("land");
                riceType = bundle.getInt("riceType");

                district = bundle.getString("district");
                filename = bundle.getString("filename");
                //Log.v("info", "(cultivation) district : "+district+"\n(Cultivation) filename : "+filename);

                if (yesOrNo2 == findViewById(R.id.yesButton2Id)) {
                    ureaSG = true;
                    if(riceType == 1)//aus
                        fertilizerAmount.setText("You will be needed \n" + land * 45 + " kg Urea Super Granules, \n" + land * 21 + " kg TSP, \n"+ land * 25 + " kg MP");
                    else if(riceType == 2)//aman
                        fertilizerAmount.setText("You will be needed \n" + land * 45 + " kg Urea Super Granules, \n" + land * 21 + " kg TSP, \n"+ land * 33 + " kg MP,\n"+ land * 24 + " kg Gypsum");
                    else//boro
                        fertilizerAmount.setText("You will be needed \n" + land * 67 + " kg Urea Super Granules, \n" + land * 40 + " kg TSP, \n"+ land * 49 + " kg MP,\n"+ land * 55 + " kg Gypsum\n"+ land * 4 + " kg Zinc sulphate");
                }
                else{
                    ureaSG = false;
                    if(riceType == 1)//aus
                        fertilizerAmount.setText("You will be needed \n" + land * 52 + " kg Urea, \n" + land * 21 + " kg TSP, \n"+ land * 25 + " kg MP");
                    else if(riceType == 2)//aman
                        fertilizerAmount.setText("You will be needed \n" + land * 70 + " kg Urea, \n" + land * 21 + " kg TSP, \n"+ land * 33 + " kg MP,\n"+ land * 24 + " kg Gypsum");
                    else//boro
                        fertilizerAmount.setText("You will be needed \n" + land * 105 + " kg Urea, \n" + land * 40 + " kg TSP, \n"+ land * 49 + " kg MP,\n"+ land * 55 + " kg Gypsum,\n"+ land * 4 + " kg Zinc sulphate");
                }

                Log.v("check", "\nApply2 Button is clecked");
            }

        }
        else if(v == findViewById(R.id.nextButton2Id)){
            String seedcost = seedingCost.getText().toString();
            double seed = Double.parseDouble(seedcost);
            String fertilizercost = fertilizerCost.getText().toString();
            double fertilizer = Double.parseDouble(fertilizercost);

            String irrigationcost = irrigationCost.getText().toString();
            double irrigation = Double.parseDouble(irrigationcost);
            String pestmanagementcost = pestManagementCost.getText().toString();
            double pestmanagement = Double.parseDouble(pestmanagementcost);

            //Toast.makeText(MainActivity2.this, "Next Button is clicked", Toast.LENGTH_SHORT).show();
            Log.v("check", "\nland: "+land+"\n ricetype: "+riceType+"\n seeding cost: "+seed+"\n fertilizer cost: "+fertilizer+"\n irrigation cost: "+irrigation+"\n pest management cost: "+pestmanagement);
            Log.v("check", "\n"+drumSeeder+"\n"+ureaSG);

            double totalCost = seed+fertilizer+irrigation+pestmanagement; Log.v("check", "\n"+totalCost);

            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtra("land",land);
            intent.putExtra("riceType", riceType);
            intent.putExtra("totalCost", totalCost);

            intent.putExtra("drumSeeder", drumSeeder);
            intent.putExtra("ureaSG", ureaSG);

            intent.putExtra("district",district.toString());
            intent.putExtra("filename",filename.toString());

            startActivity(intent);
            finish();

        }
    }
}