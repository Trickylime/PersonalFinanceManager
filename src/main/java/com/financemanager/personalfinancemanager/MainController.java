package com.financemanager.personalfinancemanager;

import com.financemanager.personalfinancemanager.transaction.TransactionData;
import com.financemanager.personalfinancemanager.transaction.TransactionItem;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class MainController {

    @FXML
    public TableView<TransactionItem> incomeTableView;
    @FXML
    public TableView<TransactionItem> expenseTableView;

    @FXML
    public void initialize() {
        incomeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        expenseTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        incomeTableView.setItems(TransactionData.getInstance().getTransactions());
        expenseTableView.setItems(TransactionData.getInstance().getTransactions());
        incomeTableView.getSelectionModel().selectFirst();

    }
}