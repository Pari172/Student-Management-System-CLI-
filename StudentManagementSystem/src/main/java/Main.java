package main.java;


import main.java.service.StudentService;
import main.java.util.Colors;
import main.java.model.Student;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        Scanner scanner = new Scanner(System.in);
        
        boolean running = true;
        // This commented code is added to check our GSON package is working file or not
        // Student s = new Student(1003, "Pankaj", 45, "D");
        // Gson gson = new Gson();
        // String j = gson.toJson(s);
        // System.out.println("Using GSON: " + j);
        while (running) {
            System.out.println(Colors.ANSI_RESET + "\nStudent Management System");
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
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
// compile -> javac -cp ".:main/lib/gson-2.12.1.jar" main/java/Main.java
// Run     -> java -cp ".:main/lib/gson-2.12.1.jar" main/java/Main
// git log -> after checking commit history press q to return to terminal

// Sample data 
// [
//   {
//     "id": 1001,
//     "name": "Parikshit Nandkishor Gaikwad",
//     "age": 23,
//     "grade": "A+"
//   },
//   {
//     "id": 1002,
//     "name": "Kartik Pandhurang Kale",
//     "age": 20,
//     "grade": "C"
//   },
//   {
//     "id": 1004,
//     "name": "Hitesh Choudhary",
//     "age": 38,
//     "grade": "A+"
//   },
//   {
//     "id": 1005,
//     "name": "Jethalal Champaklal Gada",
//     "age": 45,
//     "grade": "B+"
//   },
//   {
//     "id": 1006,
//     "name": "Atmaram Bhide",
//     "age": 46,
//     "grade": "A+"
//   }
// ]