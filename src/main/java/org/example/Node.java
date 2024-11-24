package org.example;

/**
 * This class contains the essential parts of the tree's node.
 */
public class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        left = right = null;
    }
}
