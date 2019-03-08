package com.headspire.callingtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * version 1.0
 * created by Ashish yadav 08-03-2019
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final int REQUEST_CODE = 1;
    private EditText number;
    private Button call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number=findViewById(R.id.numbertocall);
        call=findViewById(R.id.call);
        call.setOnClickListener(this);
        if(checkPermission())
            getPermission();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.call:
                Intent callIntent=new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number.getText().toString()));
                if(!checkPermission())
                    getPermission();

                startActivity(callIntent);
                Toast.makeText(this,"calling",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void getPermission()
    {
        if(checkPermission())
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE);
        }
    }
    private boolean checkPermission()
    {
        if(ContextCompat.checkSelfPermission(this
        ,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        else
            return false;
    }
}
