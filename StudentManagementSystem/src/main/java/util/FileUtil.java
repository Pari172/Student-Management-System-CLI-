package main.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import main.java.model.Student;

public class FileUtil implements Serializable {
    
    private static final String FILE_PATH = "main/resources/student.txt";
    public static void saveStudents(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(students);
            System.out.println("Student Successfully Added...!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static List<Student> loadStudents() {
    List<Student> students = new ArrayList<>();
    File file = new File(FILE_PATH);

    // Check if the file exists and is not empty
    if (file.exists() && file.length() > 0) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            students = (List<Student>) ois.readObject(); // Load data from the file
            System.out.println("Student data loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("File is empty or does not exist. Returning an empty list.");
    }
    return students;
}


    // Delete a student by ID
    public static void deleteStudent(int id) {
        List<Student> students = loadStudents(); // Load existing students
        boolean removed = students.removeIf(student -> student.getId() == id); // Remove the student with the given ID
        if (removed) {
            saveStudents(students); // Save the updated list
            System.out.println("Student with ID " + id + " deleted successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Update a student by ID
    public static void updateStudent(int id, String newName, int newAge, int newId, String newGrade) {
        List<Student> students = loadStudents(); // Load existing students
        boolean updated = false;
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(newName); 
                student.setAge(newAge);   
                student.setGrade(newGrade);
                student.setId(newId);
                updated = true;
                break;
            }
        }
        if (updated) {
            saveStudents(students); // Save the updated list
            System.out.println("Student with ID " + id + " updated successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }
}
