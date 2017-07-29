package com.simplesite.komalauthor.bharatyuvasangh2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by KOMAL on 18-07-2017.
 */

public class AboutMystartup extends AppCompatActivity {

    private TextView txtDetails;
    private Button mnew_Post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutsac);

        mnew_Post=(Button)findViewById(R.id.new_Post);
        TextView txtDetails = (TextView) findViewById(R.id.txtDetails);
        txtDetails.setMovementMethod(new ScrollingMovementMethod());
        txtDetails.setText(" \n \nMy Startup is a app where you can share your idea and get a team to start your own startup.\n My Startup motivates you through your friends innovative ideas.\n here we will let you know what's going on in this startup world...");


        mnew_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent postIntent =new Intent(AboutMystartup.this,PostActivity.class);
                startActivity(postIntent);
            }
        });



    }
}
