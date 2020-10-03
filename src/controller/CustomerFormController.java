package controller;

import business.BOFactory;
import business.BOType;
import business.custom.CustomerBO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CustomerTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {
    public Button btnDelete;
    public Button btnSave;
    public Button btnBack;
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public Button btnAddCustomer;
    public TableView<CustomerTM> tblCustomerDetails;
    public AnchorPane root;
    private static CustomerBO customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);

    public void initialize(){
        //basic initializations
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        btnAddCustomer.requestFocus();

        //loading the Customers
        loadAllCustomer();

        //map the columns
        tblCustomerDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblCustomerDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblCustomerDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerAddress"));

        tblCustomerDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM selectedCustomer) {
                if(selectedCustomer==null){
                    return;
                }

                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                btnAddCustomer.setDisable(true);
                txtCustomerId.setText(selectedCustomer.getCustomerId());
                txtCustomerName.setText(selectedCustomer.getCustomerName());
                txtCustomerAddress.setText(selectedCustomer.getCustomerAddress());
            }
        });

    }

    public void btnAddCustomer_OnAction(ActionEvent actionEvent) {
        //btn and textfield initializations
        btnAddCustomer.setDisable(true);
        btnSave.setDisable(false);
        txtCustomerName.requestFocus();

        //TODO validations

        //generate the customer id
        generateId();

    }

    public void btnDelete_OnAction(ActionEvent actionEvent){
        CustomerTM selectedCustomer = tblCustomerDetails.getSelectionModel().getSelectedItem();
        try {
            customerBO.deleteCustomer(selectedCustomer.getCustomerId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        btnDelete.setDisable(true);
        btnSave.setDisable(true);
        btnSave.setText("Save");
        btnAddCustomer.setDisable(false);
        btnAddCustomer.requestFocus();
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();

    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        //btn and textfield initializations when save button is pressed
        btnSave.setDisable(true);
        btnAddCustomer.setDisable(false);
        btnAddCustomer.requestFocus();

        String customerId = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String customerAddress = txtCustomerAddress.getText();

        if(btnSave.getText().equals("Update")){
            try {
                customerBO.updateCustomer(customerId,customerName,customerAddress);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else {
            try {
                customerBO.saveCustomer(customerId,customerName,customerAddress);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //clear textfields
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        loadAllCustomer();

    }

    @SuppressWarnings("Duplicates")
    public void btnBack_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    private void generateId(){
        String newId = customerBO.generateNewCustomerId();
        txtCustomerId.setText(newId);
    }

    private void loadAllCustomer(){
        tblCustomerDetails.getItems().clear();
        List<CustomerTM> allCustomers = customerBO.getAllCustomers();
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList(allCustomers);
        tblCustomerDetails.setItems(customerTMS);
    }

}
