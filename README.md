# ATM Interface Project

A secure, console-based Java application simulating an Automated Teller Machine (ATM) system. This project is built as part of the DecodeLabs Industrial Training Kit to demonstrate enterprise-grade architecture, data encapsulation, clean separation of concerns, and robust input error handling.


## Features

* **Secure PIN Authentication**: Restricts unauthorized account access using a secure PIN validation gateway.
* **Data Encapsulation**: Private state fields in the `BankAccount` class protect sensitive financial details, exposing them only through controlled access methods.
* **Core ATM Operations**:
  * **Check Balance**: Real-time read-only access to current account funds.
  * **Deposit Cash**: Validates and updates balances for positive cash deposits.
  * **Withdraw Cash**: Handles cash withdrawals with built-in overdraft and insufficient fund protection.
* **Robust Error Handling**: Prevents application crashes from malformed data types (e.g., typing text instead of numbers) or negative values[cite: 1].



## Project Structure

The project is structured into a single, cohesive file layout containing:
1. **`Main`**: The system bootstrap entry point that initializes mock accounts and launches the application.
2. **`BankAccount`**: The secure data vault managing balances, PIN verification, and business logic calculations.
3. **`ATM`**: The user interface manager controlling menus, user choices, and input validation routines.



## Getting Started & Installation

### Prerequisites
* Java Development Kit (JDK) installed on your system.
* A code editor like **Visual Studio Code (VS Code)** or any Java-compatible IDE

### Running the Project in VS Code
1. Clone the repository or download the `Main.java` file into your local machine:
   ```bash
   
