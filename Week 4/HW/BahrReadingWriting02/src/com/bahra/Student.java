package com.bahra;

public class Student {
    private int ID;
    private String studentName;
    private String studentYear;
    private Double studentGPA;
    public Student() { //overload for default values
        this(" ", " ", -1.0, 0);
    }
    public Student(String studentName, String studentYear, Double studentGPA) {
        this.studentName = studentName;
        this.studentYear = studentYear;
        this.studentGPA = studentGPA;
    }
    public Student(String studentName, String studentYear, Double studentGPA, int ID ) {
        this.studentName = studentName;
        this.studentYear = studentYear;
        this.studentGPA = studentGPA;
        this.ID = ID;
    }
    public Double getStudentGPA() {
        return studentGPA;
    }
    public void setStudentGPA(Double studentGPA) {
        this.studentGPA = studentGPA;
    }
    public String getStudentYear() {
        return studentYear;
    }
    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public int getID() {
        return ID;
    }
    public void printStudent(Student instance){
        System.out.println(getStudentName() + " " + getStudentYear() + " " + getStudentGPA() + " " + getID());
    }
}
