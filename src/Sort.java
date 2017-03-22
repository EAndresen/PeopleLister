import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.util.Comparator;

public class Sort {
    private String firstNames = "";
    private int ageNumber, wageNumber;
    private String oldestPearsonFirstName;
    private String oldestPearsonLastName;

    //Finds richest pearson
    void findRichest(ObservableList<Person> personData, SortedList<Person> sortedData, TableColumn<Person, String> firstNameCol, TableColumn<Person, String> lastNameCol, TextArea inputTextArea) {
        personData.sort(Comparator.comparing(Person::getWage));

        sortedData.forEach((Person Person) -> {
            wageNumber = Person.getWage();
            oldestPearsonFirstName = Person.getFirstName();
            oldestPearsonLastName = Person.getLastName();
        });
        inputTextArea.setText(oldestPearsonFirstName + " " + oldestPearsonLastName + " wage: " + wageNumber + "");
        }

    //Finds youngest pearson
    void findYoungest(ObservableList<Person> personData, SortedList<Person> sortedData, TableColumn<Person, String> firstNameCol, TableColumn<Person, String> lastNameCol, TextArea inputTextArea) {
        personData.sort(Comparator.comparing(Person::getAge).reversed());

        sortedData.forEach((Person Person) -> {
            ageNumber = Person.getAge();
            oldestPearsonFirstName = Person.getFirstName();
            oldestPearsonLastName = Person.getLastName();
        });
        inputTextArea.setText(oldestPearsonFirstName + " " + oldestPearsonLastName + " is: " + ageNumber + "");
        }

    //Finds oldest pearson
    void findOldest(ObservableList<Person> personData, SortedList<Person> sortedData, TextArea inputTextArea) {
        personData.sort(Comparator.comparing(Person::getAge));

        sortedData.forEach((Person Person) -> {
            ageNumber = Person.getAge();
            oldestPearsonFirstName = Person.getFirstName();
            oldestPearsonLastName = Person.getLastName();
        });
        inputTextArea.setText(oldestPearsonFirstName + " " + oldestPearsonLastName + " is: " + ageNumber + "");
    }

    void sortByFirstName(ObservableList<Person> personData, TableView<Person> table, SortedList<Person> sortedData, TextArea inputTextArea) {
        personData.sort(Comparator.comparing(Person::getFirstName));

        sortedData.forEach((Person Person) -> firstNames += Person.getFirstName() + "\n");
        inputTextArea.setText(firstNames);
        firstNames = "";
    }
}

