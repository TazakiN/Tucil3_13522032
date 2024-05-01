package tools;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String word;
    private int fn;
    private Node parent;

    public Node(String word, int fn, Node parent) {
        this.word = word;
        this.fn = fn;
        this.parent = parent;
    }

    public String getWord() {
        return word;
    }

    public int getFn() {
        return fn;
    }

    public Node getParent() {
        return parent;
    }

    public void setFn(int fn) {
        this.fn = fn;
    }

    public int getCost() {
        int count = 0;
        Node current = this;
        while (current != null) {
            count++;
            current = current.getParent();
        }
        return count;
    }

    public void printPath() {
        Node current = this;
        int num = 0;
        List<Node> path = new ArrayList<>();

        while (current != null) {
            path.add(current);
            current = current.getParent();
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println("Step " + num + ": " + path.get(i).getWord());
            num++;
        }
    }
}
