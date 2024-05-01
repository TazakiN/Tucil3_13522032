package logic;

import java.util.List;

import interfaces.hn;
import tools.Kamus;
import tools.Node;

public class GreedyBestFirstSearch extends BaseClass implements hn {

    public GreedyBestFirstSearch(String kataAwal, String kataAkhir, Kamus kamus) {
        super(kataAwal, kataAkhir, kamus);
    }

    @Override
    public int countHn(String kataAwal, String kataAkhir, int panjangKata) {
        int h = 0;
        for (int i = 0; i < panjangKata; i++) {
            if (kataAwal.charAt(i) != kataAkhir.charAt(i)) {
                h++;
            }
        }
        return h;
    }

    @Override
    public int countFn(Node node) {
        return countHn(node.getWord(), kataAkhir, panjangKata);
    }

    @Override
    public void driver() {
        long startTime = System.currentTimeMillis();

        Node start = new Node(kataAwal, 0, null);
        pQueue.add(start);
        boolean isStuckOrFound = false;
        while (!isStuckOrFound) {
            Node current = pQueue.poll();
            if (current == null) {
                System.out.println("\u001B[31m\nTidak ada kata yang bisa di-generate lagi.\u001B[0m");
                System.out.println("\u001B[34mBanyak node yang dikunjungi: \u001B[0m" + nodeDikunjungi);
                isStuckOrFound = true;
                break;
            }
            nodeDikunjungi++;
            pQueue.clear();
            if (current.getWord().equals(kataAkhir)) {
                displayHasil(current);
                isStuckOrFound = true;
            } else {
                List<Node> generatedNodes = generateKata(current);
                for (Node node : generatedNodes) {
                    pQueue.add(node);
                }
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("\u001B[33mWaktu eksekusi: " + (endTime - startTime) + " ms\u001B[0m");
    }
}
