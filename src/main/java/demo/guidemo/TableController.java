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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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

    /**
     * This method will change the scene to hello-view when the GoBacktoGUIObjectsView Button is pressed
     */
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/demo/guidemo/hello-view.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("GUI Objects View");
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
    }

    //Sample data
    private ObservableList<Person> getPeople() {
        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add(new Person("John", "Doe", LocalDate.of(1990, 1, 1)));
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
}
