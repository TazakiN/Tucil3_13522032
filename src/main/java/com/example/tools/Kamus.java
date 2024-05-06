package com.example.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Kamus {
    private HashSet<String> wordsSet;

    public Kamus(int panjangKata) throws IOException {
        System.out.println("Membangun kamus kata...");
        InputStream in = getClass().getResourceAsStream("/com/example/dict.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        wordsSet = new HashSet<>();

        String kata;
        while ((kata = reader.readLine()) != null) {
            if (kata.length() == panjangKata) {
                wordsSet.add(kata);
            }
        }

        reader.close();
    }

    public boolean contains(String word) {
        word.toUpperCase();
        return wordsSet.contains(word);
    }

    public void printAllWords() {
        for (String word : wordsSet) {
            System.out.println(word);
        }
    }
}