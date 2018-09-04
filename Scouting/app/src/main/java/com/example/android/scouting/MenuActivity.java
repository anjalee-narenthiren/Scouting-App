package com.example.android.scouting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.android.scouting.SettingsActivity.sUserName;
import static com.example.android.scouting.SettingsActivity.sSignOut;

public class MenuActivity extends BaseActivity {
    public static final int RC_SIGN_IN = 1;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadData(); //update the matchInfoList -MUST DO FIRST-

        super.onCreate(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_menu);

        Button scout_bttn = findViewById(R.id.scout_bttn);
        scout_bttn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Log.v("MenuActivity", "Btn Pressed - Enter ScoutActivity");
                    Intent openScoutActivity = new Intent(MenuActivity.this, ScoutActivity.class);
                    startActivity(openScoutActivity);
                }
            }
        );

        Button view_data_bttn = findViewById(R.id.view_data_bttn);
        view_data_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Log.v("MenuActivity", "Btn Pressed - Enter ViewDataActivity");
                Intent openViewDataActivity = new Intent(MenuActivity.this, ViewDataActivity.class);
                startActivity(openViewDataActivity);
            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    //User is signed in
                    onSignedInInitialize(user.getDisplayName());
                } else {
                    //User is signed out
                    onSignedOutCleanup();
                    startActivityForResult(
                            // Get an instance of AuthUI based on the default app
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(true, true)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(MenuActivity.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(MenuActivity.this, "Sign in Cancelled", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    private void onSignedInInitialize(String userName){
        String editedName = userName;

            if (userName.contains(" ")) {
                try {
                    int endI = userName.indexOf(" ");
                    editedName = userName.substring(0, endI);
                } catch (Exception e) {
                    Log.e("MenuActivity", userName+" failed to split.");
                    Toast.makeText(MenuActivity.this, userName+" failed to split.", Toast.LENGTH_SHORT);
                    editedName = userName;
                }
            }

        sUserName = editedName;
    }

    private void onSignedOutCleanup(){
        sUserName = " ";
    }
}
