package main.java;

import main.java.service.StudentService;
import main.java.util.FileUtil;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Get Total Sudent");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    studentService.AddStudent();
                    break;
                case 2:
                    studentService.ViewAll();;
                    break;
                case 3:
                    studentService.SearchStudent();
                    break;
                case 4:
                    studentService.UpdateStudent();
                    break;
                case 5:
                    studentService.RemoveStudent();
                    break;
                case 6:
                    studentService.getTotal();
                    break;
                case 7:
                    running=false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
