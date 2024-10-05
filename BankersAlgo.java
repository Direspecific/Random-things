import java.util.Scanner;

public class BankersAlgorithm {
    private static int processes, resources = 3;
    private static int[] available = new int[resources]; // Work
    private static int[][] max;     // Maximum demand of each process
    private static int[][] allocation; // Allocation matrix
    private static int[][] need;    // Need matrix (Max - Allocation)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean tryAgain = true; // Initialize tryAgain to true
        System.out.println("Banker's Algorithhm");
        do {
            System.out.print("Enter the number of processes (4 to 6): ");
            processes = scanner.nextInt();

            if (processes < 4 || processes > 6) {
                System.out.println("Invalid number of processes. Try again.");
                continue;
            }

            max = new int[processes][resources];
            allocation = new int[processes][resources];
            need = new int[processes][resources];

            // Input available resources
            System.out.println("Enter available instances of resources A, B, C: ");
            for (int i = 0; i < resources; i++) {
                available[i] = scanner.nextInt();
            }

            // Input max matrix and allocation matrix
            System.out.println("Enter maximum demand of resources for each process:");
            for (int i = 0; i < processes; i++) {
                System.out.println("For Process " + i + " (A B C): ");
                for (int j = 0; j < resources; j++) {
                    max[i][j] = scanner.nextInt();
                }
            }

            System.out.println("Enter allocation of resources for each process:");
            for (int i = 0; i < processes; i++) {
                System.out.println("For Process " + i + " (A B C): ");
                for (int j = 0; j < resources; j++) {
                    allocation[i][j] = scanner.nextInt();
                    need[i][j] = max[i][j] - allocation[i][j]; // Calculate Need
                }
            }

            // Display the matrices and safe sequence
            displayMatrices();
            if (isSafeSequence()) {
                System.out.println("System is in a safe state.");
            } else {
                System.out.println("System is not in a safe state.");
            }

            // Ask if the user wants to try again
            System.out.print("Do you want to try again? (yes/no): ");
            String answer = scanner.next();
            tryAgain = answer.equalsIgnoreCase("yes");

        } while (tryAgain);

        scanner.close();
    }

    // Function to display Allocation, Need, and Available matrices
    public static void displayMatrices() {
        System.out.println("\nAllocation Matrix:");
        System.out.println("Process\tA\tB\tC");
        for (int i = 0; i < processes; i++) {
            System.out.print("P" + i + "\t");
            for (int j = 0; j < resources; j++) {
                System.out.print(allocation[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nMax Need Matrix:");
        System.out.println("Process\tA\tB\tC");
        for (int i = 0; i < processes; i++) {
            System.out.print("P" + i + "\t");
            for (int j = 0; j < resources; j++) {
                System.out.print(max[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nNeed Matrix:");
        System.out.println("Process\tA\tB\tC");
        for (int i = 0; i < processes; i++) {
            System.out.print("P" + i + "\t");
            for (int j = 0; j < resources; j++) {
                System.out.print(need[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nAvailable Resources (Work):");
        System.out.print("A\tB\tC\n");
        for (int i = 0; i < resources; i++) {
            System.out.print(available[i] + "\t");
        }
        System.out.println("\n");
    }

    // Function to find safe sequence and display remaining available resources
    public static boolean isSafeSequence() {
        int[] work = available.clone(); // Work array (available resources)
        boolean[] finish = new boolean[processes]; // Finish array
        int[] safeSequence = new int[processes]; // Safe sequence array
        int count = 0;

        while (count < processes) {
            boolean found = false;
            for (int i = 0; i < processes; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < resources; j++) {
                        if (need[i][j] > work[j]) {
                            break;
                        }
                    }

                    if (j == resources) { // If all need <= work
                        for (int k = 0; k < resources; k++) {
                            work[k] += allocation[i][k]; // New Work = work + allocation
                        }
                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;

                        // Print remaining available after allocation
                        System.out.println("Process P" + i + " is executing.");
                        System.out.println("Remaining Available Resources after P" + i + ":");
                        System.out.print("A\tB\tC\n");
                        for (int k = 0; k < resources; k++) {
                            System.out.print(work[k] + "\t");
                        }
                        System.out.println("\n");
                    }
                }
            }

            if (!found) {
                System.out.println("The system is not in a safe state.");
                return false;
            }
        }

        // Print safe sequence
        System.out.println("The system is in a safe state.\nSafe sequence is: ");
        for (int i = 0; i < processes; i++) {
            System.out.print("P" + safeSequence[i] + " ");
        }
        System.out.println();
        return true;
    }
}
