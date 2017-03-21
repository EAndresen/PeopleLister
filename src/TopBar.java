import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

class TopBar {
    private HBox topBar = new HBox();
    private PeopleTableView peopleTableView = new PeopleTableView();

    private Button findOldestButton = new Button("Find oldest");
    private Button findYoungestButton = new Button("Find youngest");
    private Button findRichestButton = new Button("Find richest");
    private Button sortByNameButton = new Button("Sort by first name");

    void loadTopBar(BorderPane root) {
        topBar.setSpacing(5);
        topBar.setPadding(new Insets(10, 10, 10, 10));
        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(findOldestButton, findYoungestButton, findRichestButton, sortByNameButton);

        root.setTop(topBar);
    }

}
