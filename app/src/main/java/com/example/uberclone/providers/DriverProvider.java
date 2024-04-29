package com.example.uberclone.providers;

import com.example.uberclone.models.Driver;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class DriverProvider {
    DatabaseReference database;

    public DriverProvider() {
        // Note: You had a typo in FirebaseDatabase.getInstance(), and "childt" should be "child"
        database = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");
    }

    public Task<Void> create(Driver driver) {
        // Note: You had a typo in "setValuetclient", corrected to "setValue"
        return database.child(driver.getId()).setValue(driver);
    }
}