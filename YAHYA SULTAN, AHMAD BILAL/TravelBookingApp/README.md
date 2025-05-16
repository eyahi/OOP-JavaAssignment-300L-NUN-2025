Travel Booking App

A JavaFX desktop application that simulates a simple travel booking system. It allows users to:

* 🔍 Search for flights by origin, destination, and date
* 🛏️ Book flights along with a hotel stay
* 📋 View, modify, or cancel existing bookings
* 📄 Generate PDF receipts for confirmed bookings

---

## 🌟 Features

1. **User Authentication**

   * Login with username & password
   * Create new user accounts with secure SHA-256 password hashing
   * Default admin account (`admin` / `password`) for quick demo

2. **Flight Search & Booking**

   * Filter flights by origin, destination, and date
   * Interactive dialogs to select number of seats and hotel
   * Real-time update of flight seat availability in the session

3. **Booking Management**

   * View all bookings in a table
   * Modify seat count with automatic cost recalculation
   * Cancel bookings with confirmation dialog

4. **Receipt Generation**

   * Generate a PDF receipt for any booking using Apache PDFBox
   * Saves receipts as `data/receipt_<bookingId>.pdf`

## 🛠️ Technologies Used

* **Java 17**
* **JavaFX 17** (FXML + CSS for UI)
* **Maven** for build and dependency management
* **Apache Commons CSV** for CSV file I/O
* **Apache PDFBox** for PDF generation

## 🚀 Prerequisites

* Java Development Kit (JDK) 17
* Apache Maven 3.6+

## 📥 Installation & Setup

1. **Extract** the project:

   ```bash
   # Extract TravelBookingApp.zip
   cd TravelBookingApp
   ```

2. **Ensure** the `data/` directory exists in the project root and contains:

   * `flights.csv`
   * `hotels.csv`
   * `users.csv`
   * `bookings.csv`

3. **Build & run** with Maven:

   ```bash
   mvn clean compile
   mvn javafx:run
   ```

4. **Alternatively**, import the project into your IDE (IntelliJ, Eclipse, NetBeans) as a **Maven** project and run the `MainApp` class (`com.booking.ui.MainApp`).

## 🗂️ Project Structure

```
TravelBookingApp/
├─ data/                 # CSV data files
│  ├─ flights.csv
│  ├─ hotels.csv
│  ├─ users.csv
│  └─ bookings.csv
├─ src/main/java/com/booking/
│  ├─ model/             # Data models (Flight, Hotel, Booking, User)
│  ├─ service/           # Business logic & file I/O (TravelService)
│  ├─ ui/                # JavaFX controllers & application entry (MainApp)
│  └─ utils/             # CSV, password hashing, PDF utilities
├─ src/main/resources/
│  ├─ fxml/              # FXML layouts for each screen
│  └─ css/style.css      # Application stylesheet
└─ pom.xml               # Maven configuration
```

## 🎮 Usage

1. Launch the app and **log in** using the default account or create a new one.
2. From the **Main Menu**, choose an action:

   * **Search & Book**: Find flights and reserve seats + hotel.
   * **View Bookings**: Modify or cancel existing bookings.
   * **Generate Receipt**: Create a PDF receipt for a selected booking.
3. Use the **Back** button on each screen to return to the main menu.
4. Close the window to exit the application.

Student Name: Ahmad Bilal & sultan yahya

Student ID:20220227 & 20220140
