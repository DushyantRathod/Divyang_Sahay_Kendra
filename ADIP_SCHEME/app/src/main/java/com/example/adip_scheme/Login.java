package com.example.adip_scheme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;



public class Login extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        e1= (EditText) findViewById(R.id.udid);
//        e2=(EditText) findViewById(R.id.Pass);
        b1=(Button)findViewById(R.id.login);
        b2=(Button)findViewById(R.id.register);

//        String Pass = e2.getText().toString();
//        //String Udid= getIntent().getExtras().getString("sid");
//        String Udid = e1.getText().toString();

        //e1.setText(Udid);



        // Set click listener for the button
        b1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                EditText e1,e2;

                e1= (EditText) findViewById(R.id.udid);
                e2=(EditText) findViewById(R.id.Pass);
//                b1=(Button)findViewById(R.id.login);
//                b2=(Button)findViewById(R.id.register);

                String Udid,Pass;
                Pass = e2.getText().toString();
                //String Udid= getIntent().getExtras().getString("sid");
                Udid = e1.getText().toString();



    DatabaseReference userReference = database.getReference("users");

    userReference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            int c=0;
            for (DataSnapshot userSnapshot : dataSnapshot.getChildren())
            {
                // Retrieve user data
                User user = userSnapshot.getValue(User.class);

                // TODO: Handle the retrieved user data for each user
                // You can access the user's properties like user.getName(), user.getEmail(), user.getAge()

                String u=user.getUdid();
                String p=user.getPass();
                String n=user.getName();
//                Log.d("Firebase",u+p);
//                Log.d("Firebase",Udid+Pass+"userrrrrrr");
                if (u.equals(Udid) && p.equals(Pass))
                {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                    Log.d("Firebase","s");

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("udid", u);

                    editor.putString("name", n);
                    editor.apply();

                    DatabaseReference uReference = database.getReference("udid");
                    uReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                // Retrieve user data
                                Udid udid = userSnapshot.getValue(Udid.class);


                               // Log.d("Firebase", "Name: " + udid.getDtype());
                                String dtype=udid.getDtype();
                                //Log.d("Firebase", "udid: " + Udid);
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("dtype", dtype);
                                editor.apply();

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle errors
                            Log.e("Firebase", "Failed to read value.", databaseError.toException());
                        }});







//                    SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                    String value = preferences1.getString("udid", null);
//
//                    Log.d("Firebase","preeeeee"+value);



                    Intent intent=new Intent(getApplicationContext(),Home_page.class);
                    intent.putExtra("uid",user.getUdid());
                    startActivity(intent);
                        c=1;
                        break;
                }


            }
            if(c!=1)
            {
                Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_LONG).show();
                Log.d("Firebase","f");
            }

             }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle errors
                            Log.e("Firebase", "Failed to read value.", databaseError.toException());
                            }
                        });

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),insertRegister.class);
                //intent.putExtra("uid",user.getUdid());
                startActivity(intent);

            }
        });



    }


}
