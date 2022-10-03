package com.bahra;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBTools {
    public static File defaultPath = new File("schoolData.csv");
    public static void clearScreen(){
        System.out.print("\033[H\033[2J"); //move cursor and clear screen from cursor to end of the screen
        System.out.flush(); //reset cursor
    }
    public static void printMenu() {
        //print menu
        System.out.println("1. Enter Students Name");
        System.out.println("2. Enter Students Academic Year");
        System.out.println("3. Enter Students GPA");
        System.out.println("4. Print Current Working Student");
        System.out.println("5. Write Data to File");
        System.out.println("6. Read Data from File");
        System.out.println("7. Search by Student Name");
        System.out.println("8. Exit");
        System.out.print("Please enter which number you want to answer: ");
    }
    public static void printCompleteMenu(Student instance) {
        //print extra menu
        clearScreen();
        System.out.println("Students Name: " + instance.getStudentName());
        System.out.println("Students Academic Year: " + instance.getStudentYear());
        if (instance.getStudentGPA() == -1.0) System.out.println("Students GPA: ");
        else System.out.println("Students GPA: " + instance.getStudentGPA());
    }
    public static boolean validateAllAnswered(Student instance){
        //check if it's still default values
        return !instance.getStudentName().equals("") && !instance.getStudentYear().equals("") && instance.getStudentGPA() != -1.0;
    }
    public static boolean validateStudentGPA(Double inputGPA){
        //are u in the correct specs?
        return inputGPA >= 0.0 && inputGPA <= 4.0;
    }
    public static boolean validateStudentYear(String inputYear){
        return inputYear.equals("freshman") || inputYear.equals("sophomore") || inputYear.equals("junior") || inputYear.equals("senior");// return true if matches
    }
    public static int checkLine() throws IOException {//used for UID
        BufferedReader reader = new BufferedReader(new FileReader(defaultPath));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;//return line count for duplicate students
    }
    public static void writeToFile(Student instance){
        String csvData = "\n" + instance.getStudentName() + "," + instance.getStudentYear() + "," + instance.getStudentGPA().toString(); //Gather data for writing
        try {
            if(defaultPath.exists()){//Checks for existing file
                System.out.println("File Exists! Appending the file...");//checks for default file
                csvData += "," + checkLine();
            }else {
                System.out.println("File Not Found! Creating the file...");//creates if not found
            }
            FileWriter fileWriter = new FileWriter(defaultPath, true);//append is true
            fileWriter.write(csvData);//append the data at the path
            fileWriter.flush();//clear
            fileWriter.close();//close file

        } catch (Exception e) {
            System.out.println("File Incorrect!");
        }
    }
    public static ArrayList<Student> readFromFile() {
        ArrayList<Student> StudentDatabase = new ArrayList<>();//new arraylist to store data
        try{
            Scanner databaseReader = new Scanner(defaultPath); //scanner to read data
            while (databaseReader.hasNextLine()){ //if a next line exists loop
                String[] currentLine = databaseReader.nextLine().split(",");//split the string by the commas (CSV)
                StudentDatabase.add(new Student(currentLine[0], currentLine[1], Double.parseDouble(currentLine[2]), Integer.parseInt(currentLine[3])));//name
            }
            databaseReader.close();
        }catch(FileNotFoundException e){
            System.out.println("Filepath Not Found!");
            return null;//return no data if file not found
        }
        return StudentDatabase;
    }
    public static boolean validateUID(int inputUID, ArrayList<Student> instance){
        for (Student student : instance) {
            if (inputUID == student.getID()) {//if input = ID then true
                return true;
            }
        }
        return false;
    }
}
