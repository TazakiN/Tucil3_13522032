package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Kamus {
    private HashSet<String> wordsSet;

    public Kamus(int panjangKata) throws IOException {
        System.out.println("Membangun kamus kata...");
        BufferedReader reader = new BufferedReader(new FileReader("scrabble_words.txt"));
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