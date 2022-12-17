package org.Menu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import org.fitsay.tourist_trips.Vouchers.Offers;
import org.fitsay.tourist_trips.Orders.Order;
import org.fitsay.tourist_trips.Orders.Orders;
import org.fitsay.tourist_trips.Vouchers.TransportType;
import org.fitsay.tourist_trips.Vouchers.Voucher;
import org.fitsay.tourist_trips.Vouchers.VoucherType;
import java.io.IOException;
import java.sql.*;
import javafx.event.ActionEvent;
import org.fitsay.tourist_trips.Database.DatabaseHandler;

public class Controller extends  DatabaseHandler{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label PRICE;
    @FXML
    private ListView<Voucher> offers = new ListView<>();
    @FXML
    private ListView<Order> orders = new ListView<>();
    @FXML
    TextField field1,field2,field3,field4,field5,field6,field7;
    @FXML
    Label label1,label2,label3,label4,label5,label6,label7;
    final Offers Offers = new Offers();
    final Orders Orders = new Orders();
    @FXML
    protected void GetPRICEOffers(){
        PRICE.setText("Price: "+offers.getSelectionModel().getSelectedItem().getPrice());
    }
    @FXML
    protected void GETOFFER(){
        label1.setText("Type Voucher: "+offers.getSelectionModel().getSelectedItem().getType().name());
        label2.setText("Country: "+offers.getSelectionModel().getSelectedItem().getCountry());
        label3.setText("Hotel: "+offers.getSelectionModel().getSelectedItem().getHotel());
        label4.setText("Date: "+offers.getSelectionModel().getSelectedItem().getDate());
        label5.setText("Type Transport: "+offers.getSelectionModel().getSelectedItem().getTransport().name());
        label6.setText("Duration: "+offers.getSelectionModel().getSelectedItem().getDuration()+" day");
        label7.setText("Price: "+offers.getSelectionModel().getSelectedItem().getPrice());
    }
    @FXML
    protected void GETORDER(){
        label1.setText("Name Customer: "+orders.getSelectionModel().getSelectedItem().getNameCustomer());
        label2.setText("Phone Number: "+orders.getSelectionModel().getSelectedItem().getPhoneNumber());
        label3.setText("Departure Date: "+orders.getSelectionModel().getSelectedItem().getDepartureDate());
        label4.setText("Voucher Name: "+getVoucherName(orders.getSelectionModel().getSelectedItem().getOfferID()));
        label5.setText("Custom Duration: " + orders.getSelectionModel().getSelectedItem().getCustomDuration()+" day");
        label6.setText("Custom Transport: " + orders.getSelectionModel().getSelectedItem().getCustomTransport().name());
        label7.setText("Price: "+ orders.getSelectionModel().getSelectedItem().getPrice());
    }
    protected String getVoucherName(int ind){
        String str;
        try {
            rs = stm.executeQuery("select * from Offers WHERE OfferID = '" +ind + "'");
            rs.next();
            str = rs.getString(2);
        } catch (SQLException e) {
            return null;
        }
        return str;
    }
    @FXML
    protected void ShowOffers() {
        GetOffers();
        offers.getItems().clear();
        for(Voucher x : Offers.getVouchers()){
            offers.getItems().add(x);
        }
    }
    @FXML
    protected void ShowOffersSortByCost() {
        GetOffers();
        offers.getItems().clear();
        Offers.SortByCost();
        for(Voucher x : Offers.getVouchers()){
            offers.getItems().add(x);
        }
    }
    @FXML
    protected void ShowOffersSortByCountry() {
        GetOffers();
        offers.getItems().clear();
        Offers.SortByCountry();
        for(Voucher x : Offers.getVouchers()){
            offers.getItems().add(x);
        }
    }
    @FXML
    protected void ShowOffersSortByName() {
        GetOffers();
        offers.getItems().clear();
        Offers.SortByName();
        for(Voucher x : Offers.getVouchers()){
            offers.getItems().add(x);
        }
    }
    @FXML
    protected void GetOffers(){
        Offers.getVouchers().clear();
        try {
            rs = stm.executeQuery("select * from Offers");
            while(rs.next()){
                Offers.getVouchers().add(new Voucher(rs.getInt(1), rs.getString(2), VoucherType.getTypeByInd(rs.getInt(3)), rs.getString(4),rs.getString(5),rs.getDate(6),TransportType.getTypeByInd(rs.getInt(7)), rs.getInt(8),rs.getDouble(9)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void DeleteOffer(){
        try {
            rs2 = stm.execute("DELETE FROM Offers WHERE OfferID = '"+ offers.getSelectionModel().getSelectedItem().getID() +"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ShowOffers();
    }
    @FXML
    protected void AddVoucher(){
        String name = field1.getText();
        String type = field2.getText();
        String count = field3.getText();
        String hotel = field4.getText();
        String date = field5.getText();
        String transport = field6.getText();
        String duration = field7.getText();
        if((name.equals("") || name.equals(" "))||((type.charAt(0))>'5'||type.length()!=1)||((transport.charAt(0))>'3'||transport.length()!=1)||((duration.charAt(0))>'7'||duration.length()!=1)){
            PRICE.setText("Неправильне значення");
            return;
        }
        try {
            rs2 = stm.execute("INSERT INTO Offers VALUES ('" + name + "','"+type+"','"+count+"','"+hotel+"','"+ date +"','"+transport+"','"+duration+"')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            rs = stm.executeQuery("select * from Offers where VoucherName = '"+field1.getText()+"' ");
            rs.next();
            PRICE.setText("Price = "+rs.getString(9));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        field1.clear();
        field2.clear();
        field3.clear();
        field4.clear();
        field5.clear();
        field6.clear();
        field7.clear();
    }
    @FXML
    protected void ShowOrders() {
        GetOrders();
        orders.getItems().clear();
        for(Order x : Orders.getOrders()){
            orders.getItems().add(x);
        }
    }
    @FXML
    protected void ShowOrdersSortByNameCusmomer() {
        GetOrders();
        orders.getItems().clear();
        Orders.SortByNameCustomer();
        for(Order x : Orders.getOrders()){
            orders.getItems().add(x);
        }
    }
    protected void GetOrders(){
        Orders.getOrders().clear();
        try {
            rs = stm.executeQuery("select * from Orders");
            while(rs.next()){
                Orders.getOrders().add(new Order(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getDate(4),rs.getInt(5),rs.getInt(6),TransportType.getTypeByInd(rs.getInt(7)),rs.getDouble(8)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void DeleteOrder(){
        try {
            rs2 = stm.execute("DELETE FROM Orders WHERE OrderID= '"+ orders.getSelectionModel().getSelectedItem().getOrderID()+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ShowOrders();
    }
    @FXML
    protected void AddOrder(){
        String NameCustomer = field1.getText();
        String PhoneNumber = field2.getText();
        String date = field3.getText();
        String OfferID = field4.getText();
        String CustomDuration = field5.getText();
        String CustomTransport = field6.getText();
        if((NameCustomer.equals("") || NameCustomer.equals(" "))||((CustomDuration.charAt(0))>'7'||CustomDuration.length()!=1)||((CustomTransport.charAt(0))>'3'||CustomTransport.length()!=1)||(getVoucherName(Character.getNumericValue(OfferID.charAt(0)))==null)){
            PRICE.setText("Неправильне значення");
            return;
        }
        try {
            rs2 = stm.execute("INSERT INTO Orders VALUES ('" + NameCustomer + "','"+PhoneNumber+"','"+date+"','"+OfferID+"','"+ CustomDuration +"','"+CustomTransport+"')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            rs = stm.executeQuery("select Price from Orders where NameCustomer = '"+field1.getText()+"' ");
            rs.next();
            PRICE.setText("Price = "+rs.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        field1.clear();
        field2.clear();
        field3.clear();
        field4.clear();
        field5.clear();
        field6.clear();
    }
    @FXML
    public void SwitchToSceneAddVoucher(ActionEvent actionEvent) {
        root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AddOffer.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene = new Scene(root, 544 ,326);
        stage.setTitle("Add Voucher");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitchToSceneAddOrder(ActionEvent actionEvent) {
        root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AddOrder.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene = new Scene(root, 676, 341);
        stage.setTitle("Add Order");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitchToSceneMain(ActionEvent actionEvent) {
        root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene = new Scene(root, 582, 404);
        stage.setTitle("Tourist Trips");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitchToSceneDeleteOffer(ActionEvent actionEvent) {
        root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("DeleteOffer.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene = new Scene(root, 515, 387);
        stage.setTitle("Delete Offer");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitchToSceneDeleteOrder(ActionEvent actionEvent) {
        root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("DeleteOrder.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene = new Scene(root, 515, 387);
        stage.setTitle("Delete Order");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitchToSceneShowOrders(ActionEvent actionEvent) {
        root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ShowOrders.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene = new Scene(root, 515, 387);
        stage.setTitle("Show Order");
        stage.setScene(scene);
        stage.show();
    }
}