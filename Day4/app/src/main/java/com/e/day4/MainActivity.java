package com.e.day4;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String name;
    int money;
    int result;
    String phone;
    String language, city;
    String moneylocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnBook = findViewById(R.id.btnBook);
        final TextView editName = findViewById(R.id.editName);
        final CheckBox checkBox2 = findViewById(R.id.checkBox2);
        final RadioButton RDseat = findViewById(R.id.RDseat);
        final RadioButton RDberth = findViewById(R.id.RDberth);
        final TextView txtPhone = findViewById(R.id.txtPhone);



        final Spinner dropdown;
        dropdown = (Spinner) findViewById(R.id.spinner);
        final ArrayList<String> country = new ArrayList<String>();
        country.add("USD");
        country.add("VND");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        dropdown.setAdapter(arrayAdapter);

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    Toast.makeText(MainActivity.this, "Discount 20% for vip member", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button book = findViewById ( R.id.btnBook );
        book.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                name = "Name: " + editName.getText().toString();
                phone = "\nPhone: " + txtPhone.getText().toString();
                if (RDseat.isChecked()){
                    money = 600000;
                }else if (RDberth.isChecked()){
                    money = 1200000;
                }
                if (checkBox2.isChecked()) {
                    result = money -(money/100*20);
                    if (dropdown.getSelectedItem().toString() == "USD") {
                        city = "US";
                        language = "en";
                        moneylocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(result / 23300);
                    } else if (dropdown.getSelectedItem().toString() == "VND") {
                        city = "VN";
                        language = "vi";
                        moneylocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(result);
                    }
                }else if (dropdown.getSelectedItem().toString() == "USD") {
                    city = "US";
                    language = "en";
                    moneylocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(money/ 23300);
                } else if (dropdown.getSelectedItem().toString() == "VND") {
                    city = "VN";
                    language = "vi";
                    moneylocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(money);
                }

                String mess = name + phone + "\nPrice: " + moneylocal;
                display(mess);
            }
        } );

        Button cancel = findViewById ( R.id.btnCancel );
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void display(String mess){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder (MainActivity.this );
        dlgAlert.setMessage (mess);
        dlgAlert.setTitle("Information");
        dlgAlert.setPositiveButton("OK",null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

    }

}
