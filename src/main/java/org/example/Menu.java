package org.example;

public class Menu {
    public static void printMenu() {
        String binaryTreeText = """
                ,,                                        ,                         \s
                ||     '         _                       ||                         \s
                ||/|, \\\\ \\\\/\\\\  < \\, ,._-_ '\\\\/\\\\       =||= ,._-_  _-_   _-_   _-_,\s
                || || || || ||  /-||  ||    || ;'        ||   ||   || \\\\ || \\\\ ||_. \s
                || |' || || || (( ||  ||    ||/          ||   ||   ||/   ||/    ~ ||\s
                \\\\/   \\\\ \\\\ \\\\  \\/\\\\  \\\\,   |/           \\\\,  \\\\,  \\\\,/  \\\\,/  ,-_- \s
                                           (                                        \s
                """;
        System.out.println(binaryTreeText);
        String menu = """
                1 - Write numbers to make a tree
                2 - See existing tree and operations
                
                9 - Exit
                """;
        System.out.println(menu);
    }
}
