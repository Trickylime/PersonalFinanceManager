package com.financemanager.personalfinancemanager;

import com.financemanager.personalfinancemanager.transaction.TransactionData;
import com.financemanager.personalfinancemanager.transaction.TransactionItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    @FXML
    public BorderPane mainBorderPane;
    @FXML
    public TableView<TransactionItem> incomeTableView;
    @FXML
    public TableView<TransactionItem> expenseTableView;

    @FXML
    public void initialize() {
        incomeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        expenseTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        incomeTableView.setItems(TransactionData.getInstance().getIncomeTransactions());
        expenseTableView.setItems(TransactionData.getInstance().getExpenseTransactions());

        incomeTableView.getSelectionModel().selectFirst();
        expenseTableView.getSelectionModel().selectFirst();
    }

    @FXML
    public void deleteItem(TransactionItem item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact Item");
        alert.setHeaderText("Delete item: " + item.getCategory() + " - "
                + item.getType() + " - £" + item.getAmount() + "?");
        alert.setContentText("Are you sure? Press OK to confirm, or cancel");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            TransactionData.getInstance().deleteTransaction(item);
        }
    }
    @FXML
    public void itemDialog(String newEditView, TableView<TransactionItem> currentTable) {
        newEditView = newEditView.toUpperCase();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                switch(newEditView.toUpperCase()) {
                    case "NEW", "EDIT" -> "newTransactionItemDialog.fxml";
                    case "VIEW" -> "transactionItemDialog.fxml";
                    default ->  throw new IllegalArgumentException("Invalid dialog type: " + newEditView);
                }));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController = fxmlLoader.getController();
        TransactionItem selectedItem = currentTable.getSelectionModel().getSelectedItem();

        switch (newEditView) {
            case "NEW" -> {
                dialog.setTitle("Add New Transaction");
                dialog.setHeaderText("Enter your new transaction details:");
                Optional<ButtonType> result = dialog.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    TransactionItem newItem = dialogController.processNewItem();
                    currentTable.refresh();
                    currentTable.getSelectionModel().select(newItem);
                }
            }
            case "EDIT" -> {
            }
            default -> {
            }
        }
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void showNewIncomeItemDialog() {
        itemDialog("NEW", incomeTableView);
    }

    public void showEditIncomeItemDialog() {
        itemDialog("EDIT", incomeTableView);
    }

    public void showIncomeItemDialog() {
        itemDialog("VIEW", incomeTableView);
    }
}