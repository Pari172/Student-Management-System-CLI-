package main.java.model.AdminModel;

import java.util.regex.Pattern;

import main.java.util.ColorsUtil.Colors;

public class Admin {
    private String name;
    private String phno;
    private String mailId;
    private String type;
    private String username;
    private String password;

    // Constructor

    // Getters
    public String getName(){
        return name;
    }
    public String getPhoneNo(){
        return phno;
    }
    public String getMailId(){
        return mailId;
    }
    public String getType(){
        return type;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    // Setters
    public void setName(String n){
        this.name = n;
    }
    public boolean setPhoneNo(String n){
        String pattern = "^\\+[1-9]{2}[0-9]{10}$";
        if(Pattern.matches(pattern, n)){
            this.phno = n;
            return true;
        }
        System.out.println(Colors.ANSI_RED + "Phone no. is invalid ...!" + Colors.ANSI_RESET);
        return false;
    }
    public boolean setMailId(String m){
        String pattern = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";
        if(Pattern.matches(pattern, m)){
            this.mailId = m;
            return true;
        }
        System.out.println(Colors.ANSI_RED + "Email is invalid... !" + Colors.ANSI_RESET);
        return false;
    }
    public boolean setType(String t){
        String pattern = "^(principle|teacher)$";
        if(Pattern.matches(pattern, t)){
            this.type = t;
            return true;
        }
        System.out.println(Colors.ANSI_RED + "Type must be either principle or teacher" + Colors.ANSI_RESET);
        return false;
    }
    public void setUsername(String u){
        this.username = u;
    }
    public void setPassword(String p){
        this.password = p;
    }

}
