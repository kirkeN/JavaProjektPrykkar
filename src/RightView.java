import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * BorderPane Right view
 */
public class RightView {
    static VBox rightVbox;
    static Label jaatmejaamadeVastuvottLabel1;
    static Button kaardiNupp;
    static Stage mapStage; //kaardiakna stage
    static GoogleMapView mapView;
    static GoogleMap map;

    public static void rightVisuaalid() {
        rightVbox = new VBox();
        rightVbox.setSpacing(5);
        rightVbox.setPadding(new Insets(15, 0, 0, 10));

        jaatmejaamadeVastuvottLabel1 = new Label ("Jäätmejaamades võetakse elanikelt tasuta vastu:");
        jaatmejaamadeVastuvottLabel1.setStyle("-fx-font: 12 helvetica;-fx-font-weight: bold");
        Label jaatmejaamadeVastuvottLabel2 = new Label ("* pakendeid " + "\n" +
                "* plaste" + "\n" +
                "* paberit ja pappi" + "\n" +
                "* immutamata/töötlemata puitu" + "\n" +
                "* betooni ja telliseid (va Rahumäe jäätmejaam, kus kehtib eraldi hinnakiri)" + "\n" +
                "* vanametalli" + "\n" +
                "* toiduõli" + "\n" +
                "* kasutuskõlblikku vanamööblit" + "\n" +
                "* sõiduauto rehve" + "\n" +
                "* elektri- ja elektroonikajäätmeid" + "\n" +
                "* klaasi" + "\n" +
                "* kasutatud riideid" + "\n" +
                "* biolagunevaid aia- ja haljastusjäätmeid" + "\n" +
                "* koduseid ohtlikke jäätmeid" + "\n");
        jaatmejaamadeVastuvottLabel2.setMaxWidth(250);
        jaatmejaamadeVastuvottLabel2.setWrapText(true);

       kaardiNupp = new Button("Jäätmejaamade kaart");
       nupuvajutus();

        rightVbox.getChildren().addAll(jaatmejaamadeVastuvottLabel1, jaatmejaamadeVastuvottLabel2, kaardiNupp);
        Java_fx.border.setRight(rightVbox);
    }
    public static void nupuvajutus(){
        kaardiNupp.setOnAction(event -> {
            // Kaardiaken
            mapStage = new Stage();
            Kaardiaken kaart = new Kaardiaken(map, mapView);
            try {
                kaart.start(mapStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
