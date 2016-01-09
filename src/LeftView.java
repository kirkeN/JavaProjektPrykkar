import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

/**
 * BorderPane Left view
 */
public class LeftView {
    static List<String> arrayList = new ArrayList<>();
    public static Konteiner voimalikPrygiList = new Konteiner(arrayList); //kasutaja poolt sisestatud prygiga sarnaste sonede list
    public static VBox leftVbox;
    public static Label kysimus;
    public static TextField kasutajaInput;
    public static Button sorteeriNupp;

    //MEETODID:
    public static void leftVisuaalid() {
        leftVbox = new VBox(); //l2heb borderPane'i vasakule
        leftVbox.setSpacing(5);
        leftVbox.setPadding(new Insets(15, 0, 0, 0)); //top, right, bottom, left
        leftVbox.setPrefWidth(180);

        kysimus = new Label("MIDA SOOVID SORTEERIDA?");
        kasutajaInput = new TextField(); //kasutaja sisestab prygi, mida soovib sorteerida
        kasutajaInput.setMaxWidth(140);
        sorteeriNupp = new Button("Sorteeri");
        sorteeriNupp.setStyle("-fx-font: 12 helvetica");

        leftVbox.getChildren().addAll(kysimus, kasutajaInput, sorteeriNupp);
        nupuvajutus(sorteeriNupp,kasutajaInput);
        Java_fx.border.setLeft(leftVbox);
    }

    public static void nupuvajutus(Button sorteeriNupp, TextField kasutajaInput) {
        //"Sorteeri!" nupp ACTION!
        Pane sobivKonteinerLayout = new Pane();
        sorteeriNupp.setOnAction(event -> {
            voimalikPrygiList.getPrygi().clear();
            sobivKonteinerLayout.getChildren().clear();
            String input = kasutajaInput.getText().toLowerCase();
            String sobivKonteiner = "";
            if (input.isEmpty()) {
                sobivKonteiner = "Unustasid pürgi sisestada!";
            } else if (TopView.bio.kuhuVisata(input) != "") {
                sobivKonteiner = TopView.bio.kuhuVisata(input);
            } else if (TopView.elektroonika.kuhuVisata(input) != "") {
                sobivKonteiner = TopView.elektroonika.kuhuVisata(input);
            } else if (TopView.paberPapp.kuhuVisata(input) != "") {
                sobivKonteiner = TopView.paberPapp.kuhuVisata(input);
            } else if (TopView.ohtlikud.kuhuVisata(input) != "") {
                sobivKonteiner = TopView.ohtlikud.kuhuVisata(input);
            } else if (TopView.metallpakend.kuhuVisata(input) != "") {
                sobivKonteiner = TopView.metallpakend.kuhuVisata(input);
            } else if (TopView.klaaspakend.kuhuVisata(input) != "") {
                sobivKonteiner = TopView.klaaspakend.kuhuVisata(input);
            } else if (TopView.plastpakend.kuhuVisata(input) != "") {
                sobivKonteiner = TopView.plastpakend.kuhuVisata(input);
            } else if (voimalikPrygiList.getPrygi().isEmpty()) {
                sobivKonteiner = "Kahjuks ei leidnud hetkel sobivat konteinerit, vaata äkki leiad midagi sarnast pürgikonteineritele klikkides.";
            } else {
                sobivKonteiner = "Prügi " + kasutajaInput.getText() + " ei leitud, äkki mõtlesid hoopis midagi neist : " + "\n" + voimalikPrygiList.prindiKonteineriList();
            }
            kasutajaInput.clear();
            Label sobivKonteinerLabel = new Label(sobivKonteiner);
            sobivKonteinerLabel.setMaxWidth(180);
            sobivKonteinerLabel.setWrapText(true);
            sobivKonteinerLayout.getChildren().add(sobivKonteinerLabel);
        });
        leftVbox.getChildren().add(sobivKonteinerLayout);
    }
}
