module com.financemanager.personalfinancemanager2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;


    opens com.financemanager.personalfinancemanager.transaction to com.fasterxml.jackson.databind;
    opens com.financemanager.personalfinancemanager to javafx.fxml;
    exports com.financemanager.personalfinancemanager;
    exports com.financemanager.personalfinancemanager.transaction;
}