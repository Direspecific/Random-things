import java.util.Scanner;

public class App {
    private int idNumber;
    private String firstName;
    private String lastName;
    private String studentType;
    private String courseName;

    public App(int idNumber, String firstName, String lastName, String studentType, String courseName) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentType = studentType;
        this.courseName = courseName;
    }

    private double[] calculateDiscount(double tuitionFee, double miscellaneousFee) {
        if (studentType.equals("Walk-in")) {
            return new double[] {0, 0};
        } else if (studentType.equals("Continuing")) {
            return new double[] {0.1 * tuitionFee, 0};
        } else if (studentType.equals("Discounted (Gold)")) {
            double discountTuition = 0.5 * tuitionFee;
            double discountMiscellaneous = 0.5 * miscellaneousFee;
            return new double[] {discountTuition, discountMiscellaneous};
        } else if (studentType.equals("Discounted (Blue)")) {
            double discountTuition = 0.3 * tuitionFee;
            double discountMiscellaneous = 0.3 * miscellaneousFee;
            return new double[] {discountTuition, discountMiscellaneous};
        } else if (studentType.equals("Discounted (White)")) {
            double discountTuition = 0.1 * tuitionFee;
            double discountMiscellaneous = 0.1 * miscellaneousFee;
            return new double[] {discountTuition, discountMiscellaneous};
        } else {
            throw new IllegalArgumentException("Invalid student type");
        }
    }

    public double calculateTotalFees() {
        double tuitionFee;
        double miscellaneousFee;

        switch (courseName) {
            case "Tourism":
                tuitionFee = 10000;
                miscellaneousFee = 22500;
                break;
            case "Computer Science":
                tuitionFee = 20000;
                miscellaneousFee = 11000;
                break;
            case "Engineering":
                tuitionFee = 30000;
                miscellaneousFee = 25000;
                break;
            case "Nursing":
                tuitionFee = 15000;
                miscellaneousFee = 15000;
                break;
            case "Architecture":
                tuitionFee = 25000;
                miscellaneousFee = 23000;
                break;
            default:
                throw new IllegalArgumentException("Invalid course name");
        }

        double[] discount = calculateDiscount(tuitionFee, miscellaneousFee);
        double discountTuition = discount[0];
        double discountMiscellaneous = discount[1];
        double totalTuition = tuitionFee - discountTuition;
        double totalMiscellaneous = miscellaneousFee - discountMiscellaneous;
        return totalTuition + totalMiscellaneous;
    }

    public void printDetails() {
        System.out.println("ID Number: " + idNumber);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Student Type: " + studentType);
        System.out.println("Course: " + courseName);
     }
    public static void main(String[] args) {
        Scanner intScanner = new Scanner(System.in);

        System.out.print("Enter student ID number: ");
        int idNumber = intScanner.nextInt();

        Scanner stringScanner = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = stringScanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = stringScanner.nextLine();

        System.out.print("Enter student type (Discounted (Gold, Blue, White), Walk-in, Continuing): ");
        String studentType = stringScanner.nextLine();

        System.out.print("Enter course name (Tourism, Computer Science, Engineering, Nurisng, Architecture): ");
        String courseName = stringScanner.nextLine();

        App student = new App(idNumber, firstName, lastName, studentType, courseName);
        double totalFees = student.calculateTotalFees();
        
        System.out.println("");
        student.printDetails();
        System.out.println("");
        System.out.printf("Total fees for %s course: %.2f%n", courseName, totalFees);
    }
    
}
