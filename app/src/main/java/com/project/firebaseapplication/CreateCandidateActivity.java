package com.project.firebaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateCandidateActivity extends AppCompatActivity {

    private EditText editTextCandidateName, editTextCandidatePartyName, edittextCandidateGender;
    private Button buttonRegisterCandidate;
    private ProgressBar progressBar;
    private static final String TAG="CreateCandidate";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CandidateInfo candidateInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_candidate);

        getSupportActionBar().setTitle("Create Candidate");

        progressBar = findViewById(R.id.progressBar);
        editTextCandidateName = findViewById(R.id.editText_candidate_name);
        editTextCandidatePartyName = findViewById(R.id.editText_candidate_party_name);
        edittextCandidateGender = findViewById(R.id.editText_candidate_gender);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("CandidateInfo");

        candidateInfo = new CandidateInfo();

        buttonRegisterCandidate = findViewById(R.id.button_registerCandidate);
        buttonRegisterCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String candidateName = editTextCandidateName.getText().toString();
                String candidatePartyName = editTextCandidatePartyName.getText().toString();
                String candidateGender = edittextCandidateGender.getText().toString();

                if (TextUtils.isEmpty(candidateName) && TextUtils.isEmpty(candidatePartyName) && TextUtils.isEmpty(candidateGender)) {
                    Toast.makeText(CreateCandidateActivity.this, "Please add some datat.", Toast.LENGTH_SHORT).show();
                } else {
                    addDatatoFirebase(candidateName, candidatePartyName, candidateGender);
                }
            }
        });
    }

    private void addDatatoFirebase(String candidateName, String candidatePartyName, String candidateGender) {
        candidateInfo.setCandidateName(candidateName);
        candidateInfo.setCandidatePartyName(candidatePartyName);
        candidateInfo.setCandidateGender(candidateGender);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(candidateInfo);
                Toast.makeText(CreateCandidateActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CreateCandidateActivity.this, "Failed to add data" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}