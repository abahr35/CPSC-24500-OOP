package com.company;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int sum = 0;

        print_message("hey"); // this should print "hey"

        int x = sum_nums(4,5); // this should store 9 in x

        boolean is_valid = valid_x(x,9); //this should return true

        if (is_valid){
            int [] arr = {1,2,3,4,5,6,7};
            sum = total_sum(arr); //this should return 28
        }

        if (valid_x(sum,9)){
            print_message("The number from sum is valid against 9"); // this should not print
        }else{
            print_message("The number doesn't match 9"); // this should print
        }
        String [][]my_table = create_arr_table(10,10, "x"); //should return a table of 10x10 filled with xs
        print_loop(my_table);
    }

    public static void print_loop(String [][]table){
        //write code to print the table. the code should print a table like this:
        /*xxxxxxxxx
          xxxxxxxxx
          ....
          ....
         */
        for (int i=0; i<table.length; i++)
        {
            for (int j=0; j<table[i].length; j++)
            {
                System.out.print(table[i][j]);

            }
            System.out.print("\n");
        }

    }

    public static String [][]create_arr_table(int x_max, int y_max, String vals){
        //create a 2 dimmenisonal array. Assume x_max are your rows and y_max is your columns. Store vals at each index
        String [][] table = new String[x_max][y_max];

        //table[i][j]
        for (int i=0; i<x_max; i++)
        {
            for (int j=0; j<y_max; j++)
            {
                table[i][j] = vals;
            }
        }
        return table;

    }

    public static int total_sum(int []arr_to_sum){
        //write code that takes in an array and returns the sum of the array. Assume all numbers are positive.
        int x = 0;
        for (int item: arr_to_sum) {
            x +=item;
        }
        return x;
    }

    public static boolean valid_x(int x, int expected_value){
        //write code that returns true or false. True if x equals expected value, false if otherwise
        return x == expected_value;
    }

    public static int sum_nums(int x, int y){
        //write code here to return the sum of two numbers that are passed in
        return x+y;
    }
    public static void print_message(String message){
        //insert code here. Assume you'll be passed a string variable called message. Your code should print the message
        System.out.println(message);
    }

}