package com.travel.ui;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.travel.R;
import com.travel.model.Booking;
import com.travel.model.Customer;
import com.travel.model.Hotel;
import com.travel.service.TravelService;

import java.time.LocalDate;
import java.util.List;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import android.app.DatePickerDialog;
import java.util.Calendar;

import java.time.ZoneId;
public class HotelSearchActivity extends AppCompatActivity {

    private EditText edtCity, edtCheckIn, edtCheckOut, edtGuests;
    private Button btnSearchHotel;
    private ListView hotelListView;

    private TravelService travelService = TravelService.getInstance();

    private Customer customer = new Customer("CUST001", "Guest");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);

        edtCity = findViewById(R.id.editCity);
        edtCheckIn = findViewById(R.id.editCheckIn);
        edtCheckOut = findViewById(R.id.editCheckOut);
        edtGuests = findViewById(R.id.editGuests);
        btnSearchHotel = findViewById(R.id.btnSearchHotels);
        hotelListView = findViewById(R.id.hotelListView);

        edtCheckIn.setOnClickListener(v -> showDatePickerDialog(edtCheckIn));
        edtCheckOut.setOnClickListener(v -> showDatePickerDialog(edtCheckOut));

        btnSearchHotel.setOnClickListener(v -> performSearch());
    }
    private void showDatePickerDialog(EditText targetField) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String formattedDate = String.format(Locale.US, "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                    targetField.setText(formattedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    private void performSearch() {
        String city = edtCity.getText().toString().trim();
        String checkInStr = edtCheckIn.getText().toString().trim();
        String checkOutStr = edtCheckOut.getText().toString().trim();
        String guestsStr = edtGuests.getText().toString().trim();

        if (city.isEmpty() || checkInStr.isEmpty() || checkOutStr.isEmpty() || guestsStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            sdf.setLenient(false);
            Date checkIn = sdf.parse(checkInStr);
            Date checkOut = sdf.parse(checkOutStr);
            int guests = Integer.parseInt(guestsStr);

// ✅ Convert to LocalDate
            assert checkIn != null;
            LocalDate checkInLocal = checkIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            assert checkOut != null;
            LocalDate checkOutLocal = checkOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            List<Hotel> results = travelService.searchHotels(city, checkInLocal, checkOutLocal, guests); // requires TravelService update

            if (results.isEmpty()) {
                Toast.makeText(this, "No hotels found", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayAdapter<Hotel> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);
            hotelListView.setAdapter(adapter);

            hotelListView.setOnItemClickListener((adapterView, view, i, l) -> {
                Hotel selectedHotel = results.get(i);
                Booking booking = travelService.bookHotel(customer, selectedHotel, checkInLocal, checkOutLocal, guests);
                if (booking != null) {
                    Toast.makeText(this, "Hotel booked!\n" + booking.getBookingId(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Booking failed. Not enough rooms.", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (ParseException e) {
            Toast.makeText(this, "Invalid date format. Use YYYY-MM-DD.", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid guest number", Toast.LENGTH_SHORT).show();
        }
    }}
