import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<String> preferences;
    private List<String> purchases;

    public Customer(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.preferences = new ArrayList<>();
        this.purchases = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void addPreference(String preference) {
        preferences.add(preference);
    }

    public List<String> getPurchases() {
        return purchases;
    }

    public void addPurchase(String purchase) {
        purchases.add(purchase);
    }

    @Override
    public String toString() {
        return "Customer Information: " +
                "First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Email='" + email + '\'' +
                ", Phone Number='" + phoneNumber + '\'' +
                "\nPreferences=" + preferences +
                "\nPurchases=" + purchases;
    }
}

public class AdvancedCRMSystem {
    private static List<Customer> customers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, List<String>> customerNotes = new HashMap<>();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Advanced CRM System Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Add Note to Customer");
            System.out.println("6. View Customer Notes");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        addCustomer();
                        break;
                    case 2:
                        viewCustomers();
                        break;
                    case 3:
                        searchCustomer();
                        break;
                    case 4:
                        deleteCustomer();
                        break;
                    case 5:
                        addNoteToCustomer();
                        break;
                    case 6:
                        viewCustomerNotes();
                        break;
                    case 7:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        System.out.println("Advanced CRM System is closed.");
    }

    private static void addCustomer() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        Customer customer = new Customer(firstName, lastName, email, phoneNumber);
        customers.add(customer);

        // After adding customer details, you can add preferences and purchases:
        System.out.print("Enter customer preferences (comma-separated): ");
        String preferenceInput = scanner.nextLine();
        String[] preferences = preferenceInput.split(",");
        for (String preference : preferences) {
            customer.addPreference(preference.trim());
        }

        System.out.print("Enter customer purchases (comma-separated): ");
        String purchaseInput = scanner.nextLine();
        String[] purchases = purchaseInput.split(",");
        for (String purchase : purchases) {
            customer.addPurchase(purchase.trim());
        }

        System.out.println("Customer added successfully.");
    }

    private static void viewCustomers() {
        System.out.println("List of Customers:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void searchCustomer() {
        System.out.print("Enter the email of the customer to search: ");
        String email = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Customer found:");
                System.out.println(customer);
                return;
            }
        }

        System.out.println("Customer not found.");
    }

    private static void deleteCustomer() {
        System.out.print("Enter the email of the customer to delete: ");
        String email = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                customers.remove(customer);
                customerNotes.remove(email);
                System.out.println("Customer deleted successfully.");
                return;
            }
        }

        System.out.println("Customer not found.");
    }

    private static void addNoteToCustomer() {
        System.out.print("Enter the email of the customer to add a note: ");
        String email = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                System.out.print("Enter the note for the customer: ");
                String note = scanner.nextLine();

                customerNotes.computeIfAbsent(email, k -> new ArrayList<>()).add(note);
                System.out.println("Note added successfully.");
                return;
            }
        }

        System.out.println("Customer not found.");
    }

    private static void viewCustomerNotes() {
        System.out.print("Enter the email of the customer to view notes: ");
        String email = scanner.nextLine();

        List<String> notes = customerNotes.get(email);
        if (notes != null) {
            System.out.println("Notes for the customer with email '" + email + "':");
            for (String note : notes) {
                System.out.println("- " + note);
            }
        } else {
            System.out.println("Customer not found or no notes available.");
        }
    }
}