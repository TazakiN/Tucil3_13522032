package logic;

import interfaces.gn;
import tools.Kamus;
import tools.Node;

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