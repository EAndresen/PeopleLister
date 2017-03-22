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
import javafx.scene.text.TextAlignment;

import java.util.Comparator;

class Controller {
    private Sort sort = new Sort();
    private TopBar topBarClass = new TopBar();

    private TableView<Person> table = new TableView<>();
    private TextField filterField = new TextField();
    private TextArea inputTextArea = new TextArea();
    private HBox topBar = new HBox();
    private Label searchLabel = new Label("Search:");
    private Button findOldestButton = new Button("Find oldest");
    private Button findYoungestButton = new Button("Find youngest");
    private Button findRichestButton = new Button("Find richest");
    private Button sortByNameButton = new Button("Sort by first name");

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



    void loadTable(GridPane gridPane, BorderPane root) {

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
        TableColumn<Person, Integer> wageCol = new TableColumn<Person, Integer>("Wage");
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
        sortByNameButton.setOnAction(event -> sort.sortByFirstName(personData, table, sortedData, inputTextArea));

        //Finds oldest pearson
        findOldestButton.setOnAction(event -> sort.findOldest(personData, sortedData, inputTextArea));


        //Finds youngest pearson
        findYoungestButton.setOnAction(event -> sort.findYoungest(personData, sortedData, firstNameCol, lastNameCol, inputTextArea));

        //Find richest pearson
        findRichestButton.setOnAction(event -> sort.findRichest(personData, sortedData, firstNameCol, lastNameCol, inputTextArea));

        //Collecting nodes and placing the table on the grid
        table.getColumns().addAll(firstNameCol, lastNameCol, professionCol, wageCol, ageCol, skillsCol);
        table.setMaxHeight(250);
        table.setItems(personData);
        inputTextArea.setFont(Font.font(15));

        //Load top bar
        topBarClass.loadTopBar(root, findYoungestButton, findOldestButton, findRichestButton, sortByNameButton, searchLabel, filterField, topBar);

        //Add nodes to the grid
        gridPane.add(table, 0, 1);
        gridPane.add(inputTextArea, 0, 2);
    }
}
