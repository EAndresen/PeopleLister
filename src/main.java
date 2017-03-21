import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class main extends Application {
//    private TopBar topBar = new TopBar();
    private Controller controller = new Controller();


    public static void main (String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 762, 450, Color.LIGHTGRAY);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

//        topBar.loadTopBar(root);
        controller.loadTable(gridPane);
        controller.loadTopBar(root);


        root.setCenter(gridPane);
        primaryStage.setTitle("PeopleLister");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
