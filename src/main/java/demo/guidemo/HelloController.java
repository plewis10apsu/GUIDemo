package demo.guidemo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

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

    /*
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pizzaOrderLabel.setText("");
        //These items are for configuring the ChoiceBox example
        choiceBoxLabel.setText("");
        choiceBox.getItems().add("apples");
        choiceBox.getItems().add("bananas");
        choiceBox.getItems().addAll("oranges", "pears", "strawberrires");
        choiceBox.setValue("apples");
    }
}
