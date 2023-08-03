package com.example.adip_scheme;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.TextView;
import android.widget.Toast;

class User {
    private String name;
    private String pass;
    private String udid;

    // Empty constructor required for Firebase
    public User() {}

    public User(String name, String pass, String udid)
    {
        this.name = name;
        this.pass = pass;
        this.udid = udid;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getUdid() {
        return udid;
    }
}

class Udid {
    private String name;
    private String dtype;
    private String udid;

    // Empty constructor required for Firebase
    public Udid() {}

    public Udid(String name, String dtype, String udid)
    {
        this.name = name;
        this.dtype = dtype;
        this.udid = udid;
    }

    public String getName() {
        return name;
    }

    public String getDtype() {
        return dtype;
    }

    public String getUdid() {
        return udid;
    }
}
public class insertRegister extends AppCompatActivity
{

    //private TextView messageTextView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userReference = database.getReference("users");
    DatabaseReference ur = database.getReference("udid");


    String databaseUrl = "https://neww-62d0a-default-rtdb.firebaseio.com/";
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReferenceFromUrl(databaseUrl);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);




    // Fetch data from Firebase Realtime Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef1 = database.getReference("message");
    DatabaseReference userRef = database.getReference("users");

    //myRef1.setValue("Hello, World 2!");



         Button addButton;
    // Find the EditText views by their IDs
//    editTextName = findViewById(R.id.editTextName);
//    editTextEmail = findViewById(R.id.editTextEmail);
//    editTextAge = findViewById(R.id.editTextAge);

    // Find the Button view by its ID
    addButton = findViewById(R.id.register);

    // Set click listener for the button
    addButton.setOnClickListener(new View.OnClickListener() {


        @Override
        public void onClick(View v) {
             EditText name,pass,pass2,udid;


            name = findViewById(R.id.name);
            pass = findViewById(R.id.PassOne);
            pass2 = findViewById(R.id.PassTwo);
            udid = findViewById(R.id.udid);


            String Name = name.getText().toString();
            String Pass = pass.getText().toString();
            String Pass2 = pass2.getText().toString();
            String Udid = udid.getText().toString();


            if (Pass.equals(Pass2))
            {

                Log.d("Firebase", "Name***************: " + Udid);

//                ur.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//
//                            String name = userSnapshot.child("name").getValue(String.class);
//                            String dtype = userSnapshot.child("dtype").getValue(String.class);
//                            String udid = userSnapshot.child("udid").getValue(String.class);
//
//
//                            Log.d("Firebase", "Name***************: " + udid);
//                            String t=udid;
//
//                            saveUserData();
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        // Handle errors
//                        Log.e("Firebase", "Failed to read value.", databaseError.toException());
//                    }
//                });

                DatabaseReference uReference = database.getReference("udid");

                uReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren())
                        {
                                            // Retrieve user data
                                            Udid udid = userSnapshot.getValue(Udid.class);


                                Log.d("Firebase", "Name: " + udid.getName());

                                Log.d("Firebase", "udid: " + udid.getUdid());
                                if(Udid.equals(udid.getUdid()))
                                {
                                    Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
                                    Log.d("Firebase", "-------------------------------------------" + udid.getUdid());
                                    saveUserData();

                                    Intent intent=new Intent(getApplicationContext(),Login.class);
                                    intent.putExtra("sid",udid.getUdid());
                                    startActivity(intent);
                                }
//                                else
//                                {
//                                    Toast.makeText(getApplicationContext(), "Entered UDID does not exist", Toast.LENGTH_LONG).show();
//                                }

                //                            String dtype = userSnapshot.child("dtype").getValue(String.class);

                                        }
                                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors
                        Log.e("Firebase", "Failed to read value.", databaseError.toException());}
                    });
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Passwords are not matching...", Toast.LENGTH_LONG).show();

            }
        }
    });


//    // Read from the database
//    myRef.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            // Retrieve the message from the DataSnapshot
//            String message = dataSnapshot.child("message").getValue(String.class);
//            Log.d("Firebase", "Fetched message: " + message); // Log the fetched message
//
//            // Set the message in the TextView
//            //messageTextView.setText(message);
//
//
//
//
//
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//            // Handle any errors that occur
//            Log.d("TAG", "Failed to read message.", databaseError.toException());
//        }
//    });

//    DatabaseReference userReference = database.getReference("users");
//
//    userReference.addListenerForSingleValueEvent(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                // Retrieve user data
//                User user = userSnapshot.getValue(User.class);
//
//                // TODO: Handle the retrieved user data for each user
//                // You can access the user's properties like user.getName(), user.getEmail(), user.getAge()
//
//                Log.d("Firebase", "Name: " + user.getName());
//                Log.d("Firebase", "Email: " + user.getPass());
//                Log.d("Firebase", "Age: " + user.getUdid());
//            }
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//            // Handle errors
//            Log.e("Firebase", "Failed to read value.", databaseError.toException());
//        }
//    });



}

    private void saveUserData() {
        EditText name,pass,udid;


        name = findViewById(R.id.name);
        pass = findViewById(R.id.PassOne);

        udid = findViewById(R.id.udid);
        // Get the input values
        String namee = name.getText().toString();
        String passs = pass.getText().toString();
        String udidN = udid.getText().toString();

        // Create a User object
        User user = new User(namee, passs, udidN);

        // Push the User object to the "users" node in Firebase
        userReference.push().setValue(user, new DatabaseReference.CompletionListener() {
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
    }

}
