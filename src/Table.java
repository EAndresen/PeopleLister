import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class Table {
    private TableView<Person> table = new TableView<>();

    public void loadTable(ObservableList<Person> personData, TextArea inputTextArea, GridPane gridPane, TextField filterField, FilteredList<Person> filteredData, SortedList<Person> sortedData) {
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

        //Collecting nodes and placing the table on the grid
        table.getColumns().addAll(firstNameCol, lastNameCol, professionCol, wageCol, ageCol, skillsCol);
        table.setMaxHeight(250);
        table.setItems(personData);
        inputTextArea.setFont(Font.font(15));

        //Add table to grid
        gridPane.add(table, 0, 1);

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
    }
}
