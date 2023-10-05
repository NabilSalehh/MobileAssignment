package com.example.assignment11;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    //Declaring Variables

    private EditText editTextAmount;
    private EditText editTextTenure;
    private EditText editTextInterestRate;
    private EditText editTextTotalHousePrice;
    private EditText editTextDownPayment;
    private Button buttonCalculateEMI;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializes variables by finding Ui elements
        editTextAmount = findViewById(R.id.editTextTotalHousePrice);
        editTextTenure = findViewById(R.id.editTextTenure);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextTotalHousePrice = findViewById(R.id.editTextTotalHousePrice);
        editTextDownPayment = findViewById(R.id.editTextDownPayment);
        buttonCalculateEMI = findViewById(R.id.buttonCalculateEMI);
        textViewResult = findViewById(R.id.textViewResult);
        //this is a click listener for the calculate emi button
        buttonCalculateEMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when you click the button it will execute
                calculateEMI();
            }
        });
    }

    private void calculateEMI() {
        try {
            //takes input and makes it as numbers
            double totalHousePrice = Double.parseDouble(editTextTotalHousePrice.getText().toString());
            double downPayment = Double.parseDouble(editTextDownPayment.getText().toString());

            // subtracts the down payment from total house price
            double principal = totalHousePrice - downPayment;

            int tenureMonths = Integer.parseInt(editTextTenure.getText().toString());
            double interestRate = Double.parseDouble(editTextInterestRate.getText().toString());

            // converts annual interest rate into monthly then calculates emi using formula
            double monthlyInterestRate = (interestRate / 12) / 100;
            double emi = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths))
                    / (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);

            // displays emi
            textViewResult.setText(getString(R.string.result) + String.format("%.2f", emi));
            textViewResult.setTextColor(ContextCompat.getColor(this, R.color.text_color));
        } catch (NumberFormatException e) {
            //gives error if user inputs invalid entry
            textViewResult.setText(getString(R.string.error_message));
            textViewResult.setTextColor(Color.RED);
        }
    }
}
