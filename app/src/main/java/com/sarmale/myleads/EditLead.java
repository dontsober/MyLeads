package com.sarmale.myleads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sarmale.myleads.controller.DBLeadController;
import com.sarmale.myleads.model.DBLeadsHelper;
import com.sarmale.myleads.model.Lead;

import java.util.List;

public class EditLead extends AppCompatActivity {
    final String LEAD_EDITED="Lead Edited";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lead);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("ID_TO_EDIT");
            System.out.println(value);
            //The key argument here must match that used in the other activity
            DBLeadController db = new DBLeadController(getApplicationContext());
            final List<Lead> leads = db.searchLeadByID(value);
            if (leads.size()==1)
            {
                Lead leadToEdit = leads.get(0);
                final int leadID = leadToEdit.getId();
                final EditText name =  findViewById(R.id.editName);
                final EditText lastName =  findViewById(R.id.editLastName);
                final EditText leadPhone =  findViewById(R.id.editPhone);
                final EditText leadEmail =  findViewById(R.id.editEmail);

                name.setText(leadToEdit.getName());
                lastName.setText(leadToEdit.getLastName());
                leadPhone.setText(leadToEdit.getPhone());
                leadEmail.setText(leadToEdit.getEmail());

                Button buttonEdit = findViewById(R.id.editLeadButton);
                buttonEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Lead leadEdited = new Lead();
                        System.out.println("Pulado");
                        leadEdited.setId(leadID);
                        leadEdited.setName(name.getText().toString());
                        leadEdited.setLastName(lastName.getText().toString());
                        leadEdited.setPhone(leadPhone.getText().toString());
                        leadEdited.setEmail(leadEmail.getText().toString());

                        DBLeadController db = new DBLeadController(getApplicationContext());
                        db.updateLead(leadEdited);
                        Toast.makeText(getApplicationContext(),LEAD_EDITED,3).show();
                        Intent myIntent = new Intent(EditLead.this, LeadListed.class);

                        EditLead.this.startActivity(myIntent);

                    }
                });

            }

        }

    }
}