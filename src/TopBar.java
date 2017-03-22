import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TopBar {
    //Function to load top border
    void loadTopBar(BorderPane root, Button findYoungestButton, Button findOldestButton, Button findRichestButton, Button sortByNameButton, Label searchLabel, TextField filterField, HBox topBar) {
        //Set top bar layout
        findOldestButton.setMinWidth(120);
        findYoungestButton.setMinWidth(120);
        findRichestButton.setMinWidth(120);
        sortByNameButton.setMinWidth(120);
        searchLabel.setMinWidth(40);
        filterField.setPrefWidth(2000);

        //Setting up the top border
        topBar.setSpacing(5);
        topBar.setPadding(new Insets(10, 10, 0, 10));
        topBar.getChildren().addAll(findOldestButton, findYoungestButton, findRichestButton, sortByNameButton, searchLabel, filterField);

        //Placing it on the grid
        root.setTop(topBar);
    }
}
