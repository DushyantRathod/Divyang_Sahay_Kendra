package com.example.adip_scheme;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class Applied {
    private String applied;
    private String dtype;
    private String udid;
    private String name;



    // Empty constructor required for Firebase
    public Applied() {}

    public Applied(String applied,String dtype, String name, String udid)
    {
        this.name = name;
        this.dtype = dtype;
        this.applied = applied;
        this.udid = udid;
    }

    public String getName() {
        return name;
    }

    public String getUdid() {
        return udid;
    }
    public String getApplied() {
        return applied;
    }

    public void setApplied(String applied) {
        this.applied = applied;
    }
}
public class hearing1scheme extends AppCompatActivity{
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference appReference = database.getReference("applied");
        String sid;
        TextView t1;
        Button apply;
        CheckBox agree;
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_hearing1scheme);
            ActionBar actionBar = getSupportActionBar();

            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);

            t1 =findViewById(R.id.schemeName);
            t1.setText(getIntent().getExtras().getString("sid"));

            sid=t1.getText().toString();
            addListenerOnButtonClick();

//            SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//            String value = preferences1.getString("udid", null);
//
//
//            Log.d("Firebase","proooo"+value);
        }
    public void addListenerOnButtonClick()
    {

        agree=(CheckBox)findViewById(R.id.agree);
        apply=(Button)findViewById(R.id.apply);

        //Applying the Listener on the Button click
        apply.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {



                if (agree.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "applied", Toast.LENGTH_LONG).show();
                    saveUserData();

                    Intent intent=new Intent(getApplicationContext(),Home_page.class);
                    String uu=getIntent().getExtras().getString("udid");
                    intent.putExtra("udid",sid);
                    startActivity(intent);


                }
                else {
                    Toast.makeText(getApplicationContext(), "agree to proceed", Toast.LENGTH_LONG).show();
                }


            }

        });
    }

    private void saveUserData()
    {

        DatabaseReference uReference = database.getReference("udid");

        uReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren())
                {
                    // Retrieve user data
                    Udid udid = userSnapshot.getValue(Udid.class);


                        String app,dtype,name,udids;


                    app=getIntent().getExtras().getString("sid");
                    String uu=getIntent().getExtras().getString("udid");
                    String u=udid.getUdid();
                    if (u.equals(uu))
                    {
                        dtype=udid.getDtype();
                        name=udid.getName();
                        // Create a User object
                        Applied applied = new Applied(app,dtype,name,u);

                        // Push the User object to the "users" node in Firebase
                        appReference.push().setValue(applied, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError == null) {
                                    // Data saved successfully
                                    Toast.makeText(getApplicationContext(), "User data saved successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Data save failed
                                    Toast.makeText(getApplicationContext(), "Failed to save user data", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Log.e("Firebase", "Failed to read value.", databaseError.toException());}
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_user:
                // Handle button 1 click
                Intent intent=new Intent(getApplicationContext(),Login.class);
                //intent.putExtra("uid",user.getUdid());
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "User Logged out", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                // Handle button 2 click
                Intent intent1=new Intent(getApplicationContext(),Home_page.class);
                //intent.putExtra("uid",user.getUdid());
                startActivity(intent1);
                return true;
            case R.id.action_scheme:
                // Handle button 2 click
                Intent intent2=new Intent(getApplicationContext(),appliedScheme.class);
                //intent.putExtra("uid",user.getUdid());
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}