package com.example.inputapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
int numberOfCoffee=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void increment(View view){
numberOfCoffee =numberOfCoffee + 1;
display(numberOfCoffee);
    }
    public void decrement(View view){
    numberOfCoffee = numberOfCoffee - 1;
    display(numberOfCoffee);
    }
    public void submitOrder(View view){

        display(numberOfCoffee);
        displayPrice(numberOfCoffee*5);
    }

    @SuppressLint("SetTextI18n")
    private void display(int number) {
        TextView quantityTextView=(TextView)findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+ number);
    }
    private void displayPrice(int number){
        TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
        priceTextView.setText("Total "+number+" Rupees");

    }

}