
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;


class PeopleTableView {
    public TableView<Person> table = new TableView<>();
    private TextField filterField = new TextField();
    private TextArea textArea = new TextArea();

    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("Eric", "Andresen", "Student", "5", "26", "Blue sky enthusiast"),
                    new Person("Zlatan", "Ibrahimovic", "Football player", "137280000", "35", "Professional ball kicker"),
                    new Person("Carl XIV Gustaf", "Bernadotte", "King", "13400000", "70", "To role"),
                    new Person("Bob", "Dylan", "Singer-songwriter, artist, writer", "8330000", "75", "Singing, guitar, harmonica, keyboard "),
                    new Person("Joel", "Lundqvist", "Hockey player", "300000", "35", "Center, tackle Djurgården")

            );

    public void loadTable(GridPane gridPane) {
        table.setEditable(true);

        TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn<Person, String> lastNameCol = new TableColumn<Person, String>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn<Person, String> professionCol = new TableColumn<Person, String>("Profession");
        professionCol.setMinWidth(150);
        professionCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("profession"));

        TableColumn<Person, Integer> wageCol = new TableColumn<Person, Integer>("wage");
        wageCol.setMinWidth(100);
        wageCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("wage"));

        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(70);
        ageCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("age"));

        TableColumn<Person, String> skillsCol = new TableColumn<>("Skills");
        skillsCol.setMinWidth(220);
        skillsCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("skills"));

        FilteredList<Person> filteredData = new FilteredList<>(data, p -> true);

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
                } else if (person.getWage().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //Filter matches wage
                } else if (person.getAge().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //Filter matches age
                } else if (person.getSkills().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //Filter matches skills
                }
                return false; // Does not match.
            });
        });

        SortedList<Person> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
        table.getColumns().addAll(firstNameCol, lastNameCol, professionCol, wageCol, ageCol, skillsCol);

        gridPane.add(table, 0, 1);
        gridPane.add(filterField, 0, 0);
        gridPane.add(textArea, 0, 2);
    }
}
