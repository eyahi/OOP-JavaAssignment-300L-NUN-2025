package com.travel.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.travel.R;

public class MainMenuActivity extends AppCompatActivity {

    Button btnFlights, btnHotels, btnBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(R.layout.activity_main);

        btnFlights = findViewById(R.id.btnFlights);
        btnHotels = findViewById(R.id.btnHotels);
        btnBookings = findViewById(R.id.btnBookings);

        btnFlights.setOnClickListener(v -> startActivity(new Intent(this, FlightSearchActivity.class)));
        btnHotels.setOnClickListener(v -> startActivity(new Intent(this, HotelSearchActivity.class)));
        btnBookings.setOnClickListener(v -> startActivity(new Intent(this, BookingsActivity.class)));
        Button btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> finishAffinity());

    }
}
