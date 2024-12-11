package demo.guidemo;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonViewController implements Initializable {

    private Person selectedPerson;

    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label birthdayLabel;
    @FXML private Label ageLabel;
    @FXML private ImageView photo;
    private FileChooser fileChooser;
    private File filePath;

    /*
     * This method accepts a person to initialize the view
     */
    public void initData(Person person) {
        selectedPerson = person;
        firstNameLabel.setText(person.getFirstName());
        lastNameLabel.setText(person.getLastName());
        birthdayLabel.setText(person.getBirthday().toString());
        ageLabel.setText(Integer.toString(selectedPerson.getAge()));
        photo.setImage(selectedPerson.getPhoto());
    }

    /**
     * This method will change the scene to TableView when the Back Button is pressed
     */
    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/demo/guidemo/TableViewExample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Table View");
        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * This method will allow the user to change the image on the screen
     * To get the Parent of this window, we will need to trigger an ActionEvent
     */
    public void changeImageButtonPushed(ActionEvent event) throws IOException {
        // Go to the event in our window ->  get the Source -> get the Scene -> get the Window
        // The way the initial program launches, the Stage is passed in from the start method,
        // in this case, we need to get that Stage. (Window == Stage)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        /*
        //Open user's C: Drive
        this.filePath = fileChooser.showOpenDialog(stage);
        */

        //Open a user's specific directory
        String userDirectoryString = System.getProperty("user.home") + File.separator + "Pictures"; //Set up user directory based on user home
        File userDirectory = new File(userDirectoryString);

        // If the directory doesn't exist, isn't readable, or isn't a directory
        if (!userDirectory.exists() || !userDirectory.canRead() || !userDirectory.isDirectory()) {
            userDirectory = new File(System.getProperty("user.home")); //default to user's home
        }
        fileChooser.setInitialDirectory(userDirectory);
        this.filePath = fileChooser.showOpenDialog(stage);

        //Try to update the image by loading the new image
        try{
            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            selectedPerson.setPhoto(image);
            photo.setImage(selectedPerson.getPhoto());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
