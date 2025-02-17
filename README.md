# Ticket Management System (CLI)
A multi-threaded Java-based CLI system for managing ticket distribution between vendors and customers.  

## Features
- **Multi-threaded** → Vendors and customers run simultaneously using Java threads.
- **Synchronized Ticket Pool** → Prevents race conditions and enforces capacity limits.
- **Custom Configuration** → Load & save settings from `config.json` using Gson.
- **Logging System** → Tracks ticket transactions and system events in `logs.txt`.
- **Scalable** → Easily adjust ticket release and retrieval rates.

## Project Structure
```
📂 TicketManagementSystem/
├── 📂 src/ # Java source code
│ ├── 📂 main/ # Main entry point (Main.java)
│ ├── 📂 config/ # Configuration handling (Configuration.java)
│ ├── 📂 core/ # Ticket Pool & Operations (TicketPool.java)
│ ├── 📂 logging/ # Logger system (Logger.java)
│ ├── 📂 threads/ # Vendor & Customer Threads (Vendor.java, Customer.java)
│ ├── 📂 ui/ # Command Line Interface (CommandLineInterface.java)
├── 📂 lib/ # External libraries (Gson JAR)
├── 📂 resources/ # Config & logs (config.json, logs.txt)
├── .gitignore # Git ignore file
├── README.md # Project documentation

```

## Setup & Installation
### 1. Clone the Repository
```sh
git clone https://github.com/R-Tharanka/ticket-management-s-cli.git
cd ticket-management-s-cli
```

### 2. Install Java (if not installed)
Ensure you have **Java 8 or later** installed:
```sh
java -version
```

If Java is missing, download it from one of the official sources:  
- [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)  
- [OpenJDK](https://jdk.java.net/)  
- [Adoptium (Temurin JDK)](https://adoptium.net/) 

### 3. Run the Program  
Compile and run the CLI:
```sh
javac -cp "lib/gson-2.8.9.jar;src" -d bin src/main/Main.java
java -cp "lib/gson-2.8.9.jar;bin" main.Main
```
## How It Works  

### User Inputs Configuration:
```sh
Enter Total Tickets: 15
Enter Ticket Release Rate: 4
Enter Customer Retrieval Rate: 3
Enter Max Ticket Capacity: 12
```

### Vendors Add Tickets:
```sh
Vendor added: Ticket-2408142668884200
Vendor added: Ticket-2408142691035000
```
### Customers Retrieve Tickets:
```sh
Customer retrieved: Ticket-2408142668884200
```
### System Enforces Capacity Limits:
```sh
Ticket pool is full. Cannot add more tickets.
```
### Program Terminates Successfully:
```sh
System terminated.
```
## Technologies Used  
- **Java 8+**  
- **Multi-threading** (`Thread`, `synchronized`, `CountDownLatch`)  
- **File Handling** (`config.json` for persistence)  
- **Gson Library** (`lib/gson-2.8.9.jar`)  
- **Logging System** (`logs.txt`)  

## License  
This project is open-source under the **MIT License**. Feel free to modify and contribute!  

## Contributing  
```sh
# Fork the repository
git clone https://github.com/R-Tharanka/ticket-management-s-cli.git

# Create a new branch
git checkout -b feature-branch

# Commit your changes
git commit -m "Added new feature"

# Push to GitHub
git push origin feature-branch

# Submit a Pull Request
```
