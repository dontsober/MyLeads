package com.sarmale.myleads;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.PrecomputedText;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.sarmale.myleads.controller.DBLeadController;
import com.sarmale.myleads.model.Lead;

import org.json.JSONObject;

import java.util.List;

public class LeadListed extends AppCompatActivity {

    private static final int NUMBER_COLUMNS = 4;
    private static final String TEXT_DELETE = "delete";
    private static final String TEXT_DELETE_LEAD = "Deleted";
    private static final String TEXT_EDIT = "edit";

    @SuppressLint("ResourceType")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_listed);
        TableLayout leadTable = findViewById(R.id.tableAllLeads);
        TableRow tr_head = new TableRow(this);

        tr_head.setBackgroundColor(Color.GRAY);        // part1
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,

                TableLayout.LayoutParams.WRAP_CONTENT));

//////////// ADD HEADERS
        TextView label_hello = new TextView(this);

        label_hello.setText("Name");
        label_hello.setTextColor(Color.WHITE);          // part2
        label_hello.setPadding(5, 5, 5, 5);
        label_hello.setTypeface(label_hello.getTypeface(), Typeface.BOLD);
        tr_head.addView(label_hello);// add the column to the table row here

        TextView label_android = new TextView(this);    // part3

        label_android.setText("Last Name"); // set the text for the header
        label_android.setTextColor(Color.WHITE); // set the color
        label_android.setPadding(5, 5, 5, 5); // set the padding (if required)
        label_android.setTypeface(label_android.getTypeface(), Typeface.BOLD);
        tr_head.addView(label_android); // add the column to the table row here

        TextView phoneLabel = new TextView(this);    // part3

        phoneLabel.setText("Phone"); // set the text for the header
        phoneLabel.setTextColor(Color.WHITE); // set the color
        phoneLabel.setPadding(5, 5, 5, 5); // set the padding (if required)
        phoneLabel.setTypeface(phoneLabel.getTypeface(), Typeface.BOLD);
       // tr_head.addView(phoneLabel); // add the column to the table row here

        TextView emailLabel = new TextView(this);    // part3

        emailLabel.setText("Email"); // set the text for the header
        emailLabel.setTextColor(Color.WHITE); // set the color
        emailLabel.setPadding(5, 5, 5, 5); // set the padding (if required)
        emailLabel.setTypeface(emailLabel.getTypeface(), Typeface.BOLD);
        tr_head.addView(emailLabel); // add the column to the table row here

        TextView manageLabel = new TextView(this);    // part3

        manageLabel.setText("Manage"); // set the text for the header
        manageLabel.setTextColor(Color.WHITE); // set the color
        manageLabel.setPadding(5, 5, 5, 5); // set the padding (if required)
        manageLabel.setTypeface(manageLabel.getTypeface(), Typeface.BOLD);
        tr_head.addView(manageLabel); // add the column to the table row here


        leadTable.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,                    //part4
                TableLayout.LayoutParams.MATCH_PARENT));


        DBLeadController leadController = new DBLeadController(getApplicationContext());
        final List<Lead> storedLeads = leadController.readAllLeads(); //it wont change
       // int iDs=20;


        TextView[][] textArray = new TextView[storedLeads.size()][NUMBER_COLUMNS+2]; //+1 becuase I need to render the text to delete and edit
        TableRow[] rows = new TableRow[storedLeads.size()];

        for(int i=0; i<storedLeads.size();i++)
        {

            String name = storedLeads.get(i).getName();
            String lastName = storedLeads.get(i).getLastName();
            String phone = storedLeads.get(i).getPhone();
            String email = storedLeads.get(i).getEmail();

//Create the tablerows
            rows[i] = new TableRow(this);

            rows[i].setBackgroundColor(Color.GRAY);
            rows[i].setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));


            //
            textArray[i][0] = new TextView(this);

            textArray[i][0].setText(name);
            textArray[i][0].setTextColor(Color.WHITE);
            textArray[i][0].setPadding(5, 5, 5, 5);
            rows[i].addView(textArray[i][0]);

            textArray[i][1] = new TextView(this);

            textArray[i][1].setText(lastName);
            textArray[i][1].setTextColor(Color.WHITE);
            textArray[i][1].setPadding(5, 5, 5, 5);
            rows[i].addView(textArray[i][1]);

            textArray[i][2] = new TextView(this);

            textArray[i][2].setText(phone);
            textArray[i][2].setTextColor(Color.WHITE);
            textArray[i][2].setPadding(5, 5, 5, 5);
            //rows[i].addView(textArray[i][2]);

            textArray[i][3] = new TextView(this);

            textArray[i][3].setId((i+1)*4);
            textArray[i][3].setText(email);
            textArray[i][3].setTextColor(Color.WHITE);
            textArray[i][3].setPadding(5, 5, 5, 5);
            ViewGroup.LayoutParams params  =  new LayoutParams();
            params.width = 200;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            textArray[i][3].setLayoutParams(params);


            textArray[i][3].setSingleLine(true);
            textArray[i][3].setEllipsize(TextUtils.TruncateAt.END);


            rows[i].addView(textArray[i][3]);

            textArray[i][4] = new TextView(this);
            textArray[i][4].setId(i+1);

            textArray[i][4].setTextColor(Color.RED);
            textArray[i][4].setText(TEXT_DELETE);
            textArray[i][4].setTypeface(textArray[i][4].getTypeface(), Typeface.BOLD);

            textArray[i][4].setPadding(5, 5, 5, 5);
            rows[i].addView(textArray[i][4]);

            textArray[i][5] = new TextView(this);
            textArray[i][5].setId((i+1)+storedLeads.size());

            textArray[i][5].setTextColor(Color.parseColor("#EBC400"));
            textArray[i][5].setText(TEXT_EDIT);
            textArray[i][5].setTypeface(textArray[i][5].getTypeface(), Typeface.BOLD);

            textArray[i][5].setPadding(5, 5, 5, 5);
            rows[i].addView(textArray[i][5]);

            leadTable.addView(rows[i], new TableLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

        } // end of for loop

        for (int i = 0; i <  storedLeads.size(); i++) {
            textArray[i][NUMBER_COLUMNS].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBLeadController dbLeadController = new DBLeadController(getApplicationContext());
                    Lead leadToDelete = storedLeads.get(view.getId()-1);
                    String toastText = leadToDelete.getEmail() +" " + TEXT_DELETE_LEAD;
                    if(dbLeadController.deleteLead(leadToDelete))
                    {
                        Toast.makeText(getApplicationContext(),toastText,3).show();
                        startActivity(getIntent());
                    }
                }
            });

        }
        for (int i = 0; i <  storedLeads.size(); i++) {
            textArray[i][NUMBER_COLUMNS+1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int leadPos= (view.getId()-1)-storedLeads.size();
                    Lead leadToEdit = storedLeads.get(leadPos);

                    Intent myIntent = new Intent(LeadListed.this, EditLead.class);
                    myIntent.putExtra("ID_TO_EDIT", String.valueOf(leadToEdit.getId()));
                    LeadListed.this.startActivity(myIntent);
                }
            });

        }

    }

}
