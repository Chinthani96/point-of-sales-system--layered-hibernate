package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public AnchorPane root;
    public Button btnManageCustomer;
    public Button btnManageItems;
    public Button btnPlaceOrder;
    public Button btnSearchOrder;

    public void btnManageCustomer_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root  = FXMLLoader.load(this.getClass().getResource("/view/CustomerForm.fxml"));
        Scene customerScene = new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(customerScene);
        mainStage.centerOnScreen();

    }

    public void btnManageItems_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/ItemForm.fxml"));
        Scene itemScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(itemScene);
        mainStage.centerOnScreen();
    }

    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/OrderDetailsForm.fxml"));
        Scene orderScene = new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(orderScene);
        mainStage.centerOnScreen();
    }

    public void btnSearchOrder_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/SearchOrderForm.fxml"));
        Scene orderScene = new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(orderScene);
        mainStage.centerOnScreen();
    }
}
