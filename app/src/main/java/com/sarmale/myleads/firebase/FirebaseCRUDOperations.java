package com.sarmale.myleads.firebase;

import com.google.firebase.database.DatabaseReference;
import com.sarmale.myleads.model.Lead;


public class FirebaseCRUDOperations {
    public void postNewLeadToFirebase(DatabaseReference myRef, Lead lead)  {
        String key = myRef.push().getKey();
        myRef.child("leads").child(key).setValue(lead);
    }

}
