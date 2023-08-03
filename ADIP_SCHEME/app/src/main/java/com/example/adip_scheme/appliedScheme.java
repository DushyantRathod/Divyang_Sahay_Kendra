package com.example.adip_scheme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class appliedScheme extends AppCompatActivity

{
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference appReference = database.getReference("applied");

    //String sid;
    ArrayList<ModelClass> arrayList;
    ArrayList<String> listItems;
    ArrayList<String> temp=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    TextView t1;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applied_schemes);
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //t1=findViewById(R.id.count);
       // loadData();
        //sid=getIntent().getExtras().getString("sid");
        //saveDate(getIntent().getExtras().getString("sid"));

        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String UDID = preferences1.getString("udid", null);
        String NAME = preferences1.getString("name", null);
        String DTYPE = preferences1.getString("dtype", null);
        Log.d("Firebase","proooo"+UDID+NAME+DTYPE);

        loadDataApplied();





    }

    private void loadDataApplied()
    {
        Log.d("Firebase","there");
        listview =findViewById(R.id.appliedSchemelist);
        listItems=new ArrayList<String>(temp);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);


        listview.setAdapter(adapter);

        appReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listItems.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren())
                {
                    // Retrieve user data
                    Applied applied = userSnapshot.getValue(Applied.class);



                        listItems.add(applied.getApplied());
                    Log.d("Firebase",applied.getApplied());




                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Log.e("Firebase", "Failed to read value.", databaseError.toException());}
        });


    }

//    private void loadData() {
//        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
//        Gson gson=new Gson();
//        String json=sharedPreferences.getString("schemelist",null);
//        Type type=new TypeToken<ArrayList<ModelClass>>(){
//
//        }.getType();
//        arrayList=gson.fromJson(json,type);
//
//        if(arrayList==null)
//        {
//            arrayList=new ArrayList<>();
//            //t1.setText(""+0);
//        }
//        else
//        {
//            for (int i=0;i<arrayList.size();i++)
//            {
//                //t1.setText(t1.getText().toString()+"\n"+arrayList.get(i).s+"\n");
//
//
//                adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
//
//                listItems.add(arrayList.get(i).s);
//
//                listview.setAdapter(adapter);
//                //adapter.notifyDataSetChanged();
//                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
//                    {
//                        //Toast.makeText(Home_page.this,schemeArray[position],Toast.LENGTH_SHORT).show();
//                        String str=listview.getAdapter().getItem(position).toString();
//
//                        Intent intent=new Intent(getApplicationContext(),viewAppliedSchemes.class);
//                        intent.putExtra("sid",str);
//                        startActivity(intent);
//                    }
//                });
//            }
//
//        }
//
//
//    }
//
//    private void saveDate(String sid) {
//        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//
//        Gson gson=new Gson();
//        arrayList.add(new ModelClass(sid));
//        String json=gson.toJson(arrayList);
//        editor.putString("schemelist",json);
//        editor.apply();
//        //t1.setText("");
//        adapter.clear();
//        loadData();
//
//
//    }
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
                Intent intent2=new Intent(getApplicationContext(),Home_page.class);
                //intent.putExtra("uid",user.getUdid());
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}