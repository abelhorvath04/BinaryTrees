package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import org.barfuin.texttree.api.DefaultNode;
import org.barfuin.texttree.api.TreeOptions;
import org.barfuin.texttree.api.TextTree;
import org.barfuin.texttree.api.style.TreeStyles;

/**
 * This class is responsible for every operation within the binary tree.
 */
public class BinaryTreeOperations {

    Node root;

    public BinaryTreeOperations() {
    }

    /**
     * This class is the start-point for the binary-tree processes.
     * Prints the trees in the four order, if they are not empty,
     * draws it in the console and
     * examines if it is balanced or not (if meets the AVL requirements).
     * @param valueList is the hardcoded / user-input integer-list
     */
    public void processList(ArrayList<Integer> valueList) {
        clearRootAndLevel();
        addValuesToTheTree(valueList);
        printTreeAsText();
        printPreOrder();
        printPostOrder();
        printInOrder();
        printLevelOrder();

        System.out.println();
        int height = calculateHeight(root);
        System.out.printf("Height of the tree: %d%n", height);

        boolean balanced = isTreeBalanced(root);
        System.out.printf("The tree is %s%n", balanced ? "balanced. - AVL-Tree" : "not balanced. - Not AVL-tree");
    }

    /**
     * Examines the balance, if the tree is not empty.
     * @param node is the root-node.
     * @return true if balanced, false if not.
     */
    private boolean isTreeBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isTreeBalanced(node.left) && isTreeBalanced(node.right);
    }

    /**
     * This method calculates the height difference between the branches.
     * @param node goes recursive deeper, if the tree is not empty.
     * @return the difference between the height of the two binary tree branch.
     */
    private int calculateHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * This method calls the add method for each integer to the tree.
     * @param userInputList is the list of the integers.
     */
    private void addValuesToTheTree(ArrayList<Integer> userInputList) {
        for (int userInputNumber : userInputList) {
            add(userInputNumber);
        }
    }

    /**
     * This method makes the tree from the root node.
     * @param value is the integer.
     */
    private void add(int value) {
        root = addRecursive(root, value);
    }

    /**
     * This recursive method adds the integers to the root node and further.
     * @param current is first the root node, then the next one.
     * @param value is always the next node.
     * @return the new node, if it is the first, or gives back the next root.
     */
    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }
        return current;
    }

    /**
     * Hardcoded list for quick testing.
     */
    public void showExistingTree() {
        Integer[] numbers = {8,4,9,7,2,13,11,46};
        ArrayList<Integer> valueList = new ArrayList<>(Arrays.asList(numbers));
        processList(valueList);
    }

    /**
     * Prints pre-order.
     */
    private void printPreOrder() {
        System.out.println("Pre order:");
        traversePreOrder(root);
        System.out.println();
    }

    /**
     * Prints post-order.
     */
    private void printPostOrder() {
        System.out.println("Post order:");
        traversePostOrder(root);
        System.out.println();
    }

    /**
     * Prints in-order.
     */
    private void printInOrder() {
        System.out.println("In Order: ");
        traverseInOrder(root);
        System.out.println();
    }

    /**
     * Prints level-order.
     */
    private void printLevelOrder() {
        System.out.println("Level order:");
        traverseLevelOrder(root);
        System.out.println();
    }

    /**
     * Traverse pre-order recursively.
     * @param node is always the changing root.
     */
    private void traversePreOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.printf("%s ", node.value);
        traversePreOrder(node.left);
        traversePreOrder(node.right);
    }

    /**
     * Traverse post-order recursively.
     * @param node is always the changing root.
     */
    private void traversePostOrder(Node node) {
        if (node == null) {
            return;
        }

        traversePostOrder(node.left);
        traversePostOrder(node.right);
        System.out.printf("%s ", node.value);
    }

    /**
     * Traverse in-order recursively.
     * @param node is always the changing root.
     */
    private void traverseInOrder(Node node) {
        if (node == null) {
            return;
        }

        traverseInOrder(node.left);
        System.out.printf("%s ", node.value);
        traverseInOrder(node.right);
    }

    /**
     * Traverse level-order recursively with a queue.
     * @param node is always the changing root.
     */
    private void traverseLevelOrder(Node node) {
        if (node == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node tempNode = nodes.remove();
            System.out.printf("%s ", tempNode.value);

            if (tempNode.left != null) {
                nodes.add(tempNode.left);
            }

            if (tempNode.right != null) {
                nodes.add(tempNode.right);
            }
        }
    }

    /**
     * This method clears the tree, if the user wants to
     * use it more than once in running time.
     */

    private void clearRootAndLevel() {
        root = null;
    }

    /**
     * This method builds a tree from text-tree by barfuin which is
     * a Java library for rendering trees in the console.
     * Source: https://gitlab.com/barfuin/text-tree
     * https://mvnrepository.com/artifact/org.barfuin.texttree/text-tree
     */
    private void printTreeAsText() {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }
        System.out.println("The tree:");

        DefaultNode treeRoot = buildTreeNode(root);

        TreeOptions options = new TreeOptions();
        options.setStyle(TreeStyles.ASCII_ROUNDED);
        options.setEnableDefaultColoring(true);

        String rendered = TextTree.newInstance(options).render(treeRoot);
        System.out.println(rendered);
    }

    /**
     * This method makes the tree for the text-tree dependency by barfuin.
     * @param node
     * @return
     */
    private DefaultNode buildTreeNode(Node node) {
        if (node == null) {
            return null;
        }

        DefaultNode currentNode = new DefaultNode(String.valueOf(node.value));

        if (node.left != null) {
            currentNode.addChild(buildTreeNode(node.left));
        }
        if (node.right != null) {
            currentNode.addChild(buildTreeNode(node.right));
        }

        return currentNode;
    }

}
