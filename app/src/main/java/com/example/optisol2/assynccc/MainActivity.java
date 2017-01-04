package com.example.optisol2.assynccc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText name,mobile,email,city;
    DatabaseHelper dbh;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.editText);
        mobile=(EditText)findViewById(R.id.editText2);
        email=(EditText)findViewById(R.id.editText3);
        city=(EditText)findViewById(R.id.editText4);
        submit=(Button)findViewById(R.id.button);
        dbh=new DatabaseHelper(this);
        registerViews();
    }

    private void registerViews() {
        name.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validate.hasText(name);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });


        email.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validate.isEmailAddress(email, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });


        mobile.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validate.isPhoneNumber(mobile, false);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        city.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validate.hasText(city);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
    }

    public void onClick(View v){

        new Asynctask1().execute();
    }
    private class Asynctask1 extends AsyncTask<String,String,String>{
        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... strings) {
            for(int i=0;i<5;i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;
            }
        protected void onProgressUpdate(String... data){
            dbh.insertContact(name.getText().toString(), mobile.getText().toString(),email.getText().toString(),city.getText().toString());

        }
        @Override
         public void onPreExecute(){
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Please Wait...");
        }
        @Override
        protected void onPostExecute(String value){
            progressDialog.dismiss();
            AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
            adb.setMessage("Updated");
            adb.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            adb.show();
        }
    }
}
