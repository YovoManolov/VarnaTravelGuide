package com.example.yovo_user.varnatravelguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class FindTaxiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_taxi);

        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl("http://visit.varna.bg/en/transport/preview/92.html");
    }
}
