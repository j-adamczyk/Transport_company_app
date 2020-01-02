package app.presenter.selectedPresenter;

import app.command.DriverDeleteCommand;
import app.dao.DriverDAO;
import app.model.Driver;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SelectedDriverPresenter extends SelectedPresenter{

    private ObservableList<Driver> drivers;
    private ObjectProperty<Driver> selectedDriverProperty = new SimpleObjectProperty<>();

    @FXML
    private TableView<Driver> driverTableView;
    @FXML
    private TableColumn<Driver, String> driverName;
    @FXML
    private TableColumn<Driver, String> driverBirthDay;
    @FXML
    private TableColumn<Driver, String> driverHireDate;
    @FXML
    private TableColumn<Driver, String> driverPhone;
    @FXML
    private TableColumn<Driver, String> driverAddress;
    @FXML
    private TableColumn<Driver, String> driverSalary;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Label returnLabel;

    @FXML
    private void initialize(){
        DriverDAO driverDAO = new DriverDAO();
        driverTableView.getSelectionModel().getTableView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        driverName.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getName()));
        driverBirthDay.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getBirthDate().toString()));
        driverHireDate.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getHireDate().toString()));
        driverAddress.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getAddress().toString()));
        driverPhone.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getPhone()));
        driverSalary.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getSalary().toString()));
        this.drivers = FXCollections.observableArrayList();
        drivers.addAll(driverDAO.findAllDrivers());
        driverTableView.setItems(drivers);

        editButton.disableProperty().bind(
                Bindings.size(
                        driverTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        deleteButton.disableProperty().bind(
                Bindings.size(
                        driverTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));

        driverTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                selectedDriverProperty.set(nv);
            }
        });
    }

    @Override
    public void setOldObject(Object object){
        Driver selectedDriver = (Driver) object;
        driverTableView.getSelectionModel().select(selectedDriver);
    }

    @Override
    public Driver getSelectedObject(){
        return this.selectedDriverProperty.get();
    }
    @FXML
    private void handleAddButtonAction(){
        Driver addedDriver = appPresenter.showAddDriverView();
        if(addedDriver!=null) {
            drivers.add(addedDriver);
            driverTableView.refresh();
        }
    }
    @FXML
    private void handleDeleteButtonAction(){
        Driver toRemove = driverTableView.getSelectionModel().getSelectedItem();
        DriverDeleteCommand ddc = new DriverDeleteCommand(toRemove.get_id());
        ddc.execute();
        drivers.remove(toRemove);
        driverTableView.refresh();
    }
    @FXML
    private void handleEditButtonAction(){
        appPresenter.showEditDriverView(driverTableView.getSelectionModel().getSelectedItem());
        driverTableView.refresh();
    }

}
