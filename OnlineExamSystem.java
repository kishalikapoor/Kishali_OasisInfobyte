import java.util.Scanner;

class User {
    private String username;
    private String password;
    // Other user details

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        // Initialize other details
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
    
    public void updateUsername(String newUsername) {
        this.username = newUsername;
    }
    
    // Additional methods for updating profile and other functionalities
}

class MCQ {
    private String question;
    private String[] options;
    private int correctOption;

    public MCQ(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}

public class OnlineExamSystem {
    private static final int EXAM_DURATION_SECONDS = 120; // 2 minutes
    private static final int NUM_QUESTIONS = 5;
    private static final Scanner scanner = new Scanner(System.in);

    private static User currentUser;
    private static double percentage;
    private static String result;

    public static void main(String[] args) {
        showLoginPage();
    }

    private static void showLoginPage() {
        System.out.println("Welcome to Online Examination System");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        loginUser(username, password);
    }

    private static void loginUser(String username, String password) {
        // Authenticate user - fetch user data from database or any storage
        // For simplicity, hardcoded user
        currentUser = new User("user123", "password123");

        if (currentUser.authenticate(username, password)) {
            System.out.println("Login successful!");
            showMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
            showLoginPage();
        }
    }

    private static void showMenu() {
        System.out.println("1. Update Profile and Password");
        System.out.println("2. Start Exam");
        System.out.println("3. View Result");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                updateProfileAndPassword();
                break;
            case 2:
                startExam();
                break;
            case 3:
                if (percentage >= 0) {
                    viewResult();
                } else {
                    System.out.println("No result available. Please complete the exam first.");
                    showMenu();
                }
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println("Invalid choice.");
                showMenu();
        }
    }

    private static void updateProfileAndPassword() {
        System.out.println("Update Profile and Password");
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        currentUser.updateUsername(newUsername);
        currentUser.updatePassword(newPassword);
        System.out.println("Profile and Password updated successfully.");
        showMenu();
    }

    private static void startExam() {
        System.out.println("Exam Started!");
        System.out.println("You have " + EXAM_DURATION_SECONDS + " seconds to complete.");

        // Simulate exam questions
        MCQ[] questions = generateQuestions();
        int score = 0;

        // Simulate timer
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (EXAM_DURATION_SECONDS * 1000);

        boolean[] answered = new boolean[NUM_QUESTIONS];
        int answeredCount = 0;

        while (System.currentTimeMillis() < endTime && answeredCount < NUM_QUESTIONS) {
            for (int i = 0; i < NUM_QUESTIONS; i++) {
                if (!answered[i]) {
                    System.out.println("Question " + (i + 1) + ": " + questions[i].getQuestion());
                    String[] options = questions[i].getOptions();
                    for (int j = 0; j < options.length; j++) {
                        System.out.println((char) ('A' + j) + ") " + options[j]);
                    }
                    System.out.print("Enter your choice (A, B, C, D): ");
                    String selectedOption = scanner.nextLine().toUpperCase();
                    if (selectedOption.equals("A") || selectedOption.equals("B") || selectedOption.equals("C") || selectedOption.equals("D")) {
                        answered[i] = true;
                        answeredCount++;
                        if (questions[i].isCorrect(selectedOption.charAt(0) - 'A')) {
                            score++;
                        }
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }

        System.out.println("Time's up!");
        System.out.println("Your score: " + score + "/" + NUM_QUESTIONS);
        percentage = (double) score / NUM_QUESTIONS * 100;
        System.out.println("Percentage: " + percentage + "%");
        result = percentage >= 60 ? "Pass" : "Fail";
        System.out.println("Result: " + result);
        showMenu();
    }

    private static MCQ[] generateQuestions() {
        // Generate sample questions
        MCQ[] questions = new MCQ[NUM_QUESTIONS];
        questions[0] = new MCQ("What is the capital of France?",
                new String[]{"Paris", "Rome", "Berlin", "Madrid"}, 0);
        questions[1] = new MCQ("What is the largest planet in our solar system?",
                new String[]{"Jupiter", "Mars", "Saturn", "Earth"}, 0);
        questions[2] = new MCQ("Which is the only mammal capable of flight?",
                new String[]{"Bat", "Bird", "Butterfly", "Penguin"}, 0);
        questions[3] = new MCQ("Which is the tallest mammal?",
                new String[]{"Giraffe", "Elephant", "Kangaroo", "Horse"}, 0);
        questions[4] = new MCQ("Which is the longest river in the world?",
                new String[]{"Nile", "Amazon", "Yangtze", "Mississippi"}, 0);
        return questions;
    }

    private static void viewResult() {
        System.out.println("Result:");
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Result: " + result);
        showMenu();
    }

    private static void logout() {
        System.out.println("Logging out...");
        currentUser = null;
        showLoginPage();
    }
}
