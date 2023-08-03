package com.example.adip_scheme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Home_page extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    ListView listview;
    TextView t1;
    String[] schemeArray = {"PERSONS WITH LOCOMOTOR DISABILITY", "PERSONS WITH VISUAL DISABILITY INCLUDING DEAF BLIND", "PERSONS WITH HEARING DISABILITY", "PERSONS WITH INTELLECTUAL AND DEVELOPMENTAL DISABILITIES ", "PERSON WITH MULTIPLE DISABILITY", "LEPROSY CURED PERSONS"};

    String[] locometer = {"All prosthetic and orthotic devices", "mobility aids", "surgical foot wears", "MCR chappals", "High end Prosthesis", "Motorized tricycles", " Motorized wheelchairs"};
    String[] visual = {"Low vision aids", "mobility aids", "Communication equipments", "Learning equipments", "assistive devices"};
    String[] hearing = {"f hearing aids", "Educational kits", "Assistive and Alarm devices", "assistive devices"};
    String[] intellectual = {"TLM Kits", "MSIED kits", "learning material "};
    String[] multiple = {"Any suitable device as advised"};
    String[] leprosy = {"Assistive Daily Living Kits", "Any suitable device as advised"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //String Udid = getIntent().getExtras().getString("uid");
        //Toast.makeText(getApplicationContext(),Udid, Toast.LENGTH_LONG).show();

        DatabaseReference uReference = database.getReference("udid");

        uReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve user data
                    Udid udid = userSnapshot.getValue(Udid.class);


                    Log.d("Firebase", "Name: " + udid.getDtype());
                    String dtype=udid.getDtype();
                    Log.d("Firebase", "udid: " + udid.getDtype());

                    listview = findViewById(R.id.list);
                    ArrayAdapter<String> adapter;
                    if(dtype.equals("hearing"))
                    {
                        adapter = new ArrayAdapter<String>(Home_page.this, android.R.layout.simple_dropdown_item_1line, hearing);
                        break;
                    } else if (dtype.equals("locometer")) {
                        adapter = new ArrayAdapter<String>(Home_page.this, android.R.layout.simple_dropdown_item_1line, locometer);
                    }
                    else if (dtype.equals("visual")) {
                        adapter = new ArrayAdapter<String>(Home_page.this, android.R.layout.simple_dropdown_item_1line, visual);
                    }
                    else if (dtype.equals("intellectual")) {
                        adapter = new ArrayAdapter<String>(Home_page.this, android.R.layout.simple_dropdown_item_1line, intellectual);
                    }else if (dtype.equals("multiple")) {
                        adapter = new ArrayAdapter<String>(Home_page.this, android.R.layout.simple_dropdown_item_1line, multiple);

                    }else {
                        adapter = new ArrayAdapter<String>(Home_page.this, android.R.layout.simple_dropdown_item_1line, leprosy);

                    }


                    //Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
                        //Log.d("Firebase", "-------------------------------------------" + udid.getUdid());
                        //saveUserData();

//                        Intent intent = new Intent(getApplicationContext(), Login.class);
//                        intent.putExtra("sid", udid.getUdid());
//                        startActivity(intent);




                    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(Home_page.this, android.R.layout.simple_dropdown_item_1line, schemeArray);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            //Toast.makeText(Home_page.this,schemeArray[position],Toast.LENGTH_SHORT).show();
                            String str = listview.getAdapter().getItem(position).toString();

                            Intent intent = new Intent(getApplicationContext(), hearing1scheme.class);
                            intent.putExtra("sid", str);
                            //intent.putExtra("udid", Udid);

                            startActivity(intent);
                        }
                    });


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Log.e("Firebase", "Failed to read value.", databaseError.toException());
            }
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