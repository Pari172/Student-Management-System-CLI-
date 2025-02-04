package main.java.service.StudentService;

import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import main.java.model.StudentModel.Student;
import main.java.util.ColorsUtil.Colors;
import main.java.util.FileUtilJson.FileUtilJson;

public class StudentService {

    List<Student> students;

    public StudentService() {
        // students = FileUtil.loadStudents(); // Load students from the
        // file(student.txt) when the program starts
        students = FileUtilJson.loadStudentsJson(); // Load students from file(StudentData.json) when program starts
    }

    Scanner sc = new Scanner(System.in);

    public void AddStudent() {
        System.out.println("Enter Student Id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Student Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Student Grade: ");
        String grade = sc.nextLine();
        Student s = new Student(id, name, age, grade);

        students.add(s);
        // FileUtil.saveStudents(students);
        FileUtilJson.saveStudentsJson(students);
    }

    public void ViewAll() {
        if (students.isEmpty()) {
            System.out.println(Colors.ANSI_RED + "No Data Found..." + Colors.ANSI_RESET);
        } else {
            Collections.sort(students, new Comparator<Student>() {
                public int compare(Student a, Student b) {
                    return Integer.compare(a.getId(), b.getId());
                }
            });
            for (Student student : students) {

                System.out.println(Colors.ANSI_GREEN + student.toString() + Colors.ANSI_RESET);
            }
        }
    }

    // serch by id
    public void SearchById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {

                System.out.println(Colors.ANSI_GREEN + student.toString() + Colors.ANSI_RESET);
            }
        }
    }

    // serach by name
    public void SearchByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {

                System.out.println(Colors.ANSI_GREEN + student.toString() + Colors.ANSI_RESET);
            }
        }
    }

    // search by age
    public void SearchByAge(int age) {
        for (Student student : students) {
            if (student.getAge() == age) {
                System.out.println(Colors.ANSI_GREEN + student.toString() + Colors.ANSI_RESET);
            }
        }
    }

    public void SearchStudent() {
        System.out.println("Press 1 to search by Id: ");
        System.out.println("Press 2 to search by name: ");
        System.out.println("Press 3 to search by age: ");
        System.out.println("Enter your choice: ");
        try {
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter id: ");
                    int Id = sc.nextInt();
                    sc.nextLine();
                    SearchById(Id);
                    break;
                case 2:
                    System.out.println("Enter Name: ");
                    String name = sc.nextLine();
                    SearchByName(name);
                    break;
                case 3:
                    System.out.println("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    SearchByAge(age);
                    break;
                default:
                    System.out.println(Colors.ANSI_RED + "Invalid Input !" + Colors.ANSI_RESET);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println(Colors.ANSI_RED + "Input must be a number...!" + Colors.ANSI_RESET);
            sc.nextLine();
        }
    }

    public void RemoveStudent() {
        System.out.println("Enter Studnet Id to remove: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean isRemoved = students.removeIf(student -> student.getId() == id);
        // FileUtil.deleteStudent(id);
        FileUtilJson.deleteStudentFromJsonFile(id);
        if (isRemoved) {
            System.out.println(Colors.ANSI_GREEN + "Student Deleted Successfully..." + Colors.ANSI_RESET);
        } else {
            System.out.println(Colors.ANSI_RED + "Invalid Id..." + Colors.ANSI_RESET);
        }

        // following code is giving ConcurrentModificationException

        // for (Student student : students) {
        // if (student.getId() == id) {
        // String s = student.toString();
        // students.remove(student);
        // // students.removeIf(student -> student.getId() == id); // if you dont want
        // to not
        // // use for loop
        // System.out.println("Student Deleted Successfully...");
        // FileUtil.deleteStudent(id);
        // }
        // }
    }

    public void UpdateStudent() {
        System.out.println("Enter Studnet Id to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Enter updated Student Id: ");
                int uid = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter updated Student Name: ");
                String name = sc.nextLine();
                System.out.println("Enter updated Student Age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter updated Student Grade: ");
                String grade = sc.nextLine();
                student.setAge(age);
                student.setId(uid);
                student.setName(name);
                student.setGrade(grade);
                System.out.println(Colors.ANSI_YELLOW + "Student updated Successfully...!" + Colors.ANSI_RESET);
                // FileUtil.updateStudent(id, name, age, uid, grade);
                FileUtilJson.updateStudentFromJsonFile(id, name, age, uid, grade);
                return;
            }
        }
        System.out.println(Colors.ANSI_RED + "Invalid Id..." + Colors.ANSI_RESET);
    }

    public void getTotal() {
        System.out.println(Colors.ANSI_BLUE + "Total Number of students = " + students.size() + Colors.ANSI_RESET);
    }
}
