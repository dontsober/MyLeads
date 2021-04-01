package com.sarmale.myleads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sarmale.myleads.controller.DBLeadController;
import com.sarmale.myleads.firebase.FirebaseConnector;
import com.sarmale.myleads.firebase.FirebaseInterface;
import com.sarmale.myleads.model.Lead;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createNew = findViewById(R.id.createNew);


        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBLeadController leadController = new DBLeadController(getApplicationContext());
                Lead leadToStore = new Lead();
                EditText name =  findViewById(R.id.LeadName);
                EditText lastName =  findViewById(R.id.LeadLastName);
                EditText leadPhone =  findViewById(R.id.LeadPhone);
                EditText leadEmail =  findViewById(R.id.LeadEmail);
                leadToStore.setName(name.getText().toString());
                leadToStore.setLastName(lastName.getText().toString());
                leadToStore.setPhone(leadPhone.getText().toString());
                leadToStore.setEmail(leadEmail.getText().toString());
                leadController.writeLeadDB(leadToStore);
                //to Firebase
                FirebaseInterface firebase = new FirebaseInterface();
                firebase.postLeadToFirebase(leadToStore);
                Intent myIntent = new Intent(MainActivity.this, LeadListed.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        Button showAll = findViewById(R.id.showAll);

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this, LeadListed.class);

                MainActivity.this.startActivity(myIntent);

            }
        });


    }
}