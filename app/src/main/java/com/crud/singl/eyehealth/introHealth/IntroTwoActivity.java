package com.crud.singl.eyehealth.introHealth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crud.singl.eyehealth.R;
import com.crud.singl.eyehealth.entities.Knowledge;
import com.crud.singl.eyehealth.startup.BottomNavigationViewHelper;
import com.crud.singl.eyehealth.startup.MainActivity;
import com.crud.singl.eyehealth.startup.MenuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IntroTwoActivity extends AppCompatActivity {

    private BottomNavigationView bottomNave;

    //this is the JSON Data URL
    //make sure you are using the correct if else it will not work
    private static final String URL_KNOWLEDGE_TWO = "http://localhost/eye_health/android_knowlage_newapi/knowledge_two.php";


    //the recycclerview
    RecyclerView recyclerView;
    KnowledgeTwoAdapter adapter;

    //a list to store all the knowledge list one
    List<Knowledge> knowledgeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_two);

        bottomNave = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);

        //set bottom navigation view
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNave.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.screen_id:
                        bottomNave.setItemBackgroundResource(R.color.colorPrimary);
                        Intent intent0 = new Intent(IntroTwoActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.eye_id:
                        bottomNave.setItemBackgroundResource(R.color.colorAccent);
                        Intent intent1 = new Intent(IntroTwoActivity.this, MenuActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.intro_id:
                        bottomNave.setItemBackgroundResource(R.color.colorPrimary);
                        Intent intent2 = new Intent(IntroTwoActivity.this, IntroHealthActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });

        //initializing the knowledge list
        knowledgeList = new ArrayList<>();

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //this method will fetch and parse json
        //to display it in recyclerview
        loadKnowledges();
    }

    private void loadKnowledges() {
        /*
        Creating a string request
        The request type is GET defined by first parameter
        The URL is defined in the second parameter
        Then we have a response listener and a error listener
        In response listener we will get the JSON response as a string
         */


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_KNOWLEDGE_TWO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {
                                //getting knowledge object from json array
                                JSONObject knowledgeObject = array.getJSONObject(i);

                                int id_know = knowledgeObject.getInt("id_know");
                                String type = knowledgeObject.getString("type");
                                String name = knowledgeObject.getString("name");
                                String detail = knowledgeObject.getString("detail");
                                String image = knowledgeObject.getString("image");

                                Knowledge knowledge = new Knowledge(id_know, type, name, detail, image);
                                knowledgeList.add(knowledge);
                            }

                            //creating adapter object and setting it to recyclerview
                            adapter = new KnowledgeTwoAdapter(IntroTwoActivity.this, knowledgeList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(IntroTwoActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
