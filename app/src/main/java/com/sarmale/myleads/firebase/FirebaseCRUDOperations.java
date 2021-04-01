package com.sarmale.myleads.firebase;

import com.google.firebase.database.DatabaseReference;
import com.sarmale.myleads.model.Lead;

public class FirebaseCRUDOperations {
    public void postLeadToFirebase(DatabaseReference myRef, Lead lead){
        myRef.child("leads").setValue(lead);
    }

}
