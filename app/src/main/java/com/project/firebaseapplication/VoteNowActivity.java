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

import java.util.Objects;


public class VoteNowActivity extends AppCompatActivity {

    private CardView cardViewCandidate1, cardViewCandidate2, cardViewCandidate3, cardViewCandidate4;
    public int voteCount = 0;
    public static int c1 = 0;
    public static int c2 = 0;
    public static int c3 = 0;
    public static int c4 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_now);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Caste Vote");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cardViewCandidate1 = findViewById(R.id.card_candidate1);
        cardViewCandidate2 = findViewById(R.id.card_candidate2);
        cardViewCandidate3 = findViewById(R.id.card_candidate3);
        cardViewCandidate4 = findViewById(R.id.card_candidate4);

        cardViewCandidate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1++;
                voteCount++;
                Toast.makeText(VoteNowActivity.this, "Your vote has been casted" + "Votecount = " + voteCount + " C1 Value = " + c1, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VoteNowActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
        cardViewCandidate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2++;
                voteCount += 1;
                Toast.makeText(VoteNowActivity.this, "Your vote has been casted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VoteNowActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
        cardViewCandidate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c3++;
                voteCount += 1;
                Toast.makeText(VoteNowActivity.this, "Your vote has been casted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VoteNowActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
        cardViewCandidate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c4++;
                voteCount += 1;
                Toast.makeText(VoteNowActivity.this, "Your vote has been casted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VoteNowActivity.this, ResultActivity.class);
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
            NavUtils.navigateUpFromSameTask(VoteNowActivity.this);
        } else if (id == R.id.menu_refresh) {
            //Referesh Activity
            startActivity(getIntent());
            finish();
            overridePendingTransition(0,0);
        } else if (id == R.id.menu_vote) {
            Intent intent = new Intent(VoteNowActivity.this, PhoneVerificationActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_update_profile) {
            Intent intent = new Intent(VoteNowActivity.this, UpdateProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_update_email) {
            Intent intent = new Intent(VoteNowActivity.this, UpdateEmailActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.menu_change_password) {
            Intent intent = new Intent(VoteNowActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_delete_profile) {
            Intent intent = new Intent(VoteNowActivity.this, DeleteProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_settings) {
            Toast.makeText(VoteNowActivity.this, "menu_settings", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_logout) {
            UserProfileActivity.authProfile.signOut();
            Toast.makeText(VoteNowActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(VoteNowActivity.this, MainActivity.class);

            //Clear stack to prevent user coming back to VoteNowActivity on pressing back button after Logging out
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();       //Close VoteNowActivity
        } else {
            Toast.makeText(VoteNowActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}