package controller;

import business.BOFactory;
import business.BOType;
import business.custom.OrderBO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.SearchOrderTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchOrderFormController {
    public TableView<SearchOrderTM> tblOrders;
    public TextField txtSearch;
    public AnchorPane root;
    public Button btnBack;
    public static ArrayList<SearchOrderTM> ordersArray = new ArrayList<>();
    private static OrderBO orderBO = BOFactory.getInstance().getBO(BOType.ORDER);

    public void initialize(){
        //map columns
        tblOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        //TODO:resolve errors in the getOrderDetails method in QueryDAO
        loadTable();

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList<SearchOrderTM> orders = tblOrders.getItems();
                orders.clear();

                for (SearchOrderTM order :ordersArray){
                    if(order.getOrderId().contains(newValue)||order.getCustomerId().contains(newValue)||order.getCustomerName().contains(newValue)||order.getDate().contains(newValue)){
                        orders.add(order);
                    }
                }
            }
        });
    }

    @SuppressWarnings("Duplicates")
    public void btnBack_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
        Scene mainscene = new Scene(root);
        Stage mainStage= (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainscene);
        mainStage.centerOnScreen();
        mainStage.setResizable(false);
    }

    public void txtSearch(ActionEvent actionEvent) {
    }

    private void loadTable(){
            ObservableList<SearchOrderTM> items = tblOrders.getItems();
            items.clear();
            List<SearchOrderTM> orderDetails = orderBO.getOrderDetails();
            ObservableList<SearchOrderTM> searchOrderTMS= FXCollections.observableArrayList(orderDetails);
            tblOrders.setItems(searchOrderTMS);
    }

    public void tblOrders_OnMouseClicked(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        if (tblOrders.getSelectionModel().getSelectedItem() == null){
            return;
        }
        if (mouseEvent.getClickCount() == 2){
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/OrderDetailsForm.fxml"));
            Parent root = fxmlLoader.load();
            OrderDetailsFormController controller = (OrderDetailsFormController) fxmlLoader.getController();
//            controller.initializeWithSearchOrderForm(tblOrders.getSelectionModel().getSelectedItem().getOrderId());
            Scene orderScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(orderScene);
            stage.centerOnScreen();
            stage.show();
        }
    }
}
