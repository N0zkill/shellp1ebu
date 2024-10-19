package com.example.module03_basicgui_db_interface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DB_GUI_Controller {

    private ConnDbOps dbOps;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField deptField;
    @FXML
    private TextField majorField;
    @FXML
    private TextField idField;  // Used for updates and deletions
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    @FXML
    public void initialize() {
        dbOps = new ConnDbOps();
        dbOps.connectToDatabase();  // Initialize database connection

        // Add event handlers for the buttons
        addButton.setOnAction(e -> addUser());
        updateButton.setOnAction(e -> updateUser());
        deleteButton.setOnAction(e -> deleteUser());
    }

    private void addUser() {
        String firstName = firstNameField.getText();
        String dept = deptField.getText();
        String major = majorField.getText();

        if (!firstName.isEmpty() && !dept.isEmpty() && !major.isEmpty()) {
            // Use ConnDbOps to add user to the database
            dbOps.addUser(firstName, dept, major);
        }
    }

    private void updateUser() {
        String id = idField.getText();
        String firstName = firstNameField.getText();
        String dept = deptField.getText();
        String major = majorField.getText();

        if (!id.isEmpty() && !firstName.isEmpty() && !dept.isEmpty() && !major.isEmpty()) {
            // Use ConnDbOps to update user
            dbOps.updateUser(Integer.parseInt(id), firstName, dept, major);
        }
    }

    private void deleteUser() {
        String id = idField.getText();
        if (!id.isEmpty()) {
            // Use ConnDbOps to delete user
            dbOps.deleteUser(Integer.parseInt(id));
        }
    }
}

