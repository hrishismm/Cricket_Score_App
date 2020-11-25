package com.example.cricketlivescore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class news extends AppCompatActivity {
    private String url="https://newsapi.org/v2/top-headlines?country=us&apikey=5b3ef56758a14518a286c5dc135d2523";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Model1> modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mRecyclerView =findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList=new ArrayList<Model1>();
        loadUrlData();
    }

    private void loadUrlData() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {

                    JSONArray jsonArray=new JSONObject(response).getJSONArray("articles");

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        try{
                            String  author=jsonArray.getJSONObject(i).getString("source");
                            String  title=jsonArray.getJSONObject(i).getString("title");
                            String  description=jsonArray.getJSONObject(i).getString("description");
                            String  url=jsonArray.getJSONObject(i).getString("url");
                            String  content=jsonArray.getJSONObject(i).getString("content");

                            Model1 model1=new Model1(author,title,description,url,content);
                            modelList.add(model1);
                        }
                        catch (Exception e)
                        {

                            Toast.makeText(news.this, "", Toast.LENGTH_SHORT).show();
                        }
                    }


                    mAdapter=new MyAdapter2(modelList,getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);




                }
                catch(Exception e)
                {
                    Toast.makeText(news.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(news.this,"Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



}


