package com.example.adip_scheme;

import android.os.AsyncTask;

public class background extends AsyncTask<String,Void,String>
{
    @Override
    protected String doInBackground(String... voids)
    {
        String result="";
        String user=voids[0];
        String pass=voids[1];

        String conn="http://localhost:3306";

        return result;

    }
}
