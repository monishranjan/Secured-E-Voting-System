package com.project.firebaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class VoteActivity extends AppCompatActivity {

    CardView cardViewVoteNow, cardViewResult, cardViewAboutUs, cardViewLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote2);

        cardViewVoteNow = findViewById(R.id.card_voteNow);
        cardViewLogout = findViewById(R.id.card_logout);

        //Face Verification/Fingerprint Page
        cardViewVoteNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoteActivity.this, VoteFingerprintActivity.class);
                startActivity(intent);
            }
        });

        //Result
//        cardViewResult.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(VoteActivity.this, ResultActivity.class);
//                startActivity(intent);
//            }
//        });

        //Logout
        cardViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VoteActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(VoteActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    //Creating ActionBar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate menu items
        getMenuInflater().inflate(R.menu.common_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //When any menu item is selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(VoteActivity.this);
        } else if (id == R.id.menu_refresh) {
            //Referesh Activity
            startActivity(getIntent());
            finish();
            overridePendingTransition(0,0);
        } else if (id == R.id.menu_vote) {
            Intent intent = new Intent(VoteActivity.this, PhoneVerificationActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_update_profile) {
            Intent intent = new Intent(VoteActivity.this, UpdateProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_update_email) {
            Intent intent = new Intent(VoteActivity.this, UpdateEmailActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.menu_change_password) {
            Intent intent = new Intent(VoteActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_delete_profile) {
            Intent intent = new Intent(VoteActivity.this, DeleteProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_settings) {
            Toast.makeText(VoteActivity.this, "menu_settings", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_logout) {
            UserProfileActivity.authProfile.signOut();
            Toast.makeText(VoteActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(VoteActivity.this, MainActivity.class);

            //Clear stack to prevent user coming back to UserProfileActivity on pressing back button after Logging out
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();       //Close UserProfileActivity
        } else {
            Toast.makeText(VoteActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }}