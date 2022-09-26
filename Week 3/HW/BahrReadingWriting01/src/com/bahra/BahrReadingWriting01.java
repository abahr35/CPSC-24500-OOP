//Austin Bahr Homework 3 week 3
package com.bahra;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BahrReadingWriting01 {
    private String studentName;//default = ""
    private String studentYear; // = ""
    private Double studentGPA; // = -1.0
    public File defaultPath = new File("schoolData.csv");
    public BahrReadingWriting01(String studentName, String studentYear, Double studentGPA){
        //constructor to set default values on initialization
        this.setStudentName(studentName);
        this.setStudentYear(studentYear);
        this.setStudentGPA(studentGPA);
    }
    /*
    Below is Getters and Setters for the private data
    I didn't need to do it this way, but I implemented it early and never changed it
     */
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentYear() {
        return studentYear;
    }
    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }
    public Double getStudentGPA() {
        return studentGPA;
    }
    public void setStudentGPA(Double studentGPA) {
        this.studentGPA = studentGPA;
    }
    public void clearScreen(){
        System.out.print("\033[H\033[2J"); //move cursor and clear screen from cursor to end of the screen
        System.out.flush(); //reset cursor
    }
    public void printMenu() {
        //print menu
        System.out.println("1. Enter Students Name");
        System.out.println("2. Enter Students Academic Year");
        System.out.println("3. Enter Students GPA");
        System.out.println("4. Display Students Info");
        System.out.println("5. Write Data to File");
        System.out.println("6. Read Data from File");
        System.out.println("7. Exit");
        System.out.print("Please enter which number you want to answer: ");
    }
    public void printCompleteMenu() {
        //print extra menu
        clearScreen();
        System.out.println("Students Name: " + getStudentName());
        System.out.println("Students Academic Year: " + getStudentYear());
        if (getStudentGPA() == -1.0) System.out.println(" ");
        else System.out.println("Students GPA: " + getStudentGPA());
    }
    public boolean validateAllAnswered(){
        //check if it's still default values
        return !getStudentName().equals("") && !getStudentYear().equals("") && getStudentGPA() != -1.0;
    }
    public boolean validateStudentGPA(Double inputGPA){
        //are u in the correct specs?
        return inputGPA >= 0.0 && inputGPA <= 4.0;
    }
    public boolean validateStudentYear(String inputYear){
        /*
        I chose not to cancel their input if the case was wrong because I wanted the program to flow better.
        so I ended up making the text lowercase then checking
        */
        return inputYear.equals("freshman") || inputYear.equals("sophomore") || inputYear.equals("junior") || inputYear.equals("senior");// return true if matches
    }
    public void writeToFile(){
        String csvData = getStudentName() + "," + getStudentYear() + "," + getStudentGPA().toString() + "\n"; //Gather data for writing
        try {
            if(defaultPath.exists()){//Checks for existing file
                System.out.println("File Exists! Appending the file...");//checks for default file
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
    public ArrayList<String> readFromFile(File path) {
        ArrayList<String> StudentDatabase = new ArrayList<>();//new arraylist to store data
        try{
            Scanner databaseReader = new Scanner(path); //scanner to read data
            while (databaseReader.hasNextLine()){ //if a next line exists loop
                String[] currentLine = databaseReader.nextLine().split(","); //split the string by the commas (CSV)
                StudentDatabase.add(currentLine[0]);//name
                StudentDatabase.add(currentLine[1]);//year
                StudentDatabase.add(currentLine[2]);//gpa (in string form)
            }
            System.out.println("Loaded Data Successfully");
        }catch(Exception e){
            System.out.println("Filepath Not Found!");
            return null;//return no data if file not found
        }
        return StudentDatabase;
    }
    //process user input
    public static void main(String[] args) {
        BahrReadingWriting01 instance = new BahrReadingWriting01("", "", -1.0);// create an instance of the class
        Scanner inputHandler = new Scanner(System.in);// create an input handler
        var menuSelection = 0;
        do{
            instance.printMenu();//print menu
            try{//check if number inserted is an Int
                menuSelection = inputHandler.nextInt();
            }catch(Exception ex){
                inputHandler.next();//clear the input handler
                menuSelection = 0;
            }
            switch (menuSelection) {
                case 1://Enter Students Name
                    instance.clearScreen();
                    System.out.print("Enter Students Name: ");
                    instance.setStudentName(inputHandler.next());//set student name
                    break;
                case 2://Enter Students Academic Year
                    boolean yearValid = false;
                    instance.clearScreen();
                    do {
                        System.out.print("Enter Students Academic Year: ");
                        try {//check for freshman and such
                            instance.setStudentYear(inputHandler.next());
                            instance.clearScreen();
                            instance.setStudentYear(instance.getStudentYear().toLowerCase()); //set lowercase
                            yearValid = instance.validateStudentYear(instance.getStudentYear());
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
                    instance.clearScreen();
                    do {
                        System.out.print("Enter Students GPA: ");
                        try {//check for gpa bounds
                            instance.setStudentGPA(inputHandler.nextDouble());//set gpa
                            instance.clearScreen();
                            gpaValid = instance.validateStudentGPA(instance.getStudentGPA());//check if valid
                            if (!gpaValid) System.out.println("Please enter a decimal between 0.0 and 4.0!");
                        } catch (Exception ex) {
                            System.out.println("Please enter a decimal between 0.0 and 4.0!");//catch out of bounds and IOException
                            inputHandler.next();
                        }
                    } while (!gpaValid);
                    break;
                case 4://print current student
                    instance.clearScreen();//clear screen
                    System.out.println("Printing Current Student!");
                    instance.printCompleteMenu(); //print complete menu
                    break;
                case 5://Write to file
                    instance.clearScreen();
                    boolean isAnswered = instance.validateAllAnswered(); //check if all inputs are used
                    if (isAnswered){
                        instance.writeToFile();//call write file with current class data
                        System.out.println("Writing Data to File");
                    }else
                        System.out.println("Please enter all info into the selections before saving the student data!");
                    break;
                case 6://read from file
                    instance.clearScreen();
                    System.out.println("Enter an Existing filepath to read data");
                    System.out.println("The default file is schoolData.csv");
                    String userPath = inputHandler.next();//user input for file path
                    File path = new File(userPath);
                    ArrayList<String> StudentDatabase = instance.readFromFile(path); //created ArrayList of the file data
                    if (StudentDatabase != null){   //initially I wanted to use instances of the class in the list, so I could load data and keep it.but I got stuck many times
                        for (int i = 0; i < StudentDatabase.size(); i+=3) {//iteration add to the amount of data because it's not stored in one element
                            System.out.println("Students Name: " + StudentDatabase.get(i));//I wanted to use a tuple [String, String, Double] but java doesn't have native tuples
                            System.out.println("Students Academic Year: " + StudentDatabase.get(i+1));
                            System.out.println("Students GPA: " + StudentDatabase.get(i+2));
                        }
                    }
                    break;
                case 7://Exiting
                    System.out.print("Exiting Program!");
                    break;
                default://error handling
                    System.out.println("Please Enter a Selection Between 1 and 7!");
            }
        }while (menuSelection != 7);
    }
}
