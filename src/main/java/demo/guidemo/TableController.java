package demo.guidemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    //Configure the table
    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private TableColumn<Person, LocalDate> birthday;

    //Instance variables for new Person object
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private DatePicker birthdayDatePicker;

    //Access to the DetailedPersonButton
    @FXML private Button detailedPersonButton;

    /**
     * This method will change the scene to hello-view when the GoBacktoGUIObjectsView Button is pressed
     */
    public void changeSceneButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/demo/guidemo/hello-view.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("GUI Objects View");
        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * This method will pass the selected Person object to the PersonViewController when the DetailedPersonView Button is pressed
     */
    public void changeDetailedPersonButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/demo/guidemo/PersonView.fxml")); //set the loader to look at this file
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        PersonViewController personViewController = loader.getController(); //get the controller for the obtained file
        personViewController.initData(tableView.getSelectionModel().getSelectedItem()); //returns a Person object selected in the table

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Detailed Person View");
        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * This method will allow the user to double-click on a cell and update the
     * first name of the person
     */
    public void changeFirstNameCellEvent(TableColumn.CellEditEvent editedCell) {

        Person personSelected = tableView.getSelectionModel().getSelectedItem();

        personSelected.setFirstName(editedCell.getNewValue().toString());
    }

    /**
     * This method will allow the user to double-click on a cell and update the
     * last name of the person
     */
    public void changeLastNameCellEvent(TableColumn.CellEditEvent editedCell) {

        Person personSelected = tableView.getSelectionModel().getSelectedItem();

        personSelected.setLastName(editedCell.getNewValue().toString());
    }

    /**
     * This method will enable the detailed view button once a row in the table is selected
     */
    public void userClickedOnTable() {
        this.detailedPersonButton.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //This sets up our columns
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        birthday.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("birthday"));

        //Load sample data from sample method below
        tableView.setItems(getPeople());

        //Update the table to allow the first and last name fields editable
        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //This will allow the table to select multiple rows at once
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Disable the DetailedPersonButton until a row is selected (prevents errors)
        this.detailedPersonButton.setDisable(true);
    }

    //Sample data
    private ObservableList<Person> getPeople() {
        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add(new Person("John", "Adams", LocalDate.of(1735, 10, 30),
                new Image(getClass().getResource("/demo/guidemo/Images/john-adams-4905395_1280.png").toExternalForm())));
        people.add(new Person("Jane", "Doe", LocalDate.of(1991, 1, 1)));
        return people;
    }

    /*
     *  This method will create a new Person object and add it to the table
     */
    public void newPersonButtonPushed() {
        //Create a new Person
        Person newPerson = new Person(firstNameTextField.getText(),
                                        lastNameTextField.getText(),
                                        birthdayDatePicker.getValue());
        //Get all the items from the table as a list, then add the new person to the list
        tableView.getItems().add(newPerson);
    }

    /*
     * This method will remove the selected row(s) from the table
     */
    public void deleteButtonPushed() {
        ObservableList<Person> selectedRows, allPeople;
        allPeople = tableView.getItems();

        //this gives us the rows that are selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person object from the table
        for(Person person : selectedRows) {
            allPeople.remove(person);
        }
    }
}
