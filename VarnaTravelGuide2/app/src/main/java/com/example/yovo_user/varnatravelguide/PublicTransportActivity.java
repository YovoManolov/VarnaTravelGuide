package com.example.yovo_user.varnatravelguide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class PublicTransportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_transport);

        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl("https://www.varnatraffic.com/en");
    }

}
