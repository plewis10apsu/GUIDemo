package demo.guidemo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    //These items are for the CheckBox example
    @FXML private Label pizzaOrderLabel;
    @FXML private CheckBox pepperoniCheckBox;
    @FXML private CheckBox pineappleCheckBox;
    @FXML private CheckBox baconCheckBox;

    //These items are for the ChoiceBox example
    @FXML private ChoiceBox choiceBox;
    @FXML private Label choiceBoxLabel;

    //These items are for the ComboBox example
    @FXML private ComboBox comboBox;
    @FXML private Label comboBoxLabel;

    //These items are for the RadioButton Objects
    @FXML private RadioButton radioPHP;
    @FXML private RadioButton radioJava;
    @FXML private RadioButton radioCSharp;
    @FXML private RadioButton radioCPlus;
    @FXML private Label radioLabel;
    private ToggleGroup favLangToggleGroup;

    //These items are for the ListView and the TextArea example
    @FXML private ListView listView;
    @FXML private TextArea golfTextArea;
    @FXML private Button golfButton;

    /**
     * This will update the Label for the ChoiceBox
     */
    public void choiceBoxButtonPushed() {
        choiceBoxLabel.setText("My favorite fruit is: \n" + choiceBox.getValue().toString());
    }


    public void pizzaOrderButtonPushed() {
        String order = "Toppings are:";
        if (pepperoniCheckBox.isSelected()) {
            order += "\nPepperoni";
        }
        if (pineappleCheckBox.isSelected()) {
            order += "\nPineapple";
        }
        if (baconCheckBox.isSelected()) {
            order += "\nBacon";
        }
        pizzaOrderLabel.setText(order);
    }

    /**
     * This will update the comboBoxLabel when the ComboBox is changed
     */
    public void comboBoxWasUpdated() {
        this.comboBoxLabel.setText("Course selected: \n" + comboBox.getValue().toString());
    }

    /**
     * This will update the radioButtonLabel when a different radio button is pushed
     */
    public void radioButtonChanged() {
        if (favLangToggleGroup.getSelectedToggle().equals(radioPHP)) {
            radioLabel.setText("The favorite language is PHP");
        } else if (favLangToggleGroup.getSelectedToggle().equals(radioJava)) {
            radioLabel.setText("The favorite language is Java");
        } else if (favLangToggleGroup.getSelectedToggle().equals(radioCSharp)) {
            radioLabel.setText("The favorite language is C#");
        } else if (favLangToggleGroup.getSelectedToggle().equals(radioCPlus)) {
            radioLabel.setText("The favorite language is C++");
        }
    }

    /**
     * This method will copy the Strings from the ListView and put them in the TextArea
     */
    public void listViewButtonPushed() {
        String textAreaString = "";
        //Return an observable list to build a string
        ObservableList listOfItems = listView.getSelectionModel().getSelectedItems();

        for (Object item : listOfItems) {
            textAreaString += item.toString() + "\n";
        }
        golfTextArea.setText(textAreaString);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //This item is for configuring the CheckBox Label
        pizzaOrderLabel.setText("");
        //These items are for configuring the ChoiceBox example
        choiceBoxLabel.setText("");
        choiceBox.getItems().add("apples");
        choiceBox.getItems().add("bananas");
        choiceBox.getItems().addAll("oranges", "pears", "strawberrires");
        choiceBox.setValue("apples");
        //These items are for configuring the ComboBox
        comboBoxLabel.setText("");
        comboBox.getItems().addAll("CSCI 4100", "CSCI 4101", "CSCI 4102", "CSCI 4103");
        //These items are for configuring the RadioButtons
        radioLabel.setText("");
        favLangToggleGroup = new ToggleGroup();
        this.radioPHP.setToggleGroup(favLangToggleGroup);
        this.radioJava.setToggleGroup(favLangToggleGroup);
        this.radioCSharp.setToggleGroup(favLangToggleGroup);
        this.radioCPlus.setToggleGroup(favLangToggleGroup);
        //These items are for configuring the ListView
        listView.getItems().add("Golf Balls");
        listView.getItems().addAll("Wedges", "Irons", "Tees", "Driver", "Putter");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
