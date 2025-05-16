Travel Booking System (Java Swing GUI)

Author: Ebele Bethel, Okwuobi Fawaz 
Student ID: 20221469, 20221183 
Date: 16th May, 2025

Project Description

This Java application simulates a simplified Travel Booking System. Users can:

- Search for available flights.
- Search for available hotels.
- Book flights and hotels.
- View their existing bookings.

The application uses:
- Swing for GUI
- OOP principles like encapsulation, association, and service abstraction
- Collections (`ArrayList`) to manage data
- Java Time API (`LocalDate`, `LocalDateTime`) for date management

Project Structure
TravelBookingSystem/
├── src/
│ ├── com/
│ │ ├── travel/
│ │ │ ├── model/ # Data Models: Flight, Hotel, Customer, Booking
│ │ │ ├── service/ # TravelService.java for booking logic
│ │ │ └── gui/ # TravelGUI.java (Swing-based UI)
├── README.md

Run
java com.travel.gui.TravelGUI


