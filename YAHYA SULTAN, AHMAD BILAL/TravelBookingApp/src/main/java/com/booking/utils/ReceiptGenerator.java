package com.booking.utils;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.booking.model.Booking;
import com.booking.model.Flight;
import com.booking.model.Hotel;

public class ReceiptGenerator {

    /**
     * Creates a very simple PDF receipt in outPath
     * with the booking/flights/hotel details.
     * Returns outPath on success.
     */
    public static String generate(Booking b, Flight f, Hotel h, String outPath) throws IOException {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                // Title
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
                cs.newLineAtOffset(50, 750);
                cs.showText("Booking Receipt");
                cs.endText();

                // Body
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 720);

                cs.showText("Booking ID  : " + b.getBookingId());
                cs.newLineAtOffset(0, -15);

                cs.showText("Customer    : " + b.getUsername());
                cs.newLineAtOffset(0, -30);
                

                cs.showText("Flight       : " + f.getFlightId() +
                            " (" + f.getOrigin() + "->" + f.getDestination() + ")");
                cs.newLineAtOffset(0, -15);

                cs.showText("Hotel        : " + h.getHotelId() +
                            " (" + h.getName() + ")");
                cs.newLineAtOffset(0, -15);

                cs.showText("Date         : " + b.getdate());
                cs.newLineAtOffset(0, -15);

                cs.showText("Seats   : " + b.getSeats());
                cs.newLineAtOffset(0, -15);

                cs.showText("Total Cost   : $" + b.getTotalCost());
                cs.endText();
            }

            doc.save(outPath);
        }

        return outPath;
    }
}
