package com.example.cricketlivescore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class newsweb extends AppCompatActivity {
private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsweb);
        //ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Cricket News");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
    webView.loadUrl("https://www.cricbuzz.com/cricket-news");
    }
    }