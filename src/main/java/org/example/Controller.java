package org.example;

import java.util.*;

public class Controller {
    private final static Scanner sc = new Scanner(System.in);
    private final static BinaryTreeOperations bto = new BinaryTreeOperations();
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

    private static void makeBinaryTree() {

        System.out.println("Write a list of integers separated by a comma:");
        System.out.println("For example: 1, 2, 3, 4, 5");
        System.out.println("+++");
        String userInput = sc.nextLine();
        Set<Integer> userInputList = processArrayListFromUserInput(userInput);

        bto.processList(new ArrayList<>(userInputList));

    }

    private static Set<Integer> processArrayListFromUserInput(String userInput) {

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
