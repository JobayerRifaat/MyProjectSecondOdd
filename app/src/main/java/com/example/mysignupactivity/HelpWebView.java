package com.example.mysignupactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HelpWebView extends AppCompatActivity {
    WebView helpWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_web_view);

        helpWebView = (WebView) findViewById(R.id.helpWebviewId);
        helpWebView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = helpWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        helpWebView.loadUrl("http://knowledgebank-brri.org/");

    }

    @Override
    public void onBackPressed() {
        if(helpWebView.canGoBack()){
            helpWebView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}