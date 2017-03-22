import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextArea;

import java.util.Comparator;

public class Sort {
    private String firstNames = "";
    private int ageNumber, wageNumber;
    private String oldestPearsonFirstName;
    private String oldestPearsonLastName;

    //Finds richest pearson
    void findRichest(ObservableList<Person> personData, SortedList<Person> sortedData, TextArea inputTextArea) {
        personData.sort(Comparator.comparing(Person::getWage));

        sortedData.forEach((Person Person) -> {
            wageNumber = Person.getWage();
            oldestPearsonFirstName = Person.getFirstName();
            oldestPearsonLastName = Person.getLastName();
        });
        inputTextArea.setText(oldestPearsonFirstName + " " + oldestPearsonLastName + " have highest income: " + wageNumber + " SEK/month");
        }

    //Finds youngest pearson
    void findYoungest(ObservableList<Person> personData, SortedList<Person> sortedData, TextArea inputTextArea) {
        personData.sort(Comparator.comparing(Person::getAge).reversed());

        sortedData.forEach((Person Person) -> {
            ageNumber = Person.getAge();
            oldestPearsonFirstName = Person.getFirstName();
            oldestPearsonLastName = Person.getLastName();
        });
        inputTextArea.setText(oldestPearsonFirstName + " " + oldestPearsonLastName + " is youngest and : " + ageNumber + " years old.");
        }

    //Finds oldest pearson
    void findOldest(ObservableList<Person> personData, SortedList<Person> sortedData, TextArea inputTextArea) {
        personData.sort(Comparator.comparing(Person::getAge));

        sortedData.forEach((Person Person) -> {
            ageNumber = Person.getAge();
            oldestPearsonFirstName = Person.getFirstName();
            oldestPearsonLastName = Person.getLastName();
        });
        inputTextArea.setText(oldestPearsonFirstName + " " + oldestPearsonLastName + " is oldest and: " + ageNumber + " years old.");
    }

    //Sort people by first name
    void sortByFirstName(ObservableList<Person> personData, SortedList<Person> sortedData, TextArea inputTextArea) {
        personData.sort(Comparator.comparing(Person::getFirstName));

        sortedData.forEach((Person Person) -> firstNames += Person.getFirstName() + "\n");
        inputTextArea.setText(firstNames);
        firstNames = "";
    }
}

