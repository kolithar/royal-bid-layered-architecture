# RoyalBid - Auction Management System

RoyalBid is a comprehensive auction management system designed to streamline the management of auctions, including customers, evaluators, bidders, and all related processes. Built using Java 17, JavaFX, and MySQL, this application follows a layered architecture for a clean and scalable design.

## 🚀 Features
- **Customer Management**: Add, update, and manage customer information.
- **Evaluator Management**: Assign and manage evaluators for auctioned items.
- **Bidder Management**: Register and manage bidders and their bids.
- **Auction Management**: Oversee auction processes, items, and results.
- **Layered Architecture**: Improved separation of concerns for maintainability.
- **User-Friendly Interface**: Built with JavaFX for an intuitive and responsive UI.

## 🛠️ Technologies Used
- **Programming Language**: Java 17
- **Framework**: JavaFX
- **Database**: MySQL
- **Architecture**: Layered Architecture

## 📂 Project Structure
The project follows a layered architecture with clear separation of concerns:

```
RoyalBid/
│── src/
│   ├── com.royalbid.controller/    # Handles UI interactions and logic
│   ├── com.royalbid.service/       # Business logic layer
│   ├── com.royalbid.service.impl/  # Implementation of business logic
│   ├── com.royalbid.dao/           # Data access layer
│   ├── com.royalbid.dao.impl/      # Implementation of DAOs
│   ├── com.royalbid.model/         # Entity models
│   ├── com.royalbid.util/          # Utility classes
│── resources/                      # UI files (FXML, CSS, etc.)
│── database/                       # SQL scripts for database setup
│── README.md
```

## 📖 Prerequisites
- **Java Development Kit (JDK) 17 or higher**
- **JavaFX SDK** compatible with JDK 17
- **MySQL database server**
- **An IDE** such as IntelliJ IDEA, Eclipse, or NetBeans

## ⚙️ Setup Instructions
1. **Clone the Repository:**
   ```sh
   git clone https://github.com/kolithar/royal-bid-layered-architecture
   cd RoyalBid
   ```

2. **Setup MySQL Database:**
   - Create a MySQL database and import the provided SQL scripts from `database/`.

3. **Configure Database Connection:**
   - Update the database credentials in the `com.royalbid.util.DBConnection` class.

4. **Run the Application:**
   - Open the project in your IDE.
   - Build and run the JavaFX application.

## 📌 Future Enhancements
- Implement role-based authentication.
- Add real-time bidding functionalities.
- Enhance reporting features with analytics.

---
