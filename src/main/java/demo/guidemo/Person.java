// Image Credit: <a href="https://www.freepik.com/free-psd/3d-icon-social-media-app_36190320.htm#fromView=search&page=1&position=10&uuid=144bd536-32ed-43c3-94cb-9513c0d53856">Image by freepik</a>

package demo.guidemo;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.time.LocalDate;
import java.time.Period;

public class Person {
    private SimpleStringProperty firstName, lastName;
    private LocalDate birthday;
    private Image photo;

    //Default image
    public Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        this.photo = new Image(getClass().getResource("/demo/guidemo/Images/8380015.jpg").toExternalForm());
    }
    //Depending on UI, user could scroll through local machine and upload image
    public Person(String firstName, String lastName, LocalDate birthday, Image photo) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        this.photo = photo;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

}
