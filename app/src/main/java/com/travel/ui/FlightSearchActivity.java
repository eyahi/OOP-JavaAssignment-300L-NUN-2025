package com.travel.ui;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.travel.R;
import com.travel.model.Customer;
import com.travel.model.Flight;
import com.travel.model.Booking;
import com.travel.service.TravelService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import android.app.DatePickerDialog;
import java.util.Calendar;

public class FlightSearchActivity extends AppCompatActivity {

    private EditText edtFrom, edtTo, edtDate, edtPassengers;
    private Button btnSearch;
    private ListView flightListView;

    private TravelService travelService = TravelService.getInstance();

    private Customer customer = new Customer("CUST001", "Guest");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        edtFrom = findViewById(R.id.edtFrom);
        edtTo = findViewById(R.id.edtTo);
        edtDate = findViewById(R.id.edtDate);
        edtPassengers = findViewById(R.id.edtPassengers);
        btnSearch = findViewById(R.id.btnSearch);
        flightListView = findViewById(R.id.flightListView);
        edtDate.setOnClickListener(v -> showDatePickerDialog());
        btnSearch.setOnClickListener(v -> performSearch());
    }
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String formattedDate = String.format(Locale.US, "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                    edtDate.setText(formattedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    private void performSearch() {
        String from = edtFrom.getText().toString().trim();
        String to = edtTo.getText().toString().trim();
        String dateStr = edtDate.getText().toString().trim();
        String passengersStr = edtPassengers.getText().toString().trim();

        if (from.isEmpty() || to.isEmpty() || dateStr.isEmpty() || passengersStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Parse string into Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            sdf.setLenient(false);
            Date parsedDate = sdf.parse(dateStr);

            // Convert Date to LocalDate
            LocalDate localDate = parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            int passengers = Integer.parseInt(passengersStr);

            List<Flight> results = travelService.searchFlights(from, to, localDate);

            if (results.isEmpty()) {
                Toast.makeText(this, "No flights found", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayAdapter<Flight> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);
            flightListView.setAdapter(adapter);

            flightListView.setOnItemClickListener((adapterView, view, i, l) -> {
                Flight selectedFlight = results.get(i);
                Booking booking = travelService.bookFlight(customer, selectedFlight, passengers);
                if (booking != null) {
                    Toast.makeText(this, "Flight booked!\n" + booking.getBookingId(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Booking failed. Not enough seats.", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (ParseException e) {
            Toast.makeText(this, "Invalid date format. Use YYYY-MM-DD.", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number of passengers", Toast.LENGTH_SHORT).show();
        }
    }
}
