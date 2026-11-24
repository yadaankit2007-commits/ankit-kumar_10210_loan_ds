import java.util.*;
 
// Loan Application Class
class LoanApplication {
    String id;
    String applicantName;
    double loanAmount;
    int priority;
 
    public LoanApplication(String id, String applicantName, double loanAmount, int priority) {
        this.id = id;
        this.applicantName = applicantName;
        this.loanAmount = loanAmount;
        this.priority = priority;
    }
 
    @Override
    public String toString() {
        return id + " | " + applicantName + " | " + loanAmount;
    }
}
 
// BST Node
class BSTNode {
    LoanApplication app;
    BSTNode left, right;
 
    BSTNode(LoanApplication app) {
        this.app = app;
    }
}
 
// BST for Loan Amount
class LoanBST {
    BSTNode root;
 
    public BSTNode insert(BSTNode node, LoanApplication app) {
        if (node == null)
            return new BSTNode(app);
 
        if (app.loanAmount < node.app.loanAmount)
            node.left = insert(node.left, app);
        else
            node.right = insert(node.right, app);
 
        return node;
    }
 
    public void insert(LoanApplication app) {
        root = insert(root, app);
    }
 
    public void inorder(BSTNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.app);
            inorder(node.right);
        }
    }
 
    public void displaySorted() {
        inorder(root);
    }
}
 
public class LoanEMIPriorityManager {
 
    static PriorityQueue<LoanApplication> queue =
            new PriorityQueue<>((a, b) -> b.priority - a.priority);
 
    static HashMap<String, LoanApplication> map = new HashMap<>();
 
    static LoanBST bst = new LoanBST();
 
    static Scanner sc = new Scanner(System.in);
 
    public static void submitApplication() {
 
        System.out.print("Application ID: ");
        String id = sc.next();
 
        System.out.print("Applicant Name: ");
        String name = sc.next();
 
        System.out.print("Loan Amount: ");
        double amount = sc.nextDouble();
 
        if (amount <= 0) {
            System.out.println("Loan amount must be positive.");
            return;
        }
 
        System.out.print("Priority: ");
        int priority = sc.nextInt();
 
        LoanApplication app =
                new LoanApplication(id, name, amount, priority);
 
        queue.add(app);
        map.put(id, app);
        bst.insert(app);
 
        System.out.println("Application received: " + app);
    }
 
    public static void searchApplication() {
 
        System.out.print("Enter Application ID: ");
        String id = sc.next();
 
        LoanApplication app = map.get(id);
 
        if (app != null) {
            System.out.println("Found: " + app);
        } else {
            System.out.println("Application " + id + " not found.");
        }
    }
 
    public static void processNext() {
 
        if (queue.isEmpty()) {
            System.out.println("No applications available.");
            return;
        }
 
        LoanApplication app = queue.poll();
 
        System.out.println("Processing "
                + app.id + " | " + app.applicantName);
    }
 
    public static void displaySortedLoans() {
 
        System.out.println("\nLoans Sorted by Amount:");
        bst.displaySorted();
    }
 
    public static void main(String[] args) {
 
        while (true) {
 
            System.out.println("\n===== Loan EMI Priority Manager =====");
            System.out.println("1. Submit Application");
            System.out.println("2. Search Application");
            System.out.println("3. Process Next");
            System.out.println("4. Display Sorted Loans");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
 
            int choice = sc.nextInt();
 
            switch (choice) {
 
                case 1:
                    submitApplication();
                    break;
 
                case 2:
                    searchApplication();
                    break;
 
                case 3:
                    processNext();
                    break;
 
                case 4:
                    displaySortedLoans();
                    break;
 
                case 5:
                    System.out.println("Exiting...");
                    return;
 
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}