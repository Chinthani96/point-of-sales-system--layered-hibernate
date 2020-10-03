package controller;

import business.BOFactory;
import business.BOType;
import business.custom.ItemBO;
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
import util.ItemTM;

import java.io.IOException;
import java.util.List;

public class ItemFormController {
    public TextField txtItemID;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public TableView<ItemTM> tblItemDetails;
    public Button btnAddItem;
    public Button btnSave;
    public Button btnDelete;
    public Button btnBack;
    public AnchorPane root;
    private static ItemBO itemBO = BOFactory.getInstance().getBO(BOType.ITEM);

    public void initialize(){
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        btnDelete.requestFocus();

        //load all items
        loadAllItems();

        //mapping columns
        tblItemDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblItemDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItemDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItemDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        tblItemDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemTM>() {
            @Override
            public void changed(ObservableValue<? extends ItemTM> observable, ItemTM oldValue, ItemTM selectedItem) {
                if(selectedItem==null){
                    return;
                }
                else{
                    btnSave.setText("Update");
                    btnSave.setDisable(false);
                    btnDelete.setDisable(false);
                    btnAddItem.setDisable(true);

                    txtItemID.setText(selectedItem.getItemId()+"");
                    txtDescription.setText(selectedItem.getDescription()+"");
                    txtUnitPrice.setText(selectedItem.getUnitPrice()+"");
                    txtQtyOnHand.setText(selectedItem.getQtyOnHand()+"");
                }
            }
        });
    }

    @SuppressWarnings("Duplicates")
    public void btnBack_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        ItemTM selectedItem = tblItemDetails.getSelectionModel().getSelectedItem();

        if(selectedItem==null){
            return;
        }
        itemBO.deleteItem(selectedItem.getItemId());
        loadAllItems();

        btnSave.setText("Save");
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        txtDescription.clear();
        txtItemID.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();

    }

    @SuppressWarnings("Duplicates")
    public void btnSave_OnAction(ActionEvent actionEvent) {
        //TODO validations
        String itemId = txtItemID.getText();
        String description = txtDescription.getText();
        double price = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        if(btnSave.getText().equals("Update")){
            itemBO.updateItem(itemId,description,price,qtyOnHand);
        }
        else{
            itemBO.saveItem(itemId,description,price,qtyOnHand);
        }
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        btnAddItem.setDisable(false);
        btnAddItem.requestFocus();
        txtItemID.clear();
        txtDescription.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        loadAllItems();
    }
    public void btnAdd_OnAction(ActionEvent actionEvent) {
        btnAddItem.setDisable(true);
        txtDescription.requestFocus();
        btnSave.setDisable(false);

        generateId();
    }

    public void loadAllItems(){
        tblItemDetails.getItems().clear();
        List<ItemTM> allItems = itemBO.getAllItems();
        ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList(allItems);
        tblItemDetails.setItems(itemTMS);
    }
    private void generateId(){
        String newItemId = itemBO.generateNewItemId();
        txtItemID.setText(newItemId);
    }

}
