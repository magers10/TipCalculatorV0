package com.magers.tipcalculatorv0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TipCalculator tipCalc;
    private NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(.7f, 100f);
        setContentView(R.layout.activity_main);

         billEditText = findViewById(R.id.et_Bill_Amount);
         tipEditText = findViewById(R.id.et_Enter_Tip);

         TextChangeHandler tch = new TextChangeHandler();
         billEditText.addTextChangedListener(tch);
         tipEditText.addTextChangedListener(tch);


    }

    //Called when the user clicks on the Calculate Button

    public void calculate () {

        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        TextView tipTextView = findViewById(R.id.tv_Tip_Total);
        TextView totalTextView = findViewById(R.id.tv_Bill_Total);

        //Convert billString and tipString to floats
        float billAmount = Float.parseFloat(billString);
        int tipPercent = Integer.parseInt(tipString);

        //Update model (class)
        tipCalc.setBill(billAmount);
        tipCalc.setTip(.01f * tipPercent);


        //ask model to calculate tip and totals
        float tip = tipCalc.tipAmount();
        float total = tipCalc.totalAmount();

        //update the view with formatted tip and total amounts
        tipTextView.setText(money.format(tip));
        totalTextView.setText(money.format(total));






    }

    private class TextChangeHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            calculate();

        }
    }
}