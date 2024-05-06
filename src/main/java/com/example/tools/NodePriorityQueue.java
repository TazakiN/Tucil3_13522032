package com.example.tools;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class NodePriorityQueue {
    private PriorityQueue<Node> queue;
    private HashSet<String> kataDitemukan;

    public NodePriorityQueue() {
        Comparator<Node> nodeComparator = Comparator.comparingInt(Node::getFn);
        this.queue = new PriorityQueue<>(nodeComparator);
        this.kataDitemukan = new HashSet<>();
    }

    public void add(Node node) {
        if (!kataDitemukan.contains(node.getWord())) {
            queue.add(node);
            kataDitemukan.add(node.getWord());
        }
    }

    public Node poll() {
        return queue.poll();
    }

    public Node peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void clear() {
        queue.clear();
    }

    public void printQueue() {
        System.out.println("\nIsi queue:");
        for (Node node : queue) {
            System.out.println(node.getWord() + " " + node.getFn());
        }
    }
}
