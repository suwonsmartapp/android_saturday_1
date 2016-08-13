package com.suwonsmartapp.saturdayproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CoffeeActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int COFFEE_PRICE = 5;

    private TextView mTextViewQuantity;
    private TextView mTextViewPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        Button button_minus = (Button) findViewById(R.id.button_minus);
        Button button_plus = (Button) findViewById(R.id.button_plus);
        Button button_order = (Button) findViewById(R.id.button_order);

        mTextViewQuantity = (TextView) findViewById(R.id.text_quantity);
        mTextViewPrice = (TextView) findViewById(R.id.text_price);

        button_minus.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_order.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int quantity;
        int price;

        switch (view.getId()) {

            case R.id.button_minus:

                quantity = Integer.parseInt(mTextViewQuantity.getText().toString()) - 1;
                price = quantity * COFFEE_PRICE;

                if (price < 0) {
                    price = 0;
                    quantity = 0;
                }

                mTextViewQuantity.setText("" + quantity);
                mTextViewPrice.setText("$" + price);
                break;

            case R.id.button_plus:

                mTextViewQuantity.setText("" + (Integer.parseInt(mTextViewQuantity.getText().toString()) + 1));
                mTextViewPrice.setText("$" + (Integer.parseInt(mTextViewQuantity.getText().toString()) * COFFEE_PRICE));
                break;

        }

    }

}
