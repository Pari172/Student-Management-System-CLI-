package main.java.model;

import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private String name;
    private int age;
    private String grade;

    public Student(int id,String name,int age,String grade){
        setAge(age);
        setGrade(grade);
        setId(id);
        setName(name);
    }

    public int getAge() {
        return age;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getGrade() {
        return grade;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "[Name: " + name + ", Id: " + id + ", age: " + age + ", grade: " + grade + "]";
    }
}