Developed by: [Chigozirim Igwe] and  [ Abrakasa Pearl 20221626 ] 
Student ID: [242030137]  and [20221626] 

Project Description

This is a simple Travel Booking System built using Java and the principles of Object-Oriented Programming (OOP).
The system allows a customer to:

Search available Flights 
Search available Hotels 
Book Flights or Hotels
View their Bookings



 Structure:

TravelBookingApp/
├── src/
│   ├── com/travel/
│   │   ├── model/
│   │   │   ├── Flight.java
│   │   │   ├── Hotel.java
│   │   │   ├── Booking.java
│   │   │   └── Customer.java
│   │   ├── service/
│   │   │   └── TravelService.java
│   │   └── main/
│   │       └── TravelApp.java
└── README.md

Features:

Search Flights by departure city, arrival city, and date
Search Hotels by city and date range
Book Flights for multiple passengers
Book Hotels for multiple guests
View Bookings at any time
Cancel Bookings (option available in the service class)

OOP Principles Used

Encapsulation:
All attributes are private with public getters and setters.
Association:
Booking associates a Customer with a Flight and/or Hotel.
Separation of Concerns:
Business logic is separated into TravelService.
The user interaction logic is handled by TravelApp.

Use of Collections:
Lists are used to manage multiple flights, hotels, and bookings.
Unique Identifiers:
Bookings are assigned unique IDs using UUID.

Technologies Used

Java SE 8+
java.time package for dates and times
java.util for collections and UUID

Notes

This is a console-based application (GUI can be added later).
Basic exception handling for invalid inputs (e.g., wrong date format).
Sample data is added at startup for Flights and Hotels.


