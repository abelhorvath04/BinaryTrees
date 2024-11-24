package org.example;

import java.util.*;

/**
 * Controller class runs the program,
 * handles the user input and
 * send further the request if needed.
 */
public class Controller {
    private final static Scanner sc = new Scanner(System.in);
    private final static BinaryTreeOperations bto = new BinaryTreeOperations();

    /**
     * Starts the program.
     */
    public static void programStart() {
        boolean programRuns = true;

        while (programRuns) {
            Menu.printMenu();
            String userInput = sc.nextLine();
            switch (userInput) {
                case "1" -> makeBinaryTree();
                case "2" -> bto.showExistingTree();
                case "9" -> programRuns = false;
                default -> System.out.println("Wrong input!");
            }
        }
    }

    /**
     * This method is the start-point of a binary tree based on user input.
     * Reads the user input, processes it to an ArrayList and
     * passes it further on to build a binary tree.
     */
    private static void makeBinaryTree() {

        System.out.println("Write a list of integers separated by a comma:");
        System.out.println("For example: 1, 2, 3, 4, 5");
        System.out.println("+++");
        String userInput = sc.nextLine();
        Set<Integer> userInputList = processSetFromUserInput(userInput);

        bto.processList(new ArrayList<>(userInputList));

    }

    /**
     * This method cuts nad trims a string to a Set<Integer> from the user input string.
     * @param userInput is the String from the user.
     * @return the userInputSet that contains every number at most once.
     */
    private static Set<Integer> processSetFromUserInput(String userInput) {

        String[] userInputArray = userInput.split(",");
        Set<Integer> userInputSet = new LinkedHashSet<>();
        for (String s : userInputArray) {
            if (!s.isEmpty()) {
                try {
                    Integer listElement = Integer.parseInt(s.trim());
                    userInputSet.add(listElement);
                } catch (NumberFormatException e) {
                    System.out.println("Wrong input for: '" + s.trim() + "'");
                }
            }
        }
        return userInputSet;
    }
}
