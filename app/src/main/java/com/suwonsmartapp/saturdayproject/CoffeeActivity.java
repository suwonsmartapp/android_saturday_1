package com.suwonsmartapp.saturdayproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class CoffeeActivity extends AppCompatActivity {

    public final static int COFFEE_PRICE = 5;

    private int qty;    // mQty
    String total;   // mTotal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        Button minus = (Button) findViewById(R.id.minus);
        Button plus = (Button) findViewById(R.id.plus);
        Button order = (Button) findViewById(R.id.order);

        final TextView quantity = (TextView) findViewById(R.id.quantity);
        final TextView print = (TextView) findViewById(R.id.print);


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty = Integer.parseInt(quantity.getText().toString());
                if (qty < 0) {
                    Toast.makeText(CoffeeActivity.this, "0 보다 작습니다", Toast.LENGTH_SHORT).show();
                } else {
                    qty--;
                }

                printPrice(quantity, print);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty = Integer.parseInt(quantity.getText().toString());
                if (qty > 100) {
                    Toast.makeText(CoffeeActivity.this, "0 보다 작습니다", Toast.LENGTH_SHORT).show();
                } else {
                    qty++;
                }

                printPrice(quantity, print);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty = Integer.parseInt(quantity.getText().toString());
                if (qty != 0)
                    Toast.makeText(CoffeeActivity.this, "커피 " + quantity.getText().toString() + "잔을 주문하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void printPrice(TextView quantity, TextView print) {
        quantity.setText(String.format(Locale.getDefault(), "$ %d", qty));
        print.setText(total);
    }
}