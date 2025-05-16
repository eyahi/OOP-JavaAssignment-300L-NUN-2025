# Travel Booking System (Android App)

**Names:**
 Bamikunle Opeyemi
 Obiajulu-Nduka Chukwunalu
 
**Student ID:** 
20221318  
20220318
---

## Project Description

This Android application allows users to search for flights and hotels, book them, and view or cancel bookings. The app provides a modern, intuitive interface with date pickers, list views, and clear navigation between activities. It simulates a basic travel booking system with locally stored data for demonstration purposes.

---

# How to Compile and Run

1. **Open Android Studio.**
2. Select **"Open an Existing Project"** and choose the root folder of this app.
3. Make sure your `minSdkVersion` is set to 21 or higher in `build.gradle`.
4. Click **Build > Rebuild Project** to compile.
5. Use **Run** or **Shift + F10** to launch on an emulator or device.

> 📦 All dependencies are managed via Gradle. No external libraries are required.

---

# Object-Oriented Principles Demonstrated

###  Encapsulation
Each class such as `Flight`, `Hotel`, `Booking`, and `Customer` encapsulates its own data and behavior. Attributes are marked as `private` and accessed via `getters` and `setters`.

### Association
Classes like `Booking` associate multiple objects — a `Booking` may reference both a `Flight` and a `Hotel`, showing real-world relationships.

### Separation of Concerns
The `TravelService` class acts as a **service layer**. It separates the app’s **business logic** (searching, booking, canceling) from the **UI logic** in Activities. This makes the codebase cleaner and easier to maintain or scale.

---

# Project Structure

- `com.travel.model`: Contains data models like `Flight`, `Hotel`, `Customer`, `Booking`
- `com.travel.service`: Contains `TravelService` for managing bookings, searches, and sample data
- `com.travel.ui`: Contains all user-facing activities like `FlightSearchActivity`, `HotelSearchActivity`, `BookingsActivity`, etc.

---

# Features

- Search and book flights between Nigerian cities
- Search and book hotels with guest count and date filters
- View all personal bookings
- Cancel any booking with seat/room adjustments
- DatePicker UI to ensure correct date input

---

# Testing

- Validated manually on API 21 emulator
- Tested edge cases for empty input fields and invalid dates
- Confirmed cancellation correctly restores availability
