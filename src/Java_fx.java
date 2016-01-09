import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * ITK Java kursuse i200 iseseisev projekt. Autor: Kirke Narusk, sügis-talv 2015.
 */
public class Java_fx extends Application {
    Stage primaryStage;
    Scene scene;
    static BorderPane border = new BorderPane();

    public void start(Stage entryStage)throws Exception {
        primaryStage = entryStage;
        entryStage.setResizable(false);
        border.setStyle("-fx-padding: 15");

        TopView.topVisuaalid();
        LeftView.leftVisuaalid();
        BottomView.bottomVisuaalid();
        RightView.rightVisuaalid();
        CenterView.centerVisuaalid();

        scene = new Scene(border, 760, 480);
        entryStage.setScene(scene);
        entryStage.setTitle("Prykkar");
        entryStage.show();
        entryStage.setOnCloseRequest(event -> System.exit(0));
    }
}
