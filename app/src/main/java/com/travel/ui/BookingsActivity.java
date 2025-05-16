package com.travel.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.travel.R;
import com.travel.model.Booking;
import com.travel.model.Customer;
import com.travel.service.TravelService;

import java.util.List;

public class BookingsActivity extends AppCompatActivity {

    private ListView bookingsListView;
    private TravelService travelService = TravelService.getInstance(); // singleton instance
    private Customer customer = new Customer("CUST001", "Guest"); // replace with actual login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        bookingsListView = findViewById(R.id.bookingsListView);

        List<Booking> userBookings = travelService.getAllBookingsForCustomer(customer);

        if (userBookings.isEmpty()) {
            Toast.makeText(this, "You have no bookings.", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<Booking> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userBookings);
        bookingsListView.setAdapter(adapter);

        bookingsListView.setOnItemClickListener((parent, view, position, id) -> {
            Booking selectedBooking = userBookings.get(position);

            new AlertDialog.Builder(this)
                    .setTitle("Cancel Booking")
                    .setMessage("Are you sure you want to cancel this booking?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        boolean success = travelService.cancelBooking(selectedBooking.getBookingId());
                        if (success) {
                            userBookings.remove(position);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(this, "Booking cancelled.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Error cancelling booking.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}
