package com.simplesite.komalauthor.bharatyuvasangh2017;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Startupnews extends AppCompatActivity {

    WebView WebNirmal;
    ProgressDialog pd = null;
    FloatingActionButton fabmail;
    SwipeRefreshLayout swipe;
    PendingIntent pendingIntent;
    //Handler myHandler = new Handler();
   // Runnable run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startupnews);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My StartUp Ideas");

        FloatingActionButton fabmail = (FloatingActionButton) findViewById(R.id.fabhemanthmail);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        fabmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"komal.sambana111@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Hi ," + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }
        });




        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                LoadWeb();
                Toast.makeText(Startupnews.this, "Refreshing..", Toast.LENGTH_SHORT).show();
            }
        });

        LoadWeb();
    }

    public void LoadWeb(){

        WebNirmal = (WebView) findViewById(R.id.WebNirmal);
        WebNirmal.getSettings().setJavaScriptEnabled(true);
        WebNirmal.getSettings().setAppCacheEnabled(true);
        WebNirmal.loadUrl("http://mystartupidea.simplesite.com/");
        swipe.setRefreshing(true);
        WebNirmal.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                WebNirmal.loadUrl("file:///android_asset/error.html");
            }

            public  void  onPageFinished(WebView view, String url){

                //Hide the SwipeReefreshLayout

                swipe.setRefreshing(false);
            }

        });
    }


    @Override
    public void onBackPressed(){

        if (WebNirmal.canGoBack()){
            WebNirmal.goBack();
        }else {
            finish();
        }
    }
}
