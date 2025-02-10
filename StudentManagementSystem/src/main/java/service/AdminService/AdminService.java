package main.java.service.AdminService;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import main.java.model.AdminModel.Admin;
import main.java.util.ColorsUtil.Colors;
import main.java.util.FileUtilJson.AdminFileUtilJson.AdminFileUtilJson;

public class AdminService {
    private List<Admin> admins;

    public AdminService() {
        admins = AdminFileUtilJson.loadAdminJson();
    }

    Scanner sc = new Scanner(System.in);

    private boolean VerifyAdmin(String vc) {
        String VERIF_CODE = "A75JKP";
        return vc.equals(VERIF_CODE);
    }

    private String GenerateUsername(String t, String ph) {
        String s = "";
        if (t.equals("principle")) {
            s += "P";
        } else if (t.equals("teacher")) {
            s += "T";
        }
        return s + ph.substring(9, ph.length());
    }

    private String GeneratePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }

    public void saveAdmin() {
        List<Admin> admins = AdminFileUtilJson.loadAdminJson();
        Admin admin = new Admin();
        boolean loop = true;
        while (loop) {
            System.out.println("Enter name: ");
            String name = sc.nextLine();
            admin.setName(name);
            System.out.println("Enter Phone No: ");
            String ph = sc.nextLine();
            if (!admin.setPhoneNo(ph)) {
                System.out.println(Colors.ANSI_RED + "Please re-enter admin details...!" + Colors.ANSI_RESET);
                continue;
            }
            System.out.println("Enter email Id: ");
            String emailId = sc.nextLine();
            if (!admin.setMailId(emailId)) {
                System.out.println(Colors.ANSI_RED + "Please re-enter admin details...!" + Colors.ANSI_RESET);
                continue;
            }
            System.out.println("Enter admin type: ");
            String type = sc.nextLine();
            if (!admin.setType(type)) {
                System.out.println(Colors.ANSI_RED + "Please re-enter admin details...!" + Colors.ANSI_RESET);
                continue;
            }
            boolean isValid=false;
            for (Admin ad : admins) {
                if (ad.getPhoneNo().equals(ph) || ad.getMailId().equals(emailId)) {
                    System.out.println(Colors.ANSI_RED + "Invalid admin details... !" + Colors.ANSI_RESET);
                    isValid=true;
                    break;
                }
            }
            if(isValid){
                // call menu
            }
            System.out.println("Enter verification code: ");
            String verificationCode = sc.nextLine();
            if (VerifyAdmin(verificationCode)) {
                String username = GenerateUsername(type, ph);
                String password = GeneratePassword();
                admin.setUsername(username);
                admin.setPassword(password);
                admins.add(admin);
                AdminFileUtilJson.saveAdminJson(admins);
                System.out.println(Colors.ANSI_YELLOW + "Username: " + username);
                System.out.println("Password: " + password + Colors.ANSI_RESET);
                loop = false;
            }else{
                System.out.println(Colors.ANSI_RED + "Admin is not authorised to SignUp...! " + Colors.ANSI_RESET);
                // call menu
            }
        }
    }
}
