/**
 * Source : https://github.com/mitchtabian/SQLite-for-Beginners-2019
 */
package com.example.ramq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ramq.classes.AvailableCarpoolInformation;
import com.example.ramq.classes.recyclerviewhelper.AvailableCarpoolAdapter;

public class AvailableJoinCarpools extends AppCompatActivity implements AvailableCarpoolAdapter.OnCarpoolSelectListener {

    RecyclerView recyclerView;
    AvailableCarpoolInformation avi1 = new AvailableCarpoolInformation("Ruidi's Circus",2,2,"Hello!");
    AvailableCarpoolInformation avi2 = new AvailableCarpoolInformation("Cab2",3,1,"Hello!");
    AvailableCarpoolInformation avi3 = new AvailableCarpoolInformation("R Circus",1,3,"Hello!");
    AvailableCarpoolInformation avi4 = new AvailableCarpoolInformation("A Circus",2,2,"Hello!");
    AvailableCarpoolInformation avi5 = new AvailableCarpoolInformation("M Circus",3,2,"Hello!");
    AvailableCarpoolInformation avi6 = new AvailableCarpoolInformation("Q Circus",4,1,"Hello!");

    AvailableCarpoolInformation [] availableCarpoolsInformations = {avi1,avi2,avi3,avi4,avi5,avi6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_join_carpools);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AvailableCarpoolAdapter c = new AvailableCarpoolAdapter(availableCarpoolsInformations,this); //this is referring to the interface that was implemented
        recyclerView.setAdapter(c);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onCarpoolSelect(int position) {
        Toast.makeText(this,availableCarpoolsInformations[position].getCabName(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AvailableJoinCarpools.this,ScanQRCodeButtonActivity.class);
        SharedPreferences trip = getSharedPreferences("TripInformation",MODE_PRIVATE);
        SharedPreferences.Editor tripEdit = trip.edit();
        tripEdit.putString("qrCodeGenerated",availableCarpoolsInformations[position].getQrCodeAssociated());
        tripEdit.putString("numPassengers",String.valueOf(availableCarpoolsInformations[position].getNumPassengers()+1));
        tripEdit.putString("CarpoolName",String.valueOf(availableCarpoolsInformations[position].getCabName()));
        tripEdit.apply();
        startActivity(intent);
    }
}