package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.logic.AStar;
import com.example.logic.GreedyBestFirstSearch;
import com.example.logic.UCS;
import com.example.tools.Kamus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainController {

    ObservableList<String> algorithmList = FXCollections.observableArrayList("A*", "Greedy Best First Search",
            "Uniform Cost Search");
    private String kataAwal;
    private String kataAkhir;
    private String algoritma;

    @FXML
    private ComboBox<String> algorithmComboBox;

    @FXML
    private TextField textKataAwal;

    @FXML
    private TextField textKataAkhir;

    @FXML
    private Label timeLabel;

    @FXML
    private Label banyakNodeLabel;

    @FXML
    private Label panjangJalurLabel;

    @FXML
    private Text pathText;

    @FXML
    private void initialize() {
        algorithmComboBox.setItems(algorithmList);
        algorithmComboBox.getSelectionModel().select(0);
    }

    @FXML
    private void solve() throws IOException {
        kataAwal = textKataAwal.getText().toUpperCase();
        kataAkhir = textKataAkhir.getText().toUpperCase();
        algoritma = algorithmComboBox.getValue();
        reset();

        List<String> pathHasil = new ArrayList<>();
        StringBuilder path = new StringBuilder();

        Kamus kamus = new Kamus(kataAwal.length());

        if (!kamus.contains(kataAwal)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Kata awal tidak ditemukan");
            alert.setContentText("Kata awal tidak ditemukan dalam kamus");
            alert.showAndWait();
            return;
        }

        if (!kamus.contains(kataAkhir)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Kata akhir tidak ditemukan");
            alert.setContentText("Kata akhir tidak ditemukan dalam kamus");
            alert.showAndWait();
            return;
        }

        long startTime = System.currentTimeMillis();
        switch (algoritma) {
            case "A*":
                AStar aStar = new AStar(kataAwal, kataAkhir, kamus);
                aStar.driver();
                banyakNodeLabel.setText(String.valueOf(aStar.getNodeDikunjungi()));
                panjangJalurLabel.setText(String.valueOf(aStar.getPathHasil().size() - 1));
                pathHasil = aStar.getPathHasil();
                break;
            case "Greedy Best First Search":
                GreedyBestFirstSearch greedyBestFirstSearch = new GreedyBestFirstSearch(kataAwal, kataAkhir, kamus);
                greedyBestFirstSearch.driver();
                banyakNodeLabel.setText(String.valueOf(greedyBestFirstSearch.getNodeDikunjungi()));
                panjangJalurLabel.setText(String.valueOf(greedyBestFirstSearch.getPathHasil().size() - 1));
                pathHasil = greedyBestFirstSearch.getPathHasil();
                break;
            case "Uniform Cost Search":
                UCS ucs = new UCS(kataAwal, kataAkhir, kamus);
                ucs.driver();
                banyakNodeLabel.setText(String.valueOf(ucs.getNodeDikunjungi()));
                panjangJalurLabel.setText(String.valueOf(ucs.getPathHasil().size() - 1));
                pathHasil = ucs.getPathHasil();
                break;
            default:
                break;
        }
        long endTime = System.currentTimeMillis();

        if (pathHasil.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Jalur tidak ditemukan");
            alert.setContentText("Tidak ditemukan jalur dari kata awal ke kata akhir.");
            alert.showAndWait();
            return;
        }

        int i = 0;
        for (String kata : pathHasil) {
            path.append("Step ").append(i).append(": ").append(kata).append("\n");
            i++;
        }

        pathText.setText(path.toString());

        long executionTime = endTime - startTime;
        timeLabel.setText(String.valueOf(executionTime) + " ms");
    }

    private void reset() {
        pathText.setText("");
        timeLabel.setText("");
        banyakNodeLabel.setText("");
        panjangJalurLabel.setText("");
    }

}
