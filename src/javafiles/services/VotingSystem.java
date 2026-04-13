package javafiles.services;

import java.io.*;
import java.util.*;
import javafiles.User.User;

public class VotingSystem {
    private static final List<User> users = new ArrayList<>();
    private static final Map<String, Integer> votes = new HashMap<>();

    private static final String USERS_FILE = "javafiles/users.txt";
    private static final String VOTES_FILE = "javafiles/votes.txt";

    static {
        loadUsers();
        loadVotes();
    }

    public static boolean register(String name, String email, String password) {
        if (name == null || name.isBlank() || email == null || email.isBlank() || password == null || password.isBlank()) {
            return false;
        }

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }

        User newUser = new User(name, email, password);
        users.add(newUser);
        saveUsers();
        return true;
    }

    public static User login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public static boolean verifyUser(User user, int enteredOtp, int actualOtp) {
        if (user == null) {
            return false;
        }

        if (enteredOtp == actualOtp) {
            user.setVerified(true);
            saveUsers();
            return true;
        }

        return false;
    }

    public static String vote(User user, String candidate) {
        if (user == null) {
            return "Login first.";
        }

        if (!user.isVerified()) {
            return "User not verified.";
        }

        if (user.isHasVoted()) {
            return "You already voted.";
        }

        if (!votes.containsKey(candidate)) {
            return "Invalid candidate.";
        }

        votes.put(candidate, votes.get(candidate) + 1);
        user.setHasVoted(true);

        saveVotes();
        saveUsers();

        return "Vote submitted successfully.";
    }

    public static String getResults() {
        return "Candidate A: " + votes.getOrDefault("Candidate A", 0) +
               "\nCandidate B: " + votes.getOrDefault("Candidate B", 0);
    }

    public static boolean adminLogin(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

    public static void resetElection() {
        votes.put("Candidate A", 0);
        votes.put("Candidate B", 0);

        for (User u : users) {
            u.setHasVoted(false);
            u.setVerified(false);
        }

        saveVotes();
        saveUsers();
    }

    public static String getAllUsers() {
        if (users.isEmpty()) {
            return "No registered users.";
        }

        StringBuilder sb = new StringBuilder();
        for (User u : users) {
            sb.append("Name: ").append(u.getName())
              .append(", Email: ").append(u.getEmail())
              .append(", Verified: ").append(u.isVerified())
              .append(", Voted: ").append(u.isHasVoted())
              .append("\n");
        }
        return sb.toString();
    }

    private static void loadUsers() {
        users.clear();
        File file = new File(USERS_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    String name = parts[0];
                    String email = parts[1];
                    String password = parts[2];
                    boolean verified = Boolean.parseBoolean(parts[3]);
                    boolean hasVoted = Boolean.parseBoolean(parts[4]);

                    users.add(new User(name, email, password, verified, hasVoted));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    private static void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User u : users) {
                bw.write(u.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private static void loadVotes() {
        votes.clear();
        votes.put("Candidate A", 0);
        votes.put("Candidate B", 0);

        File file = new File(VOTES_FILE);
        if (!file.exists()) {
            saveVotes();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String candidate = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    votes.put(candidate, count);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading votes: " + e.getMessage());
        }
    }

    private static void saveVotes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VOTES_FILE))) {
            for (Map.Entry<String, Integer> entry : votes.entrySet()) {
                bw.write(entry.getKey() + "=" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving votes: " + e.getMessage());
        }
    }
}