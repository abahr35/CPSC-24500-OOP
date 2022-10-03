//Homework 4 week 4
package com.bahra;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BahrReadingWriting02 {
    //process user input
    public static File defaultPath = new File("schoolData.csv");
    public static void main(String[] args) {
        Student instance = new Student();
        Scanner inputHandler = new Scanner(System.in);// create an input handler
        var menuSelection = 0;
        do{
            DBTools.printMenu();//print menu
            try{//check if number inserted is an Int
                menuSelection = inputHandler.nextInt();
            }catch(Exception ex){
                inputHandler.next();//clear the input handler
                menuSelection = 0;
            }
            switch (menuSelection) {
                case 1://Enter Students Name
                    DBTools.clearScreen();
                    System.out.print("Enter Students Name: ");
                    instance.setStudentName(inputHandler.next());//set student name
                    break;
                case 2://Enter Students Academic Year
                    boolean yearValid = false;
                    DBTools.clearScreen();
                    do {
                        System.out.print("Enter Students Academic Year: ");
                        try {//check for freshman and such
                            instance.setStudentYear(inputHandler.next());
                            DBTools.clearScreen();
                            instance.setStudentYear(instance.getStudentYear().toLowerCase()); //set lowercase
                            yearValid = DBTools.validateStudentYear(instance.getStudentYear());
                            if (!yearValid) System.out.println("Please enter Freshman, Sophomore, Junior, or Senior");
                            instance.setStudentYear(instance.getStudentYear().substring(0, 1).toUpperCase() + instance.getStudentYear().substring(1)); //capitalize the first letter for looks
                        } catch (Exception ex) {//if it's not an actual year pull them back
                            System.out.println("Please enter Freshman, Sophomore, Junior, or Senior");
                            inputHandler.next();
                        }
                    } while (!yearValid);
                    break;
                case 3://Enter Students GPA
                    boolean gpaValid = false;
                    DBTools.clearScreen();
                    do {
                        System.out.print("Enter Students GPA: ");
                        try {//check for gpa bounds
                            instance.setStudentGPA(inputHandler.nextDouble());//set gpa
                            DBTools.clearScreen();
                            gpaValid = DBTools.validateStudentGPA(instance.getStudentGPA());//check if valid
                            if (!gpaValid) System.out.println("Please enter a decimal between 0.0 and 4.0!");
                        } catch (Exception ex) {
                            System.out.println("Please enter a decimal between 0.0 and 4.0!");//catch out of bounds and IOException
                            inputHandler.next();
                        }
                    } while (!gpaValid);
                    break;
                case 4://print current student
                    DBTools.clearScreen();//clear screen
                    System.out.println("Printing Current Student!");
                    DBTools.printCompleteMenu(instance); //print complete menu
                    break;
                case 5://Write to file
                    DBTools.clearScreen();
                    boolean isAnswered = DBTools.validateAllAnswered(instance); //check if all inputs are used
                    if (isAnswered){
                        DBTools.writeToFile(instance);//call write file with current class data
                        System.out.println("Writing Data to File");
                    }else
                        System.out.println("Please enter all info into the selections before saving the student data!");
                    break;
                case 6://read from file
                    DBTools.clearScreen();
                    ArrayList<Student> StudentDatabase = DBTools.readFromFile(); //created ArrayList of the file data
                    if (StudentDatabase != null){
                        for (Student student : StudentDatabase) {
                            System.out.println("Students Name: " + student.getStudentName());
                            System.out.println("Students Academic Year: " + student.getStudentYear());
                            System.out.println("Students GPA: " + student.getStudentGPA());
                            System.out.println("Students UID: " + student.getID());
                        }
                    }
                    break;
                case 7:
                    DBTools.clearScreen();
                    System.out.println("Please Enter a Student to search: ");
                    String userQuery = inputHandler.next();//input student name
                    System.out.println("Searching for Student...");
                    ArrayList<Student> QueryResults = DBTools.readFromFile();
                    ArrayList<Student> queryData = new ArrayList<>();
                    if (QueryResults != null) { //Load
                        for (Student queryResult : QueryResults) {//iteration add to the amount of data because it's not stored in one element
                            if (Objects.equals(queryResult.getStudentName(), userQuery)) {
                                queryData.add(queryResult);
                            }
                        }
                        if (queryData.size() > 1) {
                            System.out.println(queryData.size());
                            int selectedStudent = 0;
                            do {
                                System.out.println("Multiple Students Found...");//search for name entered
                                System.out.println("Name, Year, GPA, UID");
                                for (Student queryDataNum : queryData) {
                                    queryDataNum.printStudent(instance);//print student matching Query
                                }
                                boolean uidValid = false;
                                do {//test for matching uid
                                    System.out.println("Please Select a Student based on their UID.");
                                    try {//check for UID
                                        selectedStudent = inputHandler.nextInt();//check for integer
                                        uidValid = DBTools.validateUID(selectedStudent, queryData);//validate uid input
                                        //store student with Uid
                                        instance.setStudentName(queryData.get(selectedStudent).getStudentName());
                                        instance.setStudentYear(queryData.get(selectedStudent).getStudentYear());
                                        instance.setStudentGPA(queryData.get(selectedStudent).getStudentGPA());
                                        System.out.println("Student Loaded!");
                                        if (!uidValid)
                                            System.out.println("Please enter the correct UID listed above");//error message beyond the IOException
                                    } catch (Exception ex) {//if it's not a UID ask again
                                        System.out.println("Please enter the correct UID listed above");
                                        inputHandler.next();//clear buffer
                                    }
                                } while (!uidValid);
                            } while (selectedStudent == 0);
                        } else if(queryData.size() == 1) {
                            System.out.println("Student Loaded!");//single student found
                            instance.setStudentName(queryData.get(0).getStudentName());
                            instance.setStudentYear(queryData.get(0).getStudentYear());
                            instance.setStudentGPA(queryData.get(0).getStudentGPA());
                        } else{
                            System.out.println("No Student Found!");
                        }
                    }
                    break;
                case 8://Exiting
                    System.out.print("Exiting Program!");
                    break;
                default://error handling
                    System.out.println("Please Enter a Selection Between 1 and 8!");
            }
        }while (menuSelection != 9);
    }
}
