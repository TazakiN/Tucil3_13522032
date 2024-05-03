package logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import tools.Kamus;
import tools.Node;
import tools.NodePriorityQueue;

public abstract class BaseClass {

    protected String kataAwal;
    protected String kataAkhir;
    protected int panjangKata;
    private Kamus kamus;
    protected int nodeDikunjungi;
    protected HashSet<String> visited;

    protected NodePriorityQueue pQueue;

    public BaseClass(String kataAwal, String kataAkhir, Kamus kamus) {
        this.kataAwal = kataAwal;
        this.kataAkhir = kataAkhir;
        this.panjangKata = kataAwal.length();
        this.kamus = kamus;
        this.nodeDikunjungi = 0;
        this.pQueue = new NodePriorityQueue();
        this.visited = new HashSet<>();
    }

    public String getKataAwal() {
        return kataAwal;
    }

    public String getKataAkhir() {
        return kataAkhir;
    }

    public int getPanjangKata() {
        return panjangKata;
    }

    protected boolean isKata(String kata) {
        return kamus.contains(kata);
    }

    protected List<Node> generateKata(Node parentNode) {
        List<Node> hasil = new ArrayList<>();
        for (int i = 0; i < getPanjangKata(); i++) {
            char[] chars = parentNode.getWord().toCharArray();
            if (chars[i] == kataAkhir.charAt(i)) {
                continue;
            }
            for (char c = 'A'; c <= 'Z'; c++) {
                if (c != chars[i]) {
                    chars[i] = c;
                    String kata = new String(chars);
                    if (isKata(kata) && !visited.contains(kata)) {
                        Node nodeBaru = new Node(kata, 0, parentNode);
                        nodeBaru.setFn(countFn(nodeBaru));
                        hasil.add(nodeBaru);
                        visited.add(kata);
                    }
                }
            }
        }
        return hasil;
    }

    public void displayHasil(Node node) {
        System.out.println("\u001B[33m\n ----------- HASIL ----------- \u001B[0m");
        System.out.println("\u001B[35mJumlah node yang dikunjungi: \u001B[0m" + nodeDikunjungi);
        System.out.println("\u001B[35mJumlah step: \u001B[0m" + ((int) (node.getCost() / 2) - 1));
        node.printPath();
    }

    public abstract int countFn(Node node);

    public void driver() {
        long startTime = System.currentTimeMillis();
        Node nodeAwal = new Node(kataAwal, 0, null);
        nodeAwal.setFn(countFn(nodeAwal));
        pQueue.add(nodeAwal);
        boolean isStuckOrFound = false;

        while (!isStuckOrFound) {
            Node currentNode = pQueue.poll();

            if (currentNode == null) {
                System.out.println("\u001B[31m\nKata Tidak bisa diubah ke kata lain lagi.\u001B[0m");
                System.out.println("\u001B[34mBanyak node yang dikunjungi: \u001B[0m" + nodeDikunjungi);
                isStuckOrFound = true;
                break;
            }

            nodeDikunjungi++;

            if (currentNode.getWord().equals(kataAkhir)) {
                displayHasil(currentNode);
                isStuckOrFound = true;
                break;
            }

            for (Node node : generateKata(currentNode)) {
                pQueue.add(node);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("\u001B[33m\nWaktu eksekusi: " + (endTime - startTime) + " ms\u001B[0m");
    }
}