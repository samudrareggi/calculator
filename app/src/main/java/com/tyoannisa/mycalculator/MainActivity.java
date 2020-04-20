package com.tyoannisa.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Mendeklarasikan variabel
    TextView tv1,tv2;
    int idButton[] = {R.id.btn_0,R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4,R.id.btn_5,R.id.btn_6,
            R.id.btn_7,R.id.btn_8,R.id.btn_9,R.id.btn_ce,R.id.btn_c,R.id.btn_bs,R.id.btn_addition,
            R.id.btn_subtraction,R.id.btn_multiphication,R.id.btn_division,R.id.btn_dau,R.id.btn_cham,R.id.btn_bang};
    double result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect to View
        tv1 = findViewById(R.id.text_view1);
        tv2 = findViewById(R.id.text_view2);
        for(int i = 0 ; i < idButton.length ; i++) {
            findViewById(idButton[i]).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        //Format Desimal
        DecimalFormat df = new DecimalFormat("###.######");

        //C
        if(id == R.id.btn_c) {
            tv1.setText("");
            tv2.setText("0");
        }
        //CE
        if(id == R.id.btn_ce) {
            tv2.setText("0");
        }
        //BS
        if(id == R.id.btn_bs) {
            int a = Integer.parseInt(tv2.getText().toString());
            tv2.setText(String.valueOf(a/10));
        }
        for(int i = 0 ; i < 10 ; i++ ) {
            if(id == idButton[i]) {
                String s = tv1.getText().toString();
                if( !s.isEmpty() && s.charAt(s.length()-1) == '=' ) {
                    tv1.setText("");
                    tv2.setText("0");
                }
                int a = Integer.parseInt(tv2.getText().toString());
                if(a == 0)
                    tv2.setText(String.valueOf(i));
                else tv2.append(String.valueOf(i));
            }
        }
        //+/-
        if ( id == R.id.btn_dau) {
            int a = Integer.parseInt(tv2.getText().toString());
            a = -a;
            tv2.setText(String.valueOf(a));
        }
        //Perhitungan
        if(id == R.id.btn_addition) {
            String s = tv1.getText().toString();
            if( !s.isEmpty() ) {
                if( s.charAt(s.length()-1) == '=' ) {
                    tv1.setText(tv2.getText().toString() + "+");
                }else {
                    s = s.substring(0, s.length() - 1);
                    tv1.setText(s + "+");
                }
            }
            else {
                tv1.setText(tv2.getText().toString() + "+");
            }
            tv2.setText("0");
        }
        if(id == R.id.btn_subtraction) {
            String s = tv1.getText().toString();
            if( !s.isEmpty() ) {
                if( s.charAt(s.length()-1) == '=' ) {
                    tv1.setText(tv2.getText().toString() + "-");
                }else {
                    s = s.substring(0, s.length() - 1);
                    tv1.setText(s + "-");
                }
            }
            else {
                tv1.setText(tv2.getText().toString() + "-");
            }
            tv2.setText("0");
        }
        if(id == R.id.btn_multiphication) {
            String s = tv1.getText().toString();
            if( !s.isEmpty() ) {
                if( s.charAt(s.length()-1) == '=' ) {
                    tv1.setText(tv2.getText().toString() + "x");
                }else {
                    s = s.substring(0, s.length() - 1);
                    tv1.setText(s + "x");
                }
            }
            else {
                tv1.setText(tv2.getText().toString() + "x");
            }
            tv2.setText("0");
        }
        if(id == R.id.btn_division) {
            String s = tv1.getText().toString();
            if( !s.isEmpty() ) {
                if( s.charAt(s.length()-1) == '=' ) {
                    tv1.setText(tv2.getText().toString() + "/");
                }else {
                    s = s.substring(0, s.length() - 1);
                    tv1.setText(s + "/");
                }
            }
            else {
                tv1.setText(tv2.getText().toString() + "/");
            }
            tv2.setText("0");
        }
        if( id == R.id.btn_bang ) {
            String s = tv1.getText().toString();
            if( s.isEmpty() ) {
                tv1.setText(tv2.getText().toString() + "=");
            }else if( s.charAt(s.length()-1) == '=' ){
                tv1.setText(tv2.getText().toString() + "=");
            }else {
                int a = Integer.parseInt(s.substring(0, s.length()-1));
                int b = Integer.parseInt(tv2.getText().toString());
                tv1.setText(s + tv2.getText().toString() + "=");
                switch ( s.charAt(s.length()-1) ){
                    case '+' :
                        result = (a+b); break;
                    case '-' :
                        result = (a-b); break;
                    case 'x' :
                        result = (a*b); break;
                    case '/' :
                        if( b == 0 ) {
                            Toast.makeText(MainActivity.this, "Tidak Terdefinisi", Toast.LENGTH_LONG).show();
                            //tv2.setText("Tidak Terdefinisi");
                            return;
                        }
                        else {
                            result = (double)a/b;
                        }
                        break;
                    default:
                        result=a; break;
                }
                tv2.setText(df.format(result));
            }

        }



    }
}
