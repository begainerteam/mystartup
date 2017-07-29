package com.simplesite.komalauthor.bharatyuvasangh2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText mLoginEmailFIeld,mLoginPasswordField;
    private Button mLoginBtn;
    private ProgressDialog mProgress;
    private TextView mForgotpswdLoginTv;


    private Button mSignupBtn;

   // private SignInButton mGoogleBtn;
   // private Toolbar mToolbar;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        //  mToolbar=(Toolbar)findViewById(R.id.login_toolbar);
        // setSupportActionBar(mToolbar);
        // getSupportActionBar().setTitle("LOGIN ACCOUNT");

        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabaseUsers.keepSynced(true);
        mLoginBtn = (Button) findViewById(R.id.loginBtn);
        mSignupBtn = (Button) findViewById(R.id.signupBtn);
        mForgotpswdLoginTv=(TextView)findViewById(R.id.forgotpswdLoginTv);

        mForgotpswdLoginTv.setPaintFlags(mSignupBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
      //  mSignupBtn.setText(R.string.create_new_account);

       // mLoginBtn.setBackgroundColor(Color.TRANSPARENT);


        mLoginEmailFIeld = (EditText) findViewById(R.id.loginEmailField);
        mLoginPasswordField = (EditText) findViewById(R.id.loginPasswordField);
        mProgress = new ProgressDialog(this);
        //   mGoogleBtn=(SignInButton) findViewById(R.id.googleBtn);


        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regIntent);
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        mForgotpswdLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
            }
        });
    }



    private void checkLogin() {

        String email =mLoginEmailFIeld.getText().toString().trim();
        String password =mLoginPasswordField.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            mProgress.setMessage("Checking Login....");
            mProgress.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if(task.isSuccessful()){
                        mProgress.dismiss();

                        checkUserExist();

                    }
                    else {
                        mProgress.dismiss();
                        Toast.makeText(LoginActivity.this, "Error Login", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        }

    private void checkUserExist() {

        if (mAuth.getCurrentUser()!=null) {


            final String user_id = mAuth.getCurrentUser().getUid();
            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.hasChild(user_id)) {
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);

                    } else {
                        Intent setupIntent = new Intent(LoginActivity.this, SetupActivity.class);
                        setupIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(setupIntent);


                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


    }
 //   public static boolean isValidEmail(CharSequence target) {
 //       return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
  //  }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
