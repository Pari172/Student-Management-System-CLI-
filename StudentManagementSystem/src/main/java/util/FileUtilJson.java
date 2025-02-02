package main.java.util;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import main.java.model.Student;
public class FileUtilJson {
    private static final String FILE_PATH = "main/resources/StudentData.json";

    public static void saveStudentsJson(List<Student> students){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            gson.toJson(students,writer);
            System.out.println("Student Successfully saved in json file...!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Student> loadStudentsJson(){
        List<Student> students = new ArrayList<>();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Student>>() {}.getType();
        File file = new File(FILE_PATH);
        if(file.exists() && file.length()>0){
            try (FileReader reader = new FileReader(FILE_PATH)){
                students = gson.fromJson(reader, listType);
                System.out.println("Data loaded from json file...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("StudentData.json file does not exist...!");
        }
        return students;
    }

    public static void deleteStudentFromJsonFile(int id){
        List<Student> students = loadStudentsJson();
        boolean removed  = students.removeIf(student->student.getId()==id);
        if(removed){
            saveStudentsJson(students);
            System.out.println("Student deleted from json file...");
        }else{
            System.out.println("Invalid student id...!");
        }
    }

    public static void updateStudentFromJsonFile(int id, String newName, int newAge, int newId, String newGrade){
        List<Student> students = loadStudentsJson();
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
            saveStudentsJson(students); // Save the updated list json file
            System.out.println( "Student with ID " + id + " updated successfully in json file!" );
        } else {
            System.out.println( "Student with ID " + id + " not found." );
        }
    }
}
