package com.financemanager.personalfinancemanager;

import com.financemanager.personalfinancemanager.transaction.TransactionData;
import com.financemanager.personalfinancemanager.transaction.TransactionItem;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.time.LocalDate;
import java.util.function.UnaryOperator;

public class DialogController {

    @FXML
    private CheckBox isIncome;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField amountField;
    @FXML
    private CheckBox recurringCheckBox;

    @FXML
    public void initialize() {
        // Add a TextFormatter to the amountField to allow only valid numbers with up to 2 decimal places
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*(\\.\\d{0,2})?")) { // Digits with optional decimal part
                return change;
            }
            return null;
        };

        TextFormatter<String> formatter = new TextFormatter<>(filter);
        amountField.setTextFormatter(formatter);
    }

    public TransactionItem processNewItem() {
        boolean type = isIncome.isSelected();
        LocalDate date = datePicker.getValue();
        String category = categoryField.getText().trim();

        // Get and validate the amount
        String amountText = amountField.getText().trim();
        double amount = 0.0;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount entered. Please enter a valid number.");
        }

        boolean recurring = recurringCheckBox.isSelected();

        TransactionItem newItem = new TransactionItem(date, amount, category, type, recurring);
        TransactionData.getInstance().add(newItem);
        return newItem;
    }

//    public void viewItemDetails(ContactItem item) {
//        firstName.setText(item.getFirstName());
//        lastName.setText(item.getLastName());
//        phoneNumber.setText(item.getPhoneNumber());
//        notes.setText(item.getNotes());
//    }
//
//    public void editItemDetails(ContactItem item) {
//        firstNameField.setText(item.getFirstName());
//        lastNameField.setText(item.getLastName());
//        phoneNumberField.setText(item.getPhoneNumber());
//        notesAreaField.setText(item.getNotes());
//    }
//
//    public void processEditItem(ContactItem item) {
//        String firstName = firstNameField.getText().trim();
//        String lastName = lastNameField.getText().trim();
//        String phoneNumber = phoneNumberField.getText().trim();
//        String notes = notesAreaField.getText().trim();
//
//        ContactData.getInstance().editContact(item, firstName, lastName, phoneNumber, notes);
//    }
}
