package com.financemanager.personalfinancemanager;

import com.financemanager.personalfinancemanager.transaction.TransactionData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FinanceManagerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FinanceManagerApplication.class.getResource("mainwindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Personal Finance Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() {
        try {
            TransactionData.getInstance().save();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() {
        try {
            TransactionData.getInstance().load();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}