package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import org.barfuin.texttree.api.DefaultNode;
import org.barfuin.texttree.api.TreeOptions;
import org.barfuin.texttree.api.TextTree;
import org.barfuin.texttree.api.style.TreeStyles;

public class BinaryTreeOperations {

    Node root;

    public BinaryTreeOperations() {
    }

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

    private int calculateHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    private void addValuesToTheTree(ArrayList<Integer> userInputList) {
        for (int userInputNumber : userInputList) {
            add(userInputNumber);
        }
    }

    private void add(int value) {
        root = addRecursive(root, value);
    }

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

    public void showExistingTree() {
        Integer[] numbers = {8,4,9,7,2,13,11,46};
        ArrayList<Integer> valueList = new ArrayList<>(Arrays.asList(numbers));
        processList(valueList);
    }

    private void printPreOrder() {
        System.out.println("Pre order:");
        traversePreOrder(root);
        System.out.println();
    }

    private void printPostOrder() {
        System.out.println("Post order:");
        traversePostOrder(root);
        System.out.println();
    }

    private void printInOrder() {
        System.out.println("In Order: ");
        traverseInOrder(root);
        System.out.println();
    }

    private void printLevelOrder() {
        System.out.println("Level order:");
        traverseLevelOrder(root);
        System.out.println();
    }

    private void traversePreOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.printf("%s ", node.value);
        traversePreOrder(node.left);
        traversePreOrder(node.right);
    }

    private void traversePostOrder(Node node) {
        if (node == null) {
            return;
        }

        traversePostOrder(node.left);
        traversePostOrder(node.right);
        System.out.printf("%s ", node.value);
    }

    private void traverseInOrder(Node node) {
        if (node == null) {
            return;
        }

        traverseInOrder(node.left);
        System.out.printf("%s ", node.value);
        traverseInOrder(node.right);
    }

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

    private void clearRootAndLevel() {
        root = null;
    }

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
