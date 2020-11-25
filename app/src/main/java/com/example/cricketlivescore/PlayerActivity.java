package com.example.cricketlivescore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlayerActivity extends AppCompatActivity {


    TextView team1new,team2new,team1player,team2player;

String a,b;


    private String url="https://cricapi.com/api/fantasySquad?apikey=htJrJeTM2rTPwqsM5RzMPG2u2sW2&unique_id=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        //ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Players");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();

        String id=intent.getStringExtra("match_id");
        url =url+id;
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        team1new=(TextView)findViewById(R.id.teamName1);
        team2new=(TextView)findViewById(R.id.teamName2);

        team1player=(TextView)findViewById(R.id.playerss1);
        team2player=(TextView)findViewById(R.id.playerss2);

        loadData();

    }

    private void loadData() {

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try{
                    JSONArray squadArray=new JSONObject(response).getJSONArray("squad");

                    JSONObject json0=squadArray.getJSONObject(0);
                    JSONObject json1=squadArray.getJSONObject(1);

                    String team1_Name=json0.getString("name");
                    String team2_Name=json1.getString("name");

                    JSONArray team1Array=json0.getJSONArray("players");
                    JSONArray team2Array=json1.getJSONArray("players");

                    Toast.makeText(PlayerActivity.this, team1_Name, Toast.LENGTH_SHORT).show();
                    Toast.makeText(PlayerActivity.this, team2_Name, Toast.LENGTH_SHORT).show();

                    team1new.setText(team1_Name);
                    team2new.setText(team2_Name);





                    for(int i=0;i<team1Array.length();i++)
                    {
                        String t1=team1Array.getJSONObject(i).getString("name");
                        team1player.append((i+1) + "" +t1 + "\n");
                    }

                    for(int i=0;i<team2Array.length();i++)
                    {
                        String t2=team2Array.getJSONObject(i).getString("name");
                        team2player.append((i+1) + ")" +t2 + "\n");
                    }




                }
                catch(Exception e)
                {
                    Toast.makeText(PlayerActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PlayerActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

//Enque the request
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}