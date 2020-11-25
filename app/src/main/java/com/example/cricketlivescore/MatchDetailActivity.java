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

import org.json.JSONObject;

public class MatchDetailActivity extends AppCompatActivity {

    private String url="https://cricapi.com/api/cricketScore?apikey=htJrJeTM2rTPwqsM5RzMPG2u2sW2&unique_id=";
TextView mTeam1,mTeam2,matchstatus,mdescription,mdate,mscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
        //ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Match Detail");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent=getIntent();

String id=intent.getStringExtra("match_id");

        String date=intent.getStringExtra("date");
url =url+id;
mTeam1=findViewById(R.id.team1Tv);
        mTeam2=findViewById(R.id.team2Tv);
        matchstatus=findViewById(R.id.status);
        mscore=findViewById(R.id.scoretv);
        mdescription=findViewById(R.id.descriptiontv);
        mdate=findViewById(R.id.date);


        mdate.setText(date);

        //get/set Data
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
     JSONObject jsonObject=new JSONObject(response);
     String team1=jsonObject.getString("team-1");
    String team2= jsonObject.getString("team-2");
    String match_status=jsonObject.getString("matchStarted");
    if(match_status.equals("true"))
    {
        match_status="Match Started";

    }
    else {
        match_status="Match not started";
    }

    //Set the data


mTeam1.setText(team1);
    mTeam2.setText(team2);
    matchstatus.setText(match_status);
    try{
        String score= jsonObject.getString("score");

        String description= jsonObject.getString("description");

        mdescription.setText(description);
mscore.setText(score);
    }
    catch(Exception e)
    {
        Toast.makeText(MatchDetailActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
    }





}
catch(Exception e)
{

}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MatchDetailActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
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