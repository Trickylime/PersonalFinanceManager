package com.financemanager.personalfinancemanager;

import com.financemanager.personalfinancemanager.transaction.TransactionData;
import com.financemanager.personalfinancemanager.transaction.TransactionItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    private static final String DELETE_TRANSACTION_TITLE_TEXT = "Delete Transaction Item";
    private static final String DELETE_CONFIRMATION_TEXT = "Are you sure? Press OK to confirm, or cancel";
    private static final String DELETE_TRANSACTION_ITEM_TEXT = "Delete item: %s - %s - £%,.2f";

    private static final String INVALID_INPUT_TITLE = "Invalid Input";
    private static final String INVALID_INPUT_HEADER = "Missing or Invalid Fields";
    private static final String INVALID_INPUT_CONTENT = "Please ensure all fields are filled out correctly.";

    private static final String ADD_NEW_TRANSACTION_TITLE = "Add New Transaction";
    private static final String ADD_NEW_TRANSACTION_HEADER = "Enter your new transaction details:";

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

        handRightClickMenuItems();
    }

    public void handRightClickMenuItems() {
        ContextMenu listContextMenu = new ContextMenu();

        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction((ActionEvent event) -> {
            showEditItemDialog();
        });

        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction((ActionEvent event) -> {
            deleteItem(getSelectedItem());
        });

        listContextMenu.getItems().addAll(editMenuItem, deleteMenuItem);
        incomeTableView.setContextMenu(listContextMenu);
        expenseTableView.setContextMenu(listContextMenu);
    }

    @FXML
    public void deleteItem(TransactionItem item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(DELETE_TRANSACTION_TITLE_TEXT);
        alert.setHeaderText(String.format(DELETE_TRANSACTION_ITEM_TEXT, item.getCategory(),
                (item.getType() ? "INCOME" : "EXPENSE"), item.getAmount()));

        alert.setContentText(DELETE_CONFIRMATION_TEXT);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            TransactionData.getInstance().deleteTransaction(item);
        }
    }

    @FXML
    public void handleDelKeyPressed(KeyEvent keyEvent) {
        TransactionItem selectedItem = getSelectedItem();
        if (selectedItem != null) {
            if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                deleteItem(selectedItem);
            }
        }
    }

    @FXML
    public void invalidInputDialog(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(INVALID_INPUT_TITLE);
        alert.setHeaderText(INVALID_INPUT_HEADER);
        alert.setContentText(INVALID_INPUT_CONTENT);
        alert.showAndWait();

        event.consume();
    }
    @FXML
    public void itemDialog(String newEditView, TransactionItem selectedItem) {
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

        switch (newEditView) {
            case "NEW" -> {
                dialog.setTitle(ADD_NEW_TRANSACTION_TITLE);
                dialog.setHeaderText(ADD_NEW_TRANSACTION_HEADER);

                dialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(
                        javafx.event.ActionEvent.ACTION, event -> {
                            if (!dialogController.isFormValid()) invalidInputDialog(event);
                        });

                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    TransactionItem newItem = dialogController.processNewItem();
                    if (newItem.getType()) incomeTableView.getSelectionModel().select(newItem);
                    else expenseTableView.getSelectionModel().select(newItem);
                }
            }
            case "EDIT" -> {
            }
            default -> {
            }
        }
    }

    public TransactionItem getSelectedItem() {
        if (incomeTableView.isFocused()) {
            return incomeTableView.getSelectionModel().getSelectedItem();
        } else if (expenseTableView.isFocused()) {
            return expenseTableView.getSelectionModel().getSelectedItem();
        }
        return null;
    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }

    public void showNewItemDialog() {
        itemDialog("NEW", getSelectedItem());
    }

    public void showEditItemDialog() {
        itemDialog("EDIT", getSelectedItem());
    }

    public void showItemDialog() {
        itemDialog("VIEW", getSelectedItem());
    }
}