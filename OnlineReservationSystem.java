import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}

class Reservation {
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String source;
    private String destination;

    public Reservation(String trainNumber, String trainName, String classType, String dateOfJourney, String source, String destination) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.source = source;
        this.destination = destination;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getClassType() {
        return classType;
    }

    public String getDateOfJourney() {
        return dateOfJourney;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}

public class OnlineReservationSystem {
    private static User[] users = {
        new User("admin", "admin123"),
        new User("user", "user123")
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Online Reservation System");

        // Login
        boolean loggedIn = false;
        User currentUser = null;
        do {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            for (User user : users) {
                if (user.authenticate(username, password)) {
                    loggedIn = true;
                    currentUser = user;
                    break;
                }
            }

            if (!loggedIn) {
                System.out.println("Invalid username or password. Please try again.");
            }
        } while (!loggedIn);

        System.out.println("Login successful!");

        // Reservation
        System.out.println("Reservation Form");
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter source: ");
        String source = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        Reservation reservation = new Reservation(trainNumber, "Sample Train Name", classType, dateOfJourney, source, destination);

        // Display reservation details
        System.out.println("\nReservation Details:");
        System.out.println("Train Number: " + reservation.getTrainNumber());
        System.out.println("Train Name: " + reservation.getTrainName());
        System.out.println("Class Type: " + reservation.getClassType());
        System.out.println("Date of Journey: " + reservation.getDateOfJourney());
        System.out.println("Source: " + reservation.getSource());
        System.out.println("Destination: " + reservation.getDestination());

        // Cancellation
        System.out.println("\nCancellation Form");
        System.out.print("Enter PNR number to cancel ticket: ");
        String pnr = scanner.nextLine();
        // Logic to fetch reservation details by PNR and cancel the ticket

        System.out.println("Ticket with PNR " + pnr + " has been cancelled.");
    }
}
