//Version 1.0
package com.example.tomer.customdialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView tv1,tv2,tv3,tv4;
    ListView lv;
    Button btn;
    String[] seq = new String[20];
    Double a1,d_q,sum,n;
    AlertDialog.Builder adb;
    AlertDialog ad;
    EditText et1,et2;
    LinearLayout dialog_1;
    Switch switch1;
    boolean bo,s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        lv = (ListView)findViewById(R.id.lv);
        btn = (Button)findViewById(R.id.btn);

        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void openAlert(View view) {

        dialog_1 = (LinearLayout)getLayoutInflater().inflate(R.layout.dialog_1, null);
        et1 = (EditText)dialog_1.findViewById(R.id.et1);
        et2 = (EditText)dialog_1.findViewById(R.id.et2);
        switch1 = (Switch)dialog_1.findViewById(R.id.switch1);

        adb = new AlertDialog.Builder(this);
        adb.setView(dialog_1);
        adb.setTitle("Sequence data");
        adb.setCancelable(false);

        adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ad.cancel();
            }
        });


        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                s = switch1.isChecked();
                if(!((et1.getText().toString().equals("."))||(et1.getText().toString().equals("-"))
                        ||(et1.getText().toString().equals("-."))||(et1.getText().toString().equals(""))
                        ||(et2.getText().toString().equals("."))||(et2.getText().toString().equals("-"))
                        ||(et2.getText().toString().equals("-."))||(et2.getText().toString().equals("")))){
                    tv3.setText("n=");
                    tv4.setText("Sn=");
                    a1 = Double.parseDouble(et1.getText().toString());
                    d_q = Double.parseDouble(et2.getText().toString());
                    tv1.setText("a1= "+et1.getText().toString());
                    tv2.setText("d/q= "+et2.getText().toString());
                    seq[0] = Double.toString(a1);
                    if (!s){
                        for (int j=1;j<20;j++){
                            seq[j] = Double.toString(Double.parseDouble(seq[j-1])+d_q);
                        }
                    }
                    else{
                        for (int j=1;j<20;j++){
                            seq[j] = Double.toString(Double.parseDouble(seq[j-1])*d_q);
                        }
                    }
                    setAdp();
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
                ad.dismiss();
            }
        });


        adb.setNegativeButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tv3.setText("n=");
                tv4.setText("Sn=");
                a1 = 0.0;
                d_q = 0.0;
                s = switch1.isChecked();
                resetArray();
                ad.dismiss();
            }
        });
        ad = adb.create();
        ad.show();
    }


    public void setAdp() {
        ArrayAdapter adp = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,seq);
        lv.setAdapter(adp);
    }


    public void resetArray() {
        for (int j=0;j<20;j++){
            seq[j] = "0";
        }
        tv1.setText("a1= "+Double.toString(a1));
        tv2.setText("d/q= "+Double.toString(d_q));
        setAdp();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        n = i+1.0;
        tv3.setText("n= "+Double.toString(n));
        if(!s){
            sum = ((n*((2*a1)+d_q*(n-1)))/2);
        }
        else {
            if(d_q==1)
                sum = a1*n;
            else
                sum = ((a1 * ((Math.pow(d_q, n)) - 1)) / (d_q - 1));
        }
        tv4.setText("Sn= "+Double.toString(sum));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String st = "";
        st = item.getTitle().toString();
        if(st.equals("Credits")){
            Intent t = new Intent(this, SecondActivity.class);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }
}


