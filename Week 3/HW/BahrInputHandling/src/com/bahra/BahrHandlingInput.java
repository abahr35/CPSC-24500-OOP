//Austin Bahr Homework 2
package com.bahra;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/*todo

    -update 4 to read current student data
    -write data function !!!double check
    -read  data function
    -user specify path for read function
    -rename file to BahrReadingWriting01
    -reset default student data
    -spruce up comments



 */
public class BahrHandlingInput{

    private String studentName;// "AB" default = ""
    private String studentYear; //"Freshman"; = ""
    private Double studentGPA; //3.0; = -1.0

    public File defaultPath = new File("schoolData.csv");

    public BahrHandlingInput(String studentName, String studentYear, Double studentGPA){
        this.setStudentName(studentName);
        this.setStudentYear(studentYear);
        this.setStudentGPA(studentGPA);
    }
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
        System.out.println("Students GPA: " + getStudentGPA());
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
        inputYear = inputYear.toLowerCase();//make input lowercase
        return inputYear.equals("freshman") || inputYear.equals("sophomore") || inputYear.equals("junior") || inputYear.equals("senior");// return true if matches
    }
    public void writeToFile(){
        String csvData = getStudentName() + "," + getStudentYear() + "," + getStudentGPA().toString() + "\n"; //Gather data for writing
        try {
            if(defaultPath.exists()){//Checks for existing file
                System.out.println("File Exists! Appending the file...");
            }else {
                System.out.println("File Not Found! Creating the file...");
            }

            FileWriter fileWriter = new FileWriter(defaultPath, true);
            fileWriter.write(csvData);
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("File Incorrect!");
        }
    }
    public ArrayList<String> readFromFile(File path) {
        ArrayList<String> StudentDatabase = new ArrayList<>();
        try{
            Scanner databaseReader = new Scanner(path);
            while (databaseReader.hasNextLine()){
                String[] currentLine = databaseReader.nextLine().split(",");
                StudentDatabase.add(currentLine[0]);
                StudentDatabase.add(currentLine[1]);
                StudentDatabase.add(currentLine[2]);
            }
            System.out.println("Loaded Data Successfully");
        }catch(Exception e){
            System.out.println("Filepath Not Found!");
            return null;
        }
        return StudentDatabase;
    }

    //process user input
    public static void main(String[] args) {
        BahrHandlingInput instance = new BahrHandlingInput("", "", -1.0);// create an instance of the class
        Scanner inputHandler = new Scanner(System.in);// create an input handler
        var menuSelection = 0;
        do{
            instance.printMenu();//print menu
            try{//check if number inserted is an Int
                menuSelection = inputHandler.nextInt();
            }catch(Exception ex){//clear the input handler
                inputHandler.next();
                menuSelection = 0;
            }

            switch (menuSelection) {

                case 1://Enter Students Name
                    System.out.print("Enter Students Name: ");
                    instance.setStudentName(inputHandler.next());
                    instance.clearScreen();
                    break;

                case 2://Enter Students Academic Year
                    boolean yearValid = false;
                    do {
                        System.out.print("Enter Students Academic Year: ");
                        try {//check for freshman and such
                            instance.setStudentYear(inputHandler.next());
                            instance.clearScreen();
                            yearValid = instance.validateStudentYear(instance.getStudentYear());
                            if (!yearValid) System.out.println("Please enter Freshman, Sophomore, Junior, Senior");
                            instance.setStudentYear(instance.getStudentYear().substring(0, 1).toUpperCase() + instance.getStudentYear().substring(1));//capitalize the first letter
                        } catch (Exception ex) {//if it's not an actual year pull them back
                            System.out.println("Please enter Freshman, Sophomore, Junior, Senior");
                            inputHandler.next();
                        }
                    } while (!yearValid);
                    break;

                case 3://Enter Students GPA
                    boolean gpaValid = false;
                    do {
                        System.out.print("Enter Students GPA: ");
                        try {//check for gpa bounds
                            instance.setStudentGPA(inputHandler.nextDouble());
                            instance.clearScreen();
                            gpaValid = instance.validateStudentGPA(instance.getStudentGPA());
                            if (!gpaValid) System.out.println("Please enter a decimal between 0.0 and 4.0!");
                        } catch (Exception ex) {
                            System.out.println("Please enter a decimal between 0.0 and 4.0!");
                            inputHandler.next();
                        }
                    } while (!gpaValid);
                    break;

                case 4:
                    
                    instance.clearScreen();
                    System.out.println("Printing Current Student");
                    instance.printCompleteMenu(); //print complete menu

                case 5://Write to file
                    boolean isAnswered = instance.validateAllAnswered(); //check if all inputs are used
                    if (isAnswered){
                        instance.writeToFile();
                        System.out.println("Writing Data to File");
                    }else {
                        System.out.println("Please enter all info into the selections before saving the student data!");
                    }

                    break;

                case 6://read from file
                    System.out.println("Enter a filepath to read data");
                    String userPath = inputHandler.next();
                    File path = new File(userPath);                   
                    ArrayList<String> StudentDatabase = instance.readFromFile(path);
                    if (StudentDatabase != null){
                        for (int i = 0; i < StudentDatabase.size(); i+=3) {
                            System.out.println("Students Name: " + StudentDatabase.get(i));
                            System.out.println("Students Academic Year: " + StudentDatabase.get(i+1));
                            System.out.println("Students GPA: " + StudentDatabase.get(i+2));
                        }
                    }
                    break;

                case 7://Exiting
                    System.out.print("Exiting Program!");
                    break;

                default://error handling
                    System.out.println("Please Enter a Selection Between 1 and 5!");

            }
        }while (menuSelection != 7);
    }
}
