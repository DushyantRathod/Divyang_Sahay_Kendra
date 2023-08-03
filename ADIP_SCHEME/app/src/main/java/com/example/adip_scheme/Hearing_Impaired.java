package com.example.adip_scheme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Hearing_Impaired extends AppCompatActivity {
    String[] schemeArrayHearing = {" Hearing Aids/Assistive Ustening Devices casting up to Rs.10,000/- for persons with Hearing Impairment 1. Body level hearing aids costing up to Rs. 3,000/","Analog Hearing aids/non programmable Digital Behind The Ear","Digital/Programmable/Adaptive automatic Digital ready to fit Hearing alds Behind the Ear (BTE) hearing alds costing upto Rs.10, 000/"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AdapterView<Adapter> listView = null;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}