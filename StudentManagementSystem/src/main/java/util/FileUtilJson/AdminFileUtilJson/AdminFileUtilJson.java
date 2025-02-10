package main.java.util.FileUtilJson.AdminFileUtilJson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import main.java.model.AdminModel.Admin;
import main.java.model.StudentModel.Student;
import main.java.util.ColorsUtil.Colors;
import main.java.util.CryptoUtil.CryptoUtil;

public class AdminFileUtilJson {
    private static final String FILE_PATH = "main/resources/AdminData.json";

    private static final void CreateNewJsonFile(){
        System.out.println(Colors.ANSI_RED + "AdminData.json file does not exist...!" + Colors.ANSI_RESET);
        try {
                File file = new File(FILE_PATH);
                if (file.createNewFile()) {
                    System.out.println(Colors.ANSI_PURPLE + "Creating New File..." + Colors.ANSI_RESET);
                    System.out.println(Colors.ANSI_PURPLE + "New File Created: " + file.getPath() + Colors.ANSI_RESET);
                    System.out.println(Colors.ANSI_PURPLE + "File is empty! please add admin by pressing 1" + Colors.ANSI_RESET);
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


    public static void saveAdminJson(List<Admin> admins){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(admins);
            String encryptedString = CryptoUtil.Encrypt(jsonString);
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(encryptedString);
            writer.close();
            System.out.println(Colors.ANSI_GREEN + "Admin data saved successfully in encrypted format." + Colors.ANSI_RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Admin> loadAdminJson(){
        List<Admin> admins = new ArrayList<>();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Student>>() {
        }.getType();
        File file = new File(FILE_PATH);
        if(file.isFile()){
            try {
                FileReader reader = new FileReader(FILE_PATH);
                String encryptedData = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
                String decryptedData = CryptoUtil.Decrypt(encryptedData);
                admins = gson.fromJson(decryptedData,listType);
                System.out.println(Colors.ANSI_YELLOW + "Admin data loaded from json file in decrypted format..." + Colors.ANSI_RESET);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            CreateNewJsonFile();
        }
        return admins;
    }

    public static void deleteAdminFromJson(String username,String password){
        List<Admin> admins = loadAdminJson();
        boolean isDeleted = admins.removeIf(admin->admin.getUsername().equals(username) && admin.getPassword().equals(password));
        if(isDeleted){
            saveAdminJson(admins);
            System.out.println(Colors.ANSI_GREEN + "Admin deleted from json file..." + Colors.ANSI_RESET);
        }else{
            System.out.println(Colors.ANSI_RED + "Invalid Admin Credentials...!" + Colors.ANSI_RESET);
        }
    }

    
}
