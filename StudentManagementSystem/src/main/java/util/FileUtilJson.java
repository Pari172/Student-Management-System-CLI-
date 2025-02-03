package main.java.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import main.java.model.Student;
import main.java.util.Colors;

public class FileUtilJson {
    private static final String FILE_PATH = "main/resources/StudentData.json";
    
    private static final void CreateNewJsonFile(){
        System.out.println(Colors.ANSI_RED + "StudentData.json file does not exist...!" + Colors.ANSI_RESET);
        try {
                File file = new File(FILE_PATH);
                if (file.createNewFile()) {
                    System.out.println(Colors.ANSI_PURPLE + "Creating New File..." + Colors.ANSI_RESET);
                    System.out.println(Colors.ANSI_PURPLE + "New File Created: " + file.getPath() + Colors.ANSI_RESET);
                    System.out.println(Colors.ANSI_PURPLE + "File is empty! please add student by pressing 1" + Colors.ANSI_RESET);
                    try(FileWriter writer = new FileWriter(FILE_PATH)){
                        writer.write("[]");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public static void saveStudentsJson(List<Student> students) {
        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(students);
            String encryptedData = CryptoUtil.Encrypt(jsonString);
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(encryptedData);
            writer.close();
            System.out.println(Colors.ANSI_GREEN + "Data saved successfully in encrypted format." + Colors.ANSI_RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Student> loadStudentsJson() {
        List<Student> students = new ArrayList<>();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Student>>() {
        }.getType();
        File file = new File(FILE_PATH);
        if (file.isFile()) {
            try (FileReader reader = new FileReader(FILE_PATH)) {
                // students = gson.fromJson(reader, listType);
                // if(students==null){
                //     students = new ArrayList<>();
                // }
                // System.out.println(Colors.ANSI_YELLOW + "Data loaded from json file..." + Colors.ANSI_RESET);
                String encryptedData = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
                String decryptedJsonData = CryptoUtil.Decrypt(encryptedData);
                students =  gson.fromJson(decryptedJsonData, listType);
                System.out.println(Colors.ANSI_YELLOW + "Data loaded from json file in decrypted format..." + Colors.ANSI_RESET);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            CreateNewJsonFile();
        }
        return students;
    }

    public static void deleteStudentFromJsonFile(int id) {
        List<Student> students = loadStudentsJson();
        boolean removed = students.removeIf(student -> student.getId() == id);
        if (removed) {
            saveStudentsJson(students);
            System.out.println(Colors.ANSI_GREEN + "Student deleted from json file..." + Colors.ANSI_RESET);
        } else {
            System.out.println(Colors.ANSI_RED + "Invalid student id...!" + Colors.ANSI_RESET);
        }
    }

    public static void updateStudentFromJsonFile(int id, String newName, int newAge, int newId, String newGrade) {
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
            System.out.println(Colors.ANSI_CYAN + "Student with ID " + id + " updated successfully in json file!"+ Colors.ANSI_RESET);
        } else {
            System.out.println(Colors.ANSI_RED + "Student with ID " + id + " not found." + Colors.ANSI_RESET);
        }
    }
}
