import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.Comparator;

class Controller {
    private TableView<Person> table = new TableView<>();
    private TextField filterField = new TextField();
    private TextArea inputTextArea = new TextArea();
    private HBox topBar = new HBox();
    private Button findOldestButton = new Button("Find oldest");
    private Button findYoungestButton = new Button("Find youngest");
    private Button findRichestButton = new Button("Find richest");
    private Button sortByNameButton = new Button("Sort by first name");
    private String firstNames = "";

    private final ObservableList<Person> personData =
            FXCollections.observableArrayList(
                    new Person("Eric", "Andresen", "Student", 5, 26, "Blue sky enthusiast"),
                    new Person("Zlatan", "Ibrahimovic", "Football player", 137280000, 35, "Professional ball kicker"),
                    new Person("Carl XIV Gustaf", "Bernadotte", "King", 13400000, 70, "To role"),
                    new Person("Bob", "Dylan", "Singer-songwriter, artist, writer", 8330000, 75, "Singing, guitar, harmonica, keyboard "),
                    new Person("Joel", "Lundqvist", "Hockey player", 300000, 35, "Center, tackle Djurgården")
            );

    private FilteredList<Person> filteredData = new FilteredList<>(personData, p -> true);
    private SortedList<Person> sortedData = new SortedList<>(filteredData);

    void loadTable(GridPane gridPane) {

        //First name column
        TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        //Last name column
        TableColumn<Person, String> lastNameCol = new TableColumn<Person, String>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        //Profession column
        TableColumn<Person, String> professionCol = new TableColumn<Person, String>("Profession");
        professionCol.setMinWidth(150);
        professionCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("profession"));

        //wage column
        TableColumn<Person, Integer> wageCol = new TableColumn<Person, Integer>("wage");
        wageCol.setMinWidth(100);
        wageCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("wage"));

        //Age column
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(70);
        ageCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("age"));

        //Skill column
        TableColumn<Person, String> skillsCol = new TableColumn<>("Skills");
        skillsCol.setMinWidth(220);
        skillsCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("skills"));

        //Tern off column sorting in UI
        firstNameCol.setSortable(false);
        lastNameCol.setSortable(false);
        professionCol.setSortable(false);
        wageCol.setSortable(false);
        ageCol.setSortable(false);
        skillsCol.setSortable(false);

        //Filtering from text input

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (person.getProfession().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //Filter matches profession
                } else if (String.valueOf(person.getAge()).contains(lowerCaseFilter)) {
                    return true; //Filter matches wage
                } else if (String.valueOf(person.getWage()).contains(lowerCaseFilter)) {
                    return true; //Filter matches age
                } else if (person.getSkills().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //Filter matches skills
                }
                return false; // Does not match.
            });
            //Filter list and putting it to table.
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });

        //Sort people by name, store them and print to inputTextArea.
        sortByNameButton.setOnAction((ActionEvent event) -> {
            personData.sort(Comparator.comparing(Person::getFirstName));
            table.setItems(sortedData);

            sortedData.forEach((Person Person) -> firstNames += Person.getFirstName() + "\n");
            inputTextArea.setText(firstNames);
            firstNames = "";
        });

        //Finds oldest pearson
        findOldestButton.setOnAction(event -> {
            SortedList<Person> sortedByAgeOld = new SortedList<Person>(sortedData, (Person age1, Person age2) -> {
                if (age1.getAge() < age2.getAge()) {
                    return 1;
                } else if (age1.getAge() > age2.getAge()) {
                    return -1;
                } else {
                    return 0;
                }
            });
            table.setItems(sortedByAgeOld);

            String oldestPearsonFirstName = firstNameCol.getCellData(0);
            String oldestPearsonLastName = lastNameCol.getCellData(0);
            inputTextArea.setText("Oldest pearson is: " + oldestPearsonFirstName + " " + oldestPearsonLastName);
        });

        //Finds youngest pearson
        findYoungestButton.setOnAction(event -> {
            SortedList<Person> sortedByAgeYoung = new SortedList<Person>(sortedData, (Person age1, Person age2) -> {
                if (age1.getAge() > age2.getAge()) {
                    return 1;
                } else if (age1.getAge() < age2.getAge()) {
                    return -1;
                } else {
                    return 0;
                }
            });
            table.setItems(sortedByAgeYoung);

            String youngestPearsonFirstName = firstNameCol.getCellData(0);
            String youngestPearsonLastName = lastNameCol.getCellData(0);
            inputTextArea.setText("Youngest pearson is: " + youngestPearsonFirstName + " " + youngestPearsonLastName);
        });

        //Finds richest pearson
        findRichestButton.setOnAction(event -> {
            SortedList<Person> sortedByWage = new SortedList<Person>(sortedData, (Person wage1, Person wage2) -> {
                if (wage1.getWage() < wage2.getWage()) {
                    return 1;
                } else if (wage1.getWage() > wage2.getWage()) {
                    return -1;
                } else {
                    return 0;
                }
            });
            table.setItems(sortedByWage);

            String richestPearsonFirstName = firstNameCol.getCellData(0);
            String richestPearsonLastName = lastNameCol.getCellData(0);
            inputTextArea.setText("Richest pearson is: " + richestPearsonFirstName + " " + richestPearsonLastName);
        });

        //Collecting nodes and placing the table on the grid
        table.getColumns().addAll(firstNameCol, lastNameCol, professionCol, wageCol, ageCol, skillsCol);
        table.setMaxHeight(250);
        table.setItems(personData);
        inputTextArea.setFont(Font.font(15));

        //Add nodes to the grid
        gridPane.add(table, 0, 1);
        gridPane.add(filterField, 0, 0);
        gridPane.add(inputTextArea, 0, 2);
    }

    //Function to load top border
    void loadTopBar(BorderPane root) {
        //Setting up the top border
        topBar.setSpacing(5);
        topBar.setPadding(new Insets(10, 10, 0, 10));
        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(findOldestButton, findYoungestButton, findRichestButton, sortByNameButton);

        //Placing it on the grid
        root.setTop(topBar);
    }
}
