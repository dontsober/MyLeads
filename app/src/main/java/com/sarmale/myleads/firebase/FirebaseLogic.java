package com.sarmale.myleads.firebase;

import com.google.firebase.database.DatabaseReference;
import com.sarmale.myleads.model.Lead;


public class FirebaseLogic {
    public void postLeadToFirebase(Lead lead) {
        FirebaseCRUDOperations firebaseCRUDOperations = new FirebaseCRUDOperations();
        FirebaseConnector firebaseConnector = new FirebaseConnector();
        DatabaseReference myRef = firebaseConnector.connect();
        firebaseCRUDOperations.postNewLeadToFirebase(myRef, lead);
    }
}
