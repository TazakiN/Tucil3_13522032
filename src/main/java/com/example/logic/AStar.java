package com.example.logic;

import com.example.interfaces.*;
import com.example.tools.Kamus;
import com.example.tools.Node;

public class AStar extends BaseClass implements hn, gn {

    public AStar(String kataAwal, String kataAkhir, Kamus kamus) {
        super(kataAwal, kataAkhir, kamus);
    }

    @Override
    public int countGn(Node node) {
        return node.getCost();
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
        return countGn(node) + countHn(node.getWord(), kataAkhir, panjangKata);
    }
}
