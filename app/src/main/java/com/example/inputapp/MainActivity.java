package com.example.inputapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffee = 1 ,ordernumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (numberOfCoffee > 100){
            Toast toast = Toast.makeText(getApplicationContext(),"maxmimum order range is 100", Toast.LENGTH_SHORT);
            toast.show();
        }else {
        numberOfCoffee = numberOfCoffee + 1;
        displayQuantity(numberOfCoffee);
    }
    }

    public void decrement(View view) {
        if (numberOfCoffee <= 1){
            Context context = getApplicationContext();
            CharSequence text = "Minimum order range is 1";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
        numberOfCoffee = numberOfCoffee - 1;
        displayQuantity(numberOfCoffee);}
    }

    public void submitOrder(View view) {
        ordernumber++;
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean addChocolate = chocolate.isChecked();

        EditText name = (EditText) findViewById(R.id.editText_name);

        int price = calculatePrice(hasWhippedCream,addChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, addChocolate, name.getText().toString(), price);

        /**
         * intent to Gamil
         */

        Intent mail = new Intent(Intent.ACTION_SENDTO);
        mail.setData(Uri.parse("mailto:")); // only email apps should handle this
        mail.putExtra(Intent.EXTRA_SUBJECT, priceMessage);
        mail.putExtra(Intent.EXTRA_TEXT,priceMessage);
        mail.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order Number " + ordernumber);
            if (mail.resolveActivity(getPackageManager()) != null) {
                startActivity(mail);
            }
    



    }



    private int calculatePrice(boolean whippedCream, boolean choco) {

        int price =5;
        if (whippedCream){
            price = price + 1;
        }
        if (choco){
            price = price + 2;
        }
        price = numberOfCoffee * price;
        return price;
    }


    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String custName , int totalprice) {
        String priceMessage = "Name : " + custName;
        priceMessage += "\nAdd Whipped Cream : " + addWhippedCream;
        priceMessage += "\nAdd Chocolate : " + addChocolate;
        priceMessage += "\nQuantity:" + numberOfCoffee;
        priceMessage += "\nTotal: $" + totalprice;
        priceMessage += "\nThank you!";
        return priceMessage;
    }


}