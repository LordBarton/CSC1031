import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

interface Reader {
    Scanner scanner = new Scanner(System.in);
}

abstract class Document implements Reader {
    public List<String> Doc = new ArrayList<String>();

    public final void docTemplate() {
        createHeader();
        createBody();
        createFooter();
        printDocument();
    }

    protected void createHeader() {
        System.out.print("Enter company name: ");
        String company = scanner.nextLine();

        if (company.isEmpty()) throw new IllegalArgumentException("Error: Company name cannot be empty.");

        System.out.print("Enter date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        if (date.isEmpty()) throw new IllegalArgumentException("Date cannot be empty.");

        
        Doc.add("Company: " + company);
        Doc.add("Date: " + date);
    }

    public abstract void createBody();
    
    protected void createFooter() {
        Doc.add("Prepared by: AutoDoc System");
    }
    
    protected void printDocument() {
        for (String s : Doc) {
            System.out.println(s);
        }
        System.out.println("=========================");

    }
    
}

class Invoice extends Document {
    
    @Override
    public void createBody() {
        
        System.out.print("Enter total amount: ");

        if (!scanner.hasNextDouble()) {
            System.out.println("Error: Total amount must be numeric.");
            System.exit(0);
        }

        double total = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (total <= 0) {
            System.out.println("Error: Total amount must be positive.");
            System.exit(0);
        }

        Doc.add("Total Due: €" + total);
    }

    @Override
    protected void createFooter() {
        super.createFooter();
        Doc.add("Document Type: INVOICE");
    }
    
    @Override
    protected void printDocument() {
        System.out.println("\n=== Printing Document ===");
        System.out.println("=== INVOICE ===");

        super.printDocument();
    }
}

class Report extends Document {
    @Override
    public void createBody() {
        System.out.print("Enter report summary: ");
        String summary = scanner.nextLine();
        
        if (summary.isEmpty()) {
            System.out.println("Warning: Summary is empty.");
        }
        Doc.add("Report Summary: " + summary);
    }

    @Override
    protected void createFooter() {
        Doc.add("Reviewed by: Management Department");
    }
    
    @Override
    protected void printDocument() {
        System.out.println("\n=== Printing Document ===");
        System.out.println("=== REPORT ===");

        super.printDocument();
    }
}

class Receipt extends Document {
    @Override
    public void createBody() {
        System.out.print("Enter amount paid: ");
        double paid = scanner.nextDouble();
        if (paid <= 0) throw new IllegalArgumentException("Error: Amount paid must be positive.");
        

        System.out.print("Enter number of items: ");
        int items = scanner.nextInt();
        if (items <= 0) throw new IllegalArgumentException("Error: Items count must be positive.");

        Doc.add("Total Paid: €" + paid);
        Doc.add("Items Purchased: " + items);
        Doc.add("Price per Item: €" + paid / items);

    }

    @Override
    protected void createFooter() {
        super.createFooter();
        Doc.add("Document Type: RECEIPT");
    }

    @Override
    protected void printDocument() {
        System.out.println("\n=== Printing Document ===");
        System.out.println("=== RECEIPT ===");

        super.printDocument();
    }
}

public class DocumentGenerator implements Reader{
    public static void main(String[] args) {
        try {
            System.out.println("Choose document type: (INV) Invoice, (REP) Report, (REC) Receipt");
            String choice = scanner.nextLine();
            Document document;
            switch (choice) {
                case "INV":
                    document = new Invoice();
                    break;
                case "REP":
                    document = new Report();
                    break;
                case "REC":
                    document = new Receipt();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid choice. Exiting.");
            }
            document.docTemplate();

        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}