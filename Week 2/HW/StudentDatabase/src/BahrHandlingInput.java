import java.io.IOException;
import java.util.Scanner;
/*
In this assignment you will create a simple menu-driven application that would be used by an administrator at the college to enter information about a student.
The interface for this application will be a command line interface that will use a Menu Driven approach.
What this means is a series of menus will be provided to the user for them to interact with.
We’ll be using much of the material covered in our first reading assignment; particularly scanners; ensure you’re familiar with that.
 */

/*
First, the interface must be a command line interface. For this assignment we will only have 1 menu. The options must include:
1. Enter the Students name,
2. Enter the Students Academic Year,
3. Enter the Students GPA,
4. Display Students Info;
5. Exit.
The user will be presented a menu with these 6 options. They will answer with a numeric response of 1,2,3,4, or 5.
Based on this response a question will be presented telling them to provide the information they selected (Enter Students Name: , Enter Students GPA, etc.).
They will provide this information and the menu will be presented again, so they may enter another piece of information.
This will repeat until the user selects the Exit option. Let’s take a moment to discuss each option the user can select.
 */


public class BahrHandlingInput {
    public String studentName;
    public String studentYear;
    public double studentGPA;

    //check for input within specs
    private boolean checkAnswerMenu(int number){
        return number < 6 && number > 0;
    }
    public static void printMenu(String[] menuStrings){
        //print menu
        System.out.println("1. " + menuStrings[0]);
        System.out.println("2. " + menuStrings[1]);
        System.out.println("3. " + menuStrings[2]);
        System.out.println("4. " + menuStrings[3]);
        System.out.println("5. " + menuStrings[4]);
    }

    public boolean isDouble(String input){
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e){
            System.out.println("Input can only be a Decimal number");
            return false;
        }
    }

    public boolean isInt(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e){
            System.out.println("Input can only be a Whole number");
            return false;
        }
    }

    public boolean isAcademic(String input){
        input.toLowerCase();
        return input.equals("freshman") || input.equals("sophomore") || input.equals("junior") || input.equals("Senior");
    }



    //processes user input
    public int processMenu() throws IOException {
        //create a Scanner for input
        Scanner inputHandler = new Scanner(System.in);
        String[] menuStrings = {"Enter Students Name", "Enter Students Academic Year", "Enter Students GPA",  "Display Students Info", "Exit"};

        //loop menu
        var menuIn = 0;
        do {
            printMenu(menuStrings);
            try {
                System.out.print("Please enter which number you want to answer: ");
                menuIn = inputHandler.nextInt();
                //in not in bounds return false
                /*
                if (!checkAnswerMenu(menuIn)) {
                    //check if within menu bounds
                    System.out.println("Input Invalid!");
                }

                 */

            } catch (Exception ex) {
                //check for ints entered
                System.out.println("Input can only be a whole number!");
                inputHandler.next();
            }
            //print question

            //switch for answers
            switch (menuIn) {
                case 1://student name (string)
                    System.out.print(menuStrings[menuIn - 1] + ":");
                    try {
                        studentName = inputHandler.next();
                    } catch (Exception e) {
                        System.out.println("Input can only be a String!");
                    }
                    break;

                    //
                //HERE IS WHERE I WAS
                //ERROR HANDLING
                //
                //



                case 2://academic year (string)
                    System.out.print(menuStrings[menuIn - 1] + ":");
                    try {
                        studentYear = inputHandler.next();
                        isAcademic(studentYear);
                    } catch (Exception ex) {
                        System.out.println("Input can only be an Academic Year!");
                        inputHandler.next();
                    }
                    break;
                case 3://GPA (double)
                    System.out.print(menuStrings[menuIn - 1] + ":");
                    try {
                        studentGPA = inputHandler.nextDouble();
                    } catch (NumberFormatException e) {

                    }
                    break;
                case 4:
                    break;
                case 5:
                    return menuIn;
                default:
                    System.out.println("You must select a number between 1 and 5");
            }
        }while (menuIn != 5);
            /*
            if(menuIn == 5){
                return menuIn;
            }
            System.out.print(menuStrings[menuIn -1] + ":");

            //Student Name (string)

            if(menuIn == 1){
                try{
                    studentName = inputHandler.next();
                }catch (Exception ex){
                    System.out.println("Input can only be a String!");
                    throw ex;
                }
            //Student Academic Year (int)
            }else if(menuIn == 2){

                try {
                    studentYear = inputHandler.nextInt();
                }catch (Exception ex){
                    System.out.println("Input can only be a Whole number!");
                    throw ex;
                }
                //Student GPA (Double)
            }else if (menuIn== 3){
                try {
                    studentGPA = inputHandler.nextDouble();
                }catch (Exception ex){
                    System.out.println("Input can only be a Decimal number");
                    throw ex;
                }
            }

             */
        //return the selection number for the loop
    return menuIn;
    }

    public static void main(String[] args) throws IOException {

        BahrHandlingInput instance = new BahrHandlingInput();
        int resume = 0;
        do{
            resume = instance.processMenu();
        }while (resume != 5);
        System.out.println("Program Exiting!");
    }
}
