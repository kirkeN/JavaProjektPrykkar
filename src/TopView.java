import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;


/**
 * BorderPane Top view
 */
public class TopView {
    static Konteiner paberPapp = new Konteiner("Paber ja papp"); //loon uue Konteiner tyypi objekti, mille liik on paber ja papp
    static Konteiner bio = new Konteiner("Biolagunevad jäätmed");   //loon uue Konteiner tyypi objekti, mille liik on biol. j22tmed
    static Konteiner elektroonika = new Konteiner("Kodutehnika"); //loon uue Konteineri tyypi objekti, mille liik on vana elektroonika (k�lmkapid, arvutid, telekad)
    static Konteiner ohtlikud = new Konteiner("Ohtlikud jäätmed"); //loon uue Konteineri tyypi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
    static PakendiKonteiner metallpakend = new PakendiKonteiner("Pakendid", "Metallpakend"); //loon uue PakendiKonteineri tyypi objekti, mille liik on metallpakend
    static PakendiKonteiner klaaspakend = new PakendiKonteiner("Pakendid", "Klaaspakend"); //loon uue PakendiKonteineri (Konteineri alamklass) tyypi objekti - klaaspakend
    static PakendiKonteiner plastpakend = new PakendiKonteiner("Pakendid", "Plastpakend"); //loon uue PakendiKonteineri tyypi objekti - plastpakend

    static Stage konteinerStage; //eraldi aken konteinerite sisu kuvamiseks

    //MEETODID:
    public static void topVisuaalid() throws Exception {
        HBox konteineridHbox = new HBox(); //l2heb topVbox'i
        konteineridHbox.setSpacing(9);
        konteineridHbox.setAlignment(Pos.CENTER);
        VBox topVbox = new VBox(); //l2heb borderPane'i üles
        topVbox.setSpacing(5);

        Label selgitusKonteineritele = new Label ("PRÜGIKONTEINERID");
        selgitusKonteineritele.setStyle("-fx-font: 12 helvetica;-fx-font-weight: bold");
        Nupp paberNupp = new Nupp("PABER JA PAPP", "Pane biojääde SINISESSE konteinerisse!","#99ccff");
        Nupp bioNupp = new Nupp("BIOJÄÄDE","Pane biojääde PRUUNI konteinerisse!","#d89474");
        Nupp metallNupp = new Nupp("VANAMETALL", "Vii elektroonika elektroonikakauplusesse või jäätmejaama!", "#C0C0C0 ");
        Nupp ohtlikNupp = new Nupp("OHTLIKUD JÄÄTMED","Vii ohtlikud jäätmed jäätmejaama või ohtlike jäätmete konteinerisse","#ff9380" );

        prygiKonteinerisse();

        // Konteinerinupud ACTION!
        nupuvajutus(bioNupp,bio,new Image("toidujaatmed.jpg")); //"Biolagunevad j22tmed" nupp ACTION!
        nupuvajutus(paberNupp, paberPapp, new Image("metalman.jpg")); //"Paber ja kartong" nupp ACTION!
        nupuvajutus(metallNupp, elektroonika, new Image("metalman.jpg"));  //"Vanametall" nupp ACTION!
        nupuvajutus(ohtlikNupp, ohtlikud, new Image("metalman.jpg"));  //"Vanametall" nupp ACTION!

        ChoiceBox pakendiBox = new ChoiceBox (FXCollections.observableArrayList("PAKENDID", "Metallpakend", "Klaaspakend", "Plastpakend"));
        pakendiBox.setMinWidth(140);
        pakendiBox.setStyle("-fx-base: #ffe34d; -fx-background-radius: 4;-fx-font: 12 helvetica");
        pakendiBox.getSelectionModel().selectFirst();
        Tooltip tipz = new Tooltip("Pane pakendid KOLLASESSE konteinerisse!");
        tipz.setWrapText(true);
        tipz.setMaxWidth(150);
        pakendiBox.setTooltip(tipz);

        pakendiBox.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ObservableValue observable, Object oldValue, Object newValue) -> {
                            String pakendiValue = pakendiBox.getValue().toString();
                            switch (pakendiValue) {
                                case "Klaaspakend":
                                    choiceBoxiValik(klaaspakend);
                                    break;
                                case "Metallpakend":
                                    choiceBoxiValik(metallpakend);
                                    break;
                                case "Plastpakend":
                                    choiceBoxiValik(plastpakend);
                                    break;
                            }
                        });

        konteineridHbox.getChildren().addAll(pakendiBox,paberNupp, bioNupp, metallNupp, ohtlikNupp);
        topVbox.getChildren().addAll(selgitusKonteineritele, konteineridHbox);
        Java_fx.border.setTop(topVbox);
    }

    public static void prygiKonteinerisse() throws Exception {
        //eri liiki prygi listid l2hevad eri liiki konteineritesse
        paberPapp.setPrygi(paberPapp.jarjend(new File("paber.txt")));
        bio.setPrygi(bio.jarjend(new File("bio.txt")));
        elektroonika.setPrygi(elektroonika.jarjend(new File("elekter.txt")));
        metallpakend.setPrygi(metallpakend.jarjend(new File("metallpakend.txt")));
        klaaspakend.setPrygi(klaaspakend.jarjend(new File("klaaspakend.txt")));
        plastpakend.setPrygi(plastpakend.jarjend(new File("plastpakend.txt")));
        ohtlikud.setPrygi(ohtlikud.jarjend(new File("ohtlik.txt")));
    }
    //konteineri nuppude ACTION MEETOD: kui konteineri nuppu vajutatakse, kuvatakse sinna konteinerisse sobiv prygi ja illustreeriv pilt
    public static void nupuvajutus(Nupp nupp, Konteiner konteiner, Image pilt) {
        nupp.setOnMouseClicked(event -> {
            VBox konteinerLayout = new VBox();
            konteinerLayout.setSpacing(5);
            konteinerLayout.setPadding(new Insets(5,15,10,15));
            Label konteinerLabel = new Label(konteiner.prindiKonteineriList().toString());
            ImageView imv = new ImageView(); //pildivaade
            imv.setImage(pilt);
            Pane pictureRegion = new Pane();
            pictureRegion.getChildren().add(imv);
            konteinerLayout.getChildren().addAll(konteinerLabel, pictureRegion);
            Scene konteinerScene = new Scene(konteinerLayout, konteinerLayout.getPrefWidth(), konteinerLayout.getPrefHeight());

            konteinerStage= new Stage();
            konteinerStage.setScene(konteinerScene);
            konteinerStage.show();
        });
    }

    public static void choiceBoxiValik(PakendiKonteiner pakendikonteiner) {     //ChoiceBoxi (pakendikonteinerid) ACTION meetod
        VBox choiceLayout = new VBox();
        choiceLayout.setSpacing(5);
        choiceLayout.setPadding(new Insets(5,15,10,15));
        Label choiceLabel = new Label(pakendikonteiner.prindiKonteineriList().toString());
        choiceLayout.getChildren().addAll(choiceLabel);
        Scene choiceScene = new Scene(choiceLayout, choiceLayout.getPrefWidth(),choiceLayout.getPrefWidth());

        konteinerStage = new Stage();
        konteinerStage.setScene(choiceScene);
        konteinerStage.show();
    }

}
