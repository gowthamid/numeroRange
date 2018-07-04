package com.gow.numerorange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static EditText editText = null;
    static EditText editText2 = null;
    static EditText editText3 = null;
    static EditText editText4 = null;
    static CheckBox checkBox=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        editText2.setVisibility(View.INVISIBLE);
        editText3.setVisibility(View.INVISIBLE);
        editText4.setVisibility(View.INVISIBLE);
    }
    public void onClickEditTxt(View v)
    {
        editText.setText("");
    }
    public void onClickEditTxt2(View v)
    {
        editText2.setText("");
    }
    public void onClickEditTxt3(View v)
    {
        editText3.setText("");
    }
    public void onClickEditTxt4(View v)
    {
        editText4.setText("");
    }
    public void onClickCheck(View v)
    {
        if(checkBox.isChecked()){
            editText2.setVisibility(View.VISIBLE);
            editText3.setVisibility(View.VISIBLE);
            editText4.setVisibility(View.VISIBLE);
            editText.setVisibility(View.INVISIBLE);
            editText.setText(R.string.reg_no);
        }
        else
        {
            editText2.setVisibility(View.INVISIBLE);
            editText3.setVisibility(View.INVISIBLE);
            editText4.setVisibility(View.INVISIBLE);
            editText.setVisibility(View.VISIBLE);
            editText2.setText(R.string.from);
            editText3.setText(R.string.to);
            editText4.setText(R.string.luckyNumbers);
        }

    }
    public void onClickBtn(View v)
    {
        //Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
        //Toast.makeText(this, editText.getText(), Toast.LENGTH_LONG).show();

        Map<Integer, List<String>> numerMap = new HashMap<>();

        List<String> alpha1=new ArrayList<String>();
        List<String> alpha2=new ArrayList<String>();
        List<String> alpha3=new ArrayList<String>();
        List<String> alpha4=new ArrayList<String>();
        List<String> alpha5=new ArrayList<String>();
        List<String> alpha6=new ArrayList<String>();
        List<String> alpha7=new ArrayList<String>();
        List<String> alpha8=new ArrayList<String>();

        alpha1.add("A");
        alpha1.add("I");
        alpha1.add("J");
        alpha1.add("Q");
        alpha1.add("Y");

        alpha2.add("B");
        alpha2.add("K");
        alpha2.add("R");

        alpha3.add("C");
        alpha3.add("G");
        alpha3.add("L");
        alpha3.add("S");

        alpha4.add("D");
        alpha4.add("M");
        alpha4.add("T");

        alpha5.add("E");
        alpha5.add("H");
        alpha5.add("N");
        alpha5.add("X");

        alpha6.add("U");
        alpha6.add("V");
        alpha6.add("W");

        alpha7.add("O");
        alpha7.add("Z");

        alpha8.add("F");
        alpha8.add("P");

        numerMap.put(1, alpha1);
        numerMap.put(2, alpha2);
        numerMap.put(3, alpha3);
        numerMap.put(4, alpha4);
        numerMap.put(5, alpha5);
        numerMap.put(6, alpha6);
        numerMap.put(7, alpha7);
        numerMap.put(8, alpha8);

        Map<Integer, List<String>> luckyRange = new HashMap<Integer, List<String>>();

        String input[]=new String[2];
        //CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()) {
            if(TextUtils.isEmpty(editText4.getText()) || TextUtils.equals(editText4.getText(), "Enter Lucky number")){
                editText4.setError("Lucky number is required");
                return;
            }
            if(TextUtils.isEmpty(editText2.getText()) || TextUtils.equals(editText2.getText(), "From Range")){
                editText2.setError("From Range is required");
                return;
            }
            if(TextUtils.isEmpty(editText3.getText()) || TextUtils.equals(editText3.getText(), "To Range")){
                editText3.setError("To Range is required");
                return;
            }
            input[0] = editText2.getText().toString();
            input[1] = editText3.getText().toString();
        }
        else{
            if(TextUtils.isEmpty(editText.getText()) || TextUtils.equals(editText.getText(), "Registration Number(ex.**$$*$$$$/**$$**$$$$)")){
                editText.setError("Registration Number is required");
                return;
            }

            input[0]= editText.getText().toString();
            input[1] = editText.getText().toString();}

        List<String> luckyList = Arrays.asList(editText3.getText().toString().split(","));
        Set<String> luckySet=new HashSet<String>(luckyList);

        luckyRange=calculateNumero(numerMap,input,luckySet);
        //Toast.makeText(this, String.valueOf(sum), Toast.LENGTH_LONG).show();

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        if(!input[0].equalsIgnoreCase(input[1])) {
            alertDialogBuilder.setTitle("Why wait?! Register with any of the following");
            int fin=Integer.parseInt(editText4.getText().toString());
            alertDialogBuilder.setMessage("Series for your lucky number "+fin+" is "+luckyRange.get(fin));
        }
        else{
            alertDialogBuilder.setTitle("Why wait?! Register with the following if it is your lucky number");
            alertDialogBuilder.setMessage("Numerology number for your input series "+input[0]+" is "+luckyRange.keySet());
        }
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MainActivity.this.finish();
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public static Map<Integer,List<String>> calculateNumero(Map<Integer, List<String>> numerMap, String[] input, Set<String> luckySet) {
        List<String> alpha1 = new ArrayList<String>();
        List<String> alpha2 = new ArrayList<String>();
        List<String> alpha3 = new ArrayList<String>();
        List<String> alpha4 = new ArrayList<String>();
        List<String> alpha5 = new ArrayList<String>();
        List<String> alpha6 = new ArrayList<String>();
        List<String> alpha7 = new ArrayList<String>();
        List<String> alpha8 = new ArrayList<String>();
        List<String> alpha9 = new ArrayList<String>();

        Map<Integer, List<String>> luckyRange = new HashMap<Integer,List<String>>();
        String fromRange=input[0].toUpperCase();
        String toRange=input[1].toUpperCase();

        String series=fromRange.substring(0, fromRange.length()-4);
        int from = Integer.parseInt(fromRange.substring(fromRange.length()-4));
        int to = Integer.parseInt(toRange.substring(toRange.length()-4));

        //StringBuilder seriesCheck=new StringBuilder(series);

        for (int i = from; i <= to; i++) {
            int sum = 0;
            int total = 0;
            int totalFin=0;

            int key = 0;
            List<String> value = null;


            StringBuilder seriesCheck=new StringBuilder(series);
            seriesCheck=seriesCheck.append(String.valueOf(i));

            //System.out.println(seriesCheck);

            char[] ipArr = seriesCheck.toString().toCharArray();

            for (char ip : ipArr) {
                if (!Character.isDigit(ip))
                    for (Entry<Integer, List<String>> e : numerMap.entrySet()) {
                        key = e.getKey();
                        value = e.getValue();
                        if (value.contains(String.valueOf(ip))) // "P"
                            sum = sum + key;// return key;
                        // System.out.println(key+" "+value);

                    }
                else
                    sum = sum + ip;
            }
            //handles more than 3 didgit sum
            while (sum > 0) {
                total = total + sum % 10;
                sum = sum / 10;
            }
            //handles two digit sum
            while(total>0){
                totalFin=totalFin+ total % 10;
                total=total/10;
            }
            //System.out.println(totalFin);
            switch(totalFin)
            {
                case 1: alpha1.add(seriesCheck.toString());
                    luckyRange.put(1, alpha1);
                    break;
                case 2: alpha2.add(seriesCheck.toString());
                    luckyRange.put(2, alpha2);
                    break;
                case 3: alpha3.add(seriesCheck.toString());
                    luckyRange.put(3, alpha3);
                    break;
                case 4: alpha4.add(seriesCheck.toString());
                    luckyRange.put(4, alpha4);
                    break;
                case 5: alpha5.add(seriesCheck.toString());
                    luckyRange.put(5, alpha5);
                    break;
                case 6: alpha6.add(seriesCheck.toString());
                    luckyRange.put(6, alpha6);
                    break;
                case 7: alpha7.add(seriesCheck.toString());
                    luckyRange.put(7, alpha7);
                    break;
                case 8: alpha8.add(seriesCheck.toString());
                    luckyRange.put(8, alpha8);
                    break;
                case 9: alpha9.add(seriesCheck.toString());
                    luckyRange.put(9, alpha9);
                    break;

            }

        }

        return luckyRange;
    }

}
