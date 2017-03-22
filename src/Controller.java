import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

class Controller {
    private Sort sort = new Sort();
    private TopBar topBarClass = new TopBar();
    private Table table = new Table();

    private TextField filterField = new TextField();
    private TextArea inputTextArea = new TextArea();
    private HBox topBar = new HBox();
    private Label searchLabel = new Label("Search:");
    private Button findOldestButton = new Button("Find oldest");
    private Button findYoungestButton = new Button("Find youngest");
    private Button findRichestButton = new Button("Find richest");
    private Button sortByNameButton = new Button("Sort by first name");

    //Create list to hold Person's and fill with data
    private final ObservableList<Person> personData = FXCollections.observableArrayList(
            new Person("Eric", "Andresen", "Student", 5000, 26, "Blue sky enthusiast"),
            new Person("Zlatan", "Ibrahimovic", "Football player", 137280000, 35, "Professional ball kicker"),
            new Person("Carl XIV Gustaf", "Bernadotte", "King", 13400000, 70, "To role"),
            new Person("Bob", "Dylan", "Singer-songwriter, artist, writer", 8330000, 75, "Singing, guitar, harmonica, keyboard "),
            new Person("Joel", "Lundqvist", "Hockey player", 300000, 35, "Center, tackle Djurgården")
    );

    private FilteredList<Person> filteredData = new FilteredList<>(personData, p -> true);
    private SortedList<Person> sortedData = new SortedList<>(filteredData);

    void loadContent(GridPane gridPane, BorderPane root) {
        //Load table and implement sort function
        table.loadTable(personData, inputTextArea, gridPane, filterField, filteredData, sortedData);

        //Sort people by name, store them and print to inputTextArea.
        sortByNameButton.setOnAction(event -> sort.sortByFirstName(personData, sortedData, inputTextArea));

        //Finds oldest pearson
        findOldestButton.setOnAction(event -> sort.findOldest(personData, sortedData, inputTextArea));


        //Finds youngest pearson
        findYoungestButton.setOnAction(event -> sort.findYoungest(personData, sortedData, inputTextArea));

        //Find richest pearson
        findRichestButton.setOnAction(event -> sort.findRichest(personData, sortedData, inputTextArea));


        //Load top bar
        topBarClass.loadTopBar(root, findYoungestButton, findOldestButton, findRichestButton, sortByNameButton, searchLabel, filterField, topBar);

        //Add nodes to the grid
        gridPane.add(inputTextArea, 0, 2);
    }
}
