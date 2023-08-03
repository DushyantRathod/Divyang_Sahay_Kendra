package com.example.adip_scheme;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class viewAppliedSchemes extends AppCompatActivity {
    TextView t1;
    String sid;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_applied_schemes);

        t1 =findViewById(R.id.schemeName);
        t1.setText(getIntent().getExtras().getString("sid"));
        sid=t1.getText().toString();


    }
}
