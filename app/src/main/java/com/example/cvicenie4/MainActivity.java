package com.example.cvicenie4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch pohlavie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pohlavie = findViewById(R.id.pohlavie);
        pohlavie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pohlavie.isChecked())
                    pohlavie.setText(getString(R.string.zena));
                else
                    pohlavie.setText(getString(R.string.muz));
            }
        });
    }

    public void onClickVypocet(View v) {

        // nasledujúci kód shová softvérovu klávesnicu, nemazať
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        // vytvorte referenciu na editbox vyska
        EditText vyska_field = findViewById(R.id.editTextVyska);
        double vyska = Double.parseDouble(vyska_field.getText().toString());

        // vytvorte referenciu na editbox hmotnost
        EditText hmotnost_field = findViewById(R.id.editTextHmotnost);
        double hmotnost = Double.parseDouble(hmotnost_field.getText().toString());

        // skonvertujte vysku v [cm] na float
        // napr. float h = Float.parseFloat(vyska.getText().toString())/100;
        float vyska_float = (((float)vyska)/100);

        // skonvertujte hmotnost v [cm] na float
        float hmotnost_float = (((float)hmotnost));

        // Vypočítaj BMI = hmotnost / vyska * vyska  [kg / m2]
        float BMI = hmotnost_float/(vyska_float*vyska_float);

        // vytvorte referenciu na textview bmi
        TextView BMI_field = findViewById(R.id.bmi);

        // zobrazte hodnotu BMI
        // ???.setText("BMI="+Float.toString(bmi));
        BMI_field.setText(Double.toString(BMI));

        // vytvorte referenciu na textview stav
        TextView stav_field = findViewById(R.id.stav);
       // Switch muz_field = findViewById(R.id.pohlavie);

        boolean muz = pohlavie.isChecked();
        // nastavte stav, použite hodnotu getStav(BMI,muz)
        stav_field.setText(getStav((float) BMI,muz));
    }

    public String getStav(float bmi, boolean muz) {
        if (muz) bmi += 1;
        if (bmi < 20) return getString(R.string.podvaha);
        if (bmi > 40) return getString(R.string.obezita3);
        if (bmi > 35) return getString(R.string.obezita2);
        if (bmi > 30) return getString(R.string.obezita1);
        if (bmi > 25) return getString(R.string.nadvaha);
        return getString(R.string.normalna);
    }
}
