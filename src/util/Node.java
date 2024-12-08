package util;

public class Node {
    private Node left;
    private Node right;
    private String current;

    public Node(String current, Node left, Node right) {
        this.left = left;
        this.right = right;
        this.current = current;
    }

    public Node(String current) {
        this.current = current;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getCurrent() {
        return current;
    }

    public void setLeft(final Node left) {
        this.left = left;
    }

    public void setRight(final Node right) {
        this.right = right;
    }
}
