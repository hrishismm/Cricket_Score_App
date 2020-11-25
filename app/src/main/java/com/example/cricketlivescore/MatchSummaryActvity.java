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

public class MatchSummaryActvity extends AppCompatActivity {
String url="https://cricapi.com/api/fantasySummary?apikey=htJrJeTM2rTPwqsM5RzMPG2u2sW2&unique_id=";

TextView field1Titletv,field2Titletv,field1TDetailtv,field2TDetailtv,
        bowl1Titletv,bowl2Titletv,bowl1TDetailtv,bowl2TDetailtv,
        bat1Titletv,bat2Titletv,bat1TDetailtv,bat2TDetailtv,
        otherresulttv;




@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_summary_actvity);

//ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Match Summary");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();

        String id=intent.getStringExtra("match_id");
        url =url+id;

    field1Titletv=findViewById(R.id.field1Titletv);
    field2Titletv=findViewById(R.id.field2Titletv);
    field1TDetailtv=findViewById(R.id.field1TDetailtv);

    field2TDetailtv=findViewById(R.id.field2TDetailtv);


    bowl1Titletv=findViewById(R.id.bowl1Titletv);
    bowl2Titletv=findViewById(R.id.bowl2Titletv);
    bowl1TDetailtv=findViewById(R.id.bowl1TDetailtv);

    bowl2TDetailtv=findViewById(R.id.bowl2TDetailtv);

    bat1Titletv=findViewById(R.id.bat1Titletv);
    bat2Titletv=findViewById(R.id.bat2Titletv);
    bat1TDetailtv=findViewById(R.id.bat1TDetailtv);

    bat2TDetailtv=findViewById(R.id.bat2TDetailtv);

    otherresulttv=findViewById(R.id.otherResulttv);




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
                    JSONObject dataObject=jsonObject.getJSONObject("data");

                    JSONArray fieldArray=dataObject.getJSONArray("fielding");

                    JSONArray bowlArray=dataObject.getJSONArray("bowling");

                    JSONArray batArray=dataObject.getJSONArray("batting");

                    JSONObject field0=fieldArray.getJSONObject(0);
                    JSONObject field1=fieldArray.getJSONObject(1);

                    String field1title=field0.getString("title");
                    String field2title= field1.getString("title");

                    JSONArray field1ScoresArray=field0.getJSONArray("scores");
                    JSONArray field2ScoresArray=field1.getJSONArray("scores");


                    field1Titletv.setText(field1title);

                    for(int i=0;i<field1ScoresArray.length();i++)
                    {
                        String name=field1ScoresArray.getJSONObject(i).getString("name");
                        String bowled=field1ScoresArray.getJSONObject(i).getString("bowled");
                        String catchh=field1ScoresArray.getJSONObject(i).getString("catch");
                        String lbw=field1ScoresArray.getJSONObject(i).getString("lbw");
                        String runout=field1ScoresArray.getJSONObject(i).getString("runout");
                        String stumped=field1ScoresArray.getJSONObject(i).getString("stumped");

field1TDetailtv.append("Name:"+name
        + "\n Bowled:" +bowled
        +"\n Catch:" +catchh
        +"\n LBW:" +lbw
        +"\n Runout:" +runout
        +"\n Stumped:" +stumped +"\n\n"


);

                    }


                    field2Titletv.setText(field2title);

                    for(int i=0;i<field2ScoresArray.length();i++)
                    {
                        String name=field2ScoresArray.getJSONObject(i).getString("name");
                        String bowled=field2ScoresArray.getJSONObject(i).getString("bowled");
                        String catchh=field2ScoresArray.getJSONObject(i).getString("catch");
                        String lbw=field2ScoresArray.getJSONObject(i).getString("lbw");
                        String runout=field2ScoresArray.getJSONObject(i).getString("runout");
                        String stumped=field2ScoresArray.getJSONObject(i).getString("stumped");

                        field2TDetailtv.append("Name:"+name
                                + "\n Bowled:" +bowled
                                +"\n Catch:" +catchh
                                +"\n LBW:" +lbw
                                +"\n Runout:" +runout
                                +"\n Stumped:" +stumped +"\n\n"


                        );



                    }


                    JSONObject bowl0=bowlArray.getJSONObject(0);
                    JSONObject bowl1=bowlArray.getJSONObject(1);

                    String bowl1title=bowl0.getString("title");
                    String bowl2title= bowl1.getString("title");

                    JSONArray bowl1ScoresArray=bowl0.getJSONArray("scores");
                    JSONArray bowl2ScoresArray=bowl1.getJSONArray("scores");

                    bowl1Titletv.setText(bowl1title);
                    for(int i=0;i<bowl1ScoresArray.length();i++) {
                        String bowlername = bowl1ScoresArray.getJSONObject(i).getString("bowler");
                        String overs = bowl1ScoresArray.getJSONObject(i).getString("O");
                        String wickets = bowl1ScoresArray.getJSONObject(i).getString("W");
                        String runs = bowl1ScoresArray.getJSONObject(i).getString("R");
                        String zeores = bowl1ScoresArray.getJSONObject(i).getString("0s");
                        String fours = bowl1ScoresArray.getJSONObject(i).getString("4s");
                        String sixes = bowl1ScoresArray.getJSONObject(i).getString("6s");
                        String M = bowl1ScoresArray.getJSONObject(i).getString("M");
                        String economy = bowl1ScoresArray.getJSONObject(i).getString("Econ");

                        bowl1TDetailtv.append("Name:" + bowlername
                                + "\n Overs:" + overs
                                + "\n Wickets:" + wickets
                                + "\n Runs:" + runs
                                + "\n Dots:" + zeores
                                + "\n Fours:" + fours
                                + "\n Sixes:" + sixes
                                + "\n Maidens:" + M
                                + "\n Ecomomy:" + economy + "\n\n"


                        );

                    }

                    bowl2Titletv.setText(bowl2title);
                    for(int i=0;i<bowl2ScoresArray.length();i++) {
                        String bowlername = bowl2ScoresArray.getJSONObject(i).getString("bowler");
                        String overs = bowl2ScoresArray.getJSONObject(i).getString("O");
                        String wickets = bowl2ScoresArray.getJSONObject(i).getString("W");
                        String runs = bowl2ScoresArray.getJSONObject(i).getString("R");
                        String zeores = bowl2ScoresArray.getJSONObject(i).getString("0s");
                        String fours = bowl2ScoresArray.getJSONObject(i).getString("4s");
                        String sixes = bowl2ScoresArray.getJSONObject(i).getString("6s");
                        String M = bowl2ScoresArray.getJSONObject(i).getString("M");
                        String economy = bowl2ScoresArray.getJSONObject(i).getString("Econ");

                        bowl2TDetailtv.append("Name:" + bowlername
                                + "\n Overs:" + overs
                                + "\n Wickets:" + wickets
                                + "\n Runs:" + runs
                                + "\n Dots:" + zeores
                                + "\n Fours:" + fours
                                + "\n Sixes:" + sixes
                                + "\n Maidens:" + M
                                + "\n Ecomomy:" + economy + "\n\n"


                        );
                    }
                    JSONObject bat0=batArray.getJSONObject(0);
                    JSONObject bat1=batArray.getJSONObject(1);

                    String bat1title=bat0.getString("title");
                    String bat2title= bat1.getString("title");

                    JSONArray bat1ScoresArray=bat0.getJSONArray("scores");
                    JSONArray bat2ScoresArray=bat1.getJSONArray("scores");

                    for(int i=0;i<bat1ScoresArray.length();i++) {
                        String batsman = bat1ScoresArray.getJSONObject(i).getString("batsman");
                        String balls = bat1ScoresArray.getJSONObject(i).getString("B");

                        String runs = bat1ScoresArray.getJSONObject(i).getString("R");
                        String four = bat1ScoresArray.getJSONObject(i).getString("4s");
                        String sixes = bat1ScoresArray.getJSONObject(i).getString("6s");
                        String Strikerate = bat1ScoresArray.getJSONObject(i).getString("SR");
                        String dismisalinfo = bat1ScoresArray.getJSONObject(i).getString("dismissal-info");
                        String dismissal="",dismissedBy="";
                        try{
                        dismissal=bat1ScoresArray.getJSONObject(i).getString("dismissal");
                      dismissedBy=bat1ScoresArray.getJSONObject(i).getJSONObject("dismissal-by").getString("name");

                        }
                        catch (Exception e)
                        {

                        }
                        bat1Titletv.setText(bat1title);
                        bat1TDetailtv.append("Batsman:" + batsman
                                + "\n Balls:" + balls
                                + "\n Runs:" + runs
                                + "\n Four:" + four
                                + "\n Sixes:" + sixes
                                + "\n S/R:" + Strikerate
                                + "\n Dismisal:" + dismissal
                                + "\n Dismisal Info:" + dismisalinfo
                                + "\n Dismissed By:" + dismissedBy
+
                                "\n\n"


                        );
                    }

                    for(int i=0;i<bat2ScoresArray.length();i++) {
                        String batsman = bat2ScoresArray.getJSONObject(i).getString("batsman");
                        String balls = bat2ScoresArray.getJSONObject(i).getString("B");

                        String runs = bat2ScoresArray.getJSONObject(i).getString("R");
                        String four = bat2ScoresArray.getJSONObject(i).getString("4s");
                        String sixes = bat2ScoresArray.getJSONObject(i).getString("6s");
                        String Strikerate = bat2ScoresArray.getJSONObject(i).getString("SR");
                        String dismisalinfo = bat2ScoresArray.getJSONObject(i).getString("dismissal-info");
                        String dismissal="",dismissedBy="";
                        try{
                            dismissal=bat2ScoresArray.getJSONObject(i).getString("dismissal");
                            dismissedBy=bat2ScoresArray.getJSONObject(i).getJSONObject("dismissal-by").getString("name");

                        }
                        catch (Exception e)
                        {

                        }
                        bat2Titletv.setText(bat2title);
                        bat2TDetailtv.append("Batsman:" + batsman
                                        + "\n Balls:" + balls
                                        + "\n Runs:" + runs
                                        + "\n Four:" + four
                                        + "\n Sixes:" + sixes
                                        + "\n SR:" + Strikerate
                                        + "\n Dismisal:" + dismissal
                                        + "\n Dismisal Info:" + dismisalinfo
                                        + "\n Dismissed By:" + dismissedBy +"\n\n");
                    }

                    String manOfTheMatch="",tossWinner="",winner="";
                    try{
                        manOfTheMatch=dataObject.getJSONObject("man-of-the-match").getString("name");

                    }
                    catch(Exception e)
                    {

                        Toast.makeText(MatchSummaryActvity.this, manOfTheMatch, Toast.LENGTH_SHORT).show();
                    }
                    try{
                        tossWinner=dataObject.getString("toss_winner_team");

                    }
                    catch(Exception e)
                    {
                        Toast.makeText(MatchSummaryActvity.this, tossWinner, Toast.LENGTH_SHORT).show();
                    }

                    try{
                        winner=dataObject.getString("winner_team");

                    }
                    catch(Exception e)
                    {
                        Toast.makeText(MatchSummaryActvity.this, winner, Toast.LENGTH_SHORT).show();

                    }
            otherresulttv.setText("Toss Winner:"+tossWinner
            +"\n winner:" +winner
        +"\n Man Of The Match:"+manOfTheMatch+"\n\n");
                }
                catch(Exception e)
                {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MatchSummaryActvity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
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