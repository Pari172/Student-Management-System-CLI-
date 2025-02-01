package main.java.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.model.Student;
import main.java.util.FileUtil;

public class StudentService {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    List<Student> students;

    public StudentService() {
        students = FileUtil.loadStudents(); // Load students from the file(student.txt) when the program starts
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
        FileUtil.saveStudents(students);
    }

    public void ViewAll() {
        if (students.isEmpty()) {
            System.out.println(ANSI_RED + "No Data Found..." + ANSI_RESET);
        } else {
            for (Student student : students) {
                System.out.println(ANSI_GREEN + student.toString() + ANSI_RESET);
            }
        }
    }

    public void SearchStudent() {
        System.out.println("Enter Studnet Id to search: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println(ANSI_GREEN + student.toString() + ANSI_RESET);
                return;
            }
        }
        System.out.println(ANSI_RED + "Invalid Studnet Id !" + ANSI_RESET);
    }

    public void RemoveStudent() {
        System.out.println("Enter Studnet Id to remove: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean isRemoved = students.removeIf(student -> student.getId() == id);
        FileUtil.deleteStudent(id);
        if(isRemoved){
            System.out.println(ANSI_GREEN +"Student Deleted Successfully..." + ANSI_RESET);
        }else{
            System.out.println(ANSI_RED + "Invalid Id..." + ANSI_RESET);
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
                System.out.println(ANSI_YELLOW + "Student updated Successfully...!" + ANSI_RESET);
                FileUtil.updateStudent(id, name, age, uid, grade);
                return;
            }
        }
        System.out.println(ANSI_RED + "Invalid Id..." + ANSI_RESET);
    }

    public void getTotal(){
        System.out.println(ANSI_BLUE + "Total Number of students = " + students.size() + ANSI_RESET);
    }
}
