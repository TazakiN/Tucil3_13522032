package com.example.logic;

import com.example.interfaces.gn;
import com.example.tools.Kamus;
import com.example.tools.Node;

public class UCS extends BaseClass implements gn {

    public UCS(String kataAwal, String kataAkhir, Kamus kamus) {
        super(kataAwal, kataAkhir, kamus);
    }

    @Override
    public int countGn(Node node) {
        return node.getCost();
    }

    @Override
    public int countFn(Node node) {
        return countGn(node);
    }

}