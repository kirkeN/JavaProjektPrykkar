import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.File;
import java.util.*;
import java.util.List;
import static javafx.geometry.Pos.*;

/**
 * Created by Kirke on 24.11.2015.
 */
public class Java_fx extends Application { //implements MapComponentInitializedListener
    Nipp randomNipp;
    static List<String> arrayList = new ArrayList<>();
    public static Konteiner voimalikPrygiList = new Konteiner(arrayList); //kasutaja poolt sisestatud prygiga sarnaste sonede list
    Stage primaryStage; //konteinerite stage
    Scene scene;
    Stage mapStage; //kaardiakna stage
    Stage konteinerStage;
    GoogleMapView mapView;
    GoogleMap map;
    static VBox centerBox;
    static Button m2ng;

    Konteiner paberPapp = new Konteiner("Paber ja papp"); //loon uue Konteiner tyypi objekti, mille liik on paber ja papp
    Konteiner bio = new Konteiner("Biolagunevad jäätmed");   //loon uue Konteiner tyypi objekti, mille liik on biol. j22tmed
    Konteiner elektroonika = new Konteiner("Kodutehnika"); //loon uue Konteineri tyypi objekti, mille liik on vana elektroonika (k�lmkapid, arvutid, telekad)
    Konteiner ohtlikud = new Konteiner("Ohtlikud jäätmed"); //loon uue Konteineri tyypi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
    PakendiKonteiner metallpakend = new PakendiKonteiner("Pakendid", "Metallpakend"); //loon uue PakendiKonteineri tyypi objekti, mille liik on metallpakend
    PakendiKonteiner klaaspakend = new PakendiKonteiner("Pakendid", "Klaaspakend"); //loon uue PakendiKonteineri (Konteineri alamklass) tyypi objekti - klaaspakend
    PakendiKonteiner plastpakend = new PakendiKonteiner("Pakendid", "Plastpakend"); //loon uue PakendiKonteineri tyypi objekti - plastpakend
    Konteiner ehitusprygi = new Konteiner("Ehitusprygi ja segaaatmed"); //Selle eest tuleb maksta j22tmejaamas. 20€ kuupmeeter.

    public void start(Stage entryStage)throws Exception {
        primaryStage = entryStage;
        entryStage.setResizable(false);

        //layout:
        HBox konteineridHbox = new HBox(); //l2heb topVbox'i
        konteineridHbox.setSpacing(5);
        konteineridHbox.setAlignment(CENTER);
        VBox topVbox = new VBox(); //l2heb borderPane'i üles
        topVbox.setSpacing(5);
        VBox leftVbox = new VBox(); //l2heb borderPane'i vasakule
        leftVbox.setSpacing(5);
        leftVbox.setPadding(new Insets(15,0,0,0)); //top, right, bottom, left
        centerBox = new VBox(); //l2heb borderPane'i keskele
        centerBox.setSpacing(10);
        centerBox.setPadding(new Insets(15,10,10,10));
        VBox bottomBox = new VBox();
        bottomBox.setSpacing(5);
        VBox nipidLayout = new VBox(); //läheb bottomBoxi
        VBox rightVbox = new VBox();
        rightVbox.setSpacing(5);
        rightVbox.setPadding(new Insets(15,0,0,10));
        BorderPane border = new BorderPane();
        border.setStyle("-fx-padding: 15");
        border.setLeft(leftVbox);
        leftVbox.setPrefWidth(180);
        border.setTop(topVbox);
        border.setCenter(centerBox);
        border.setBottom(bottomBox);
        bottomBox.setPrefHeight(80);
        border.setRight(rightVbox);

        scene = new Scene(border, 760, 480);
        entryStage.setScene(scene);

        //Visuaalid
        Label kysimus = new Label("MIDA SOOVID SORTEERIDA?");
        TextField kasutajaInput = new TextField(); //kasutaja sisestab prygi, mida soovib sorteerida
        kasutajaInput.setMaxWidth(140);
        Button sorteeriNupp = new Button("Sorteeri");
        sorteeriNupp.setStyle("-fx-font: 12 helvetica");
        Button nipidNupp = new Button("Nipp");
        m2ng = new Button("MÄNGI");
        Button kaardiNupp = new Button("Jäätmejaamade kaart");

        Image manguPilt = new Image("reuse2.svg");
        ImageView imv = new ImageView(); //pildivaade
        imv.setImage(manguPilt);
        Pane pictureRegion = new Pane();
        pictureRegion.getChildren().add(imv);
        imv.setTranslateX(30);
        imv.setTranslateY(30);

        Label selgitusKonteineritele = new Label ("PRÜGIKONTEINERID");
        selgitusKonteineritele.setStyle("-fx-font: 12 helvetica;-fx-font-weight: bold");
        Nupp paberNupp = new Nupp("PABER JA PAPP", "Pane biojääde SINISESSE konteinerisse!","#99ccff");
        Nupp bioNupp = new Nupp("BIOJÄÄDE","Pane biojääde PRUUNI konteinerisse!","#d89474");
        Nupp metallNupp = new Nupp("VANAMETALL", "Vii elektroonika elektroonikakauplusesse või jäätmejaama!", "#C0C0C0 ");
        Nupp ohtlikNupp = new Nupp("OHTLIKUD JÄÄTMED","Vii ohtlikud jäätmed jäätmejaama või ohtlike jäätmete konteinerisse","#ff9380" );
        ChoiceBox pakendiBox = new ChoiceBox (FXCollections.observableArrayList("PAKENDID", "Metallpakend", "Klaaspakend", "Plastpakend"));
        pakendiBox.setMinWidth(140);
        pakendiBox.setStyle("-fx-base: #ffe34d; -fx-background-radius: 4;-fx-font: 12 helvetica");
        pakendiBox.getSelectionModel().selectFirst();
        Tooltip tipz = new Tooltip("Pane pakendid KOLLASESSE konteinerisse!");
        tipz.setWrapText(true);
        tipz.setMaxWidth(150);
        pakendiBox.setTooltip(tipz);

        // panen visuaalid box'idesse
        leftVbox.getChildren().addAll(kysimus, kasutajaInput, sorteeriNupp);
        konteineridHbox.getChildren().addAll(pakendiBox,paberNupp, bioNupp, metallNupp, ohtlikNupp);
        topVbox.getChildren().addAll(selgitusKonteineritele, konteineridHbox);
        centerBox.getChildren().addAll(m2ng, pictureRegion);
        bottomBox.getChildren().addAll(nipidNupp, nipidLayout);
        Label jaatmejaamadeVastuvottLabel1 = new Label ("Jäätmejaamades võetakse elanikelt tasuta vastu:");
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
        rightVbox.getChildren().addAll(jaatmejaamadeVastuvottLabel1, jaatmejaamadeVastuvottLabel2, kaardiNupp);


        //eri liiki prygi listid l2hevad eri liiki konteineritesse
        paberPapp.setPrygi(paberPapp.jarjend(new File("paber.txt")));
        bio.setPrygi(bio.jarjend(new File("bio.txt")));
        elektroonika.setPrygi(elektroonika.jarjend(new File("elekter.txt")));
        metallpakend.setPrygi(metallpakend.jarjend(new File("metallpakend.txt")));
        klaaspakend.setPrygi(klaaspakend.jarjend(new File("klaaspakend.txt")));
        plastpakend.setPrygi(plastpakend.jarjend(new File("plastpakend.txt")));
        ohtlikud.setPrygi(ohtlikud.jarjend(new File("ohtlik.txt")));

        //"Sorteeri!" nupp ACTION!
        Pane sobivKonteinerLayout = new Pane();
        sorteeriNupp.setOnAction(event -> {
            voimalikPrygiList.getPrygi().clear();
            sobivKonteinerLayout.getChildren().clear();
            String input = kasutajaInput.getText().toLowerCase();
            String sobivKonteiner = "";
            if (input.isEmpty()){
                sobivKonteiner = "Unustasid pürgi sisestada!";
            } else if (bio.kuhuVisata(input) != "") {
                sobivKonteiner = bio.kuhuVisata(input);
            } else if (elektroonika.kuhuVisata(input) != "") {
                sobivKonteiner = elektroonika.kuhuVisata(input);
            } else if (paberPapp.kuhuVisata(input) != "") {
                sobivKonteiner = paberPapp.kuhuVisata(input);
            } else if (ohtlikud.kuhuVisata(input) != ""){
                sobivKonteiner = ohtlikud.kuhuVisata(input);
            } else if (metallpakend.kuhuVisata(input) != ""){
                sobivKonteiner = metallpakend.kuhuVisata(input);
            } else if (klaaspakend.kuhuVisata(input) != ""){
                sobivKonteiner = klaaspakend.kuhuVisata(input);
            } else if (plastpakend.kuhuVisata(input) != ""){
                sobivKonteiner = plastpakend.kuhuVisata(input);
            } else if (voimalikPrygiList.getPrygi().isEmpty()){
                sobivKonteiner = "Kahjuks ei leidnud hetkel sobivat konteinerit, vaata äkki leiad midagi sarnast pürgikonteineritele klikkides.";
            }  else{
                sobivKonteiner = "Prügi " +kasutajaInput.getText() + " ei leitud, äkki mõtlesid hoopis midagi neist : " + "\n" +  voimalikPrygiList.prindiKonteineriList();
            }
            kasutajaInput.clear();
            Label sobivKonteinerLabel = new Label(sobivKonteiner);
            sobivKonteinerLabel.setMaxWidth(180);
            sobivKonteinerLabel.setWrapText(true);
            sobivKonteinerLayout.getChildren().add(sobivKonteinerLabel);
        });
       leftVbox.getChildren().add(sobivKonteinerLayout);

        // Pakendid choiceBox ACTION!
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
        // Konteinerinupud ACTION!
        nupuvajutus(bioNupp,bio,new Image("toidujaatmed.jpg")); //"Biolagunevad j22tmed" nupp ACTION!
        nupuvajutus(paberNupp, paberPapp, new Image("metalman.jpg")); //"Paber ja kartong" nupp ACTION!
        nupuvajutus(metallNupp, elektroonika, new Image("metalman.jpg"));  //"Vanametall" nupp ACTION!
        nupuvajutus(ohtlikNupp, ohtlikud, new Image("metalman.jpg"));  //"Vanametall" nupp ACTION!

        m2ng.setOnAction(event -> { //"Mängi" nupp ACTION!
                    m2ngi();
                });

        //"Nipid" nupp ACTION!
        nipidNupp.setOnAction(event -> {
            nipidLayout.getChildren().clear();
            try {
                randomNipp = new Nipp("uus nipp");
                randomNipp.setNipp(randomNipp.nippideJarjend());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Label nipp = new Label(randomNipp.getNipp());
            nipp.setWrapText(true);
            nipidLayout.getChildren().add(nipp);
        });

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

        entryStage.setTitle("Prykkar");
        entryStage.show();
        entryStage.setOnCloseRequest(event -> System.exit(0));
    } //STAGE LÕPP

    // MEETODID ALGAVAD SIIT
    //konteineri nuppude ACTION MEETOD: kui konteineri nuppu vajutatakse, kuvatakse sinna konteinerisse sobiv prygi ja illustreeriv pilt
    public void nupuvajutus (Nupp nupp, Konteiner konteiner, Image pilt ) {
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
    //ChoiceBoxi (pakendikonteinerid) ACTION meetod
    public void choiceBoxiValik (PakendiKonteiner pakendikonteiner) {
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
    //Nupp "Mängi" Action
     public void m2ngi() {
            centerBox.getChildren().clear();
            Konteiner[] konteineriteList = {paberPapp, bio, elektroonika, klaaspakend, plastpakend, ohtlikud}; //teen j2rjendi k6ikide konteinerite (ja nende sisu) kohta
            Konteiner[] juhuslikKonteiner = {konteineriteList[(int) (Math.random() * 6)]};
            String juhuslikPrygi = juhuslikKonteiner[0].randomPrygi(); //tahan otsida juhuslikust konteinerist juhuslikku prygi
            Label kuhuViskaksid = new Label("Kuhu viskaksid sellise prügi nagu: " + juhuslikPrygi);
            kuhuViskaksid.setWrapText(true);
            ToggleGroup m2nguValikud = new ToggleGroup();
            RadioButton paber = new RadioButton(paberPapp.getLiik());
            RadioButton bioj22de = new RadioButton(bio.getLiik());
            RadioButton pakend = new RadioButton(klaaspakend.getLiik());
            RadioButton elektro = new RadioButton(elektroonika.getLiik());
            RadioButton oht = new RadioButton(ohtlikud.getLiik());
            paber.setToggleGroup(m2nguValikud);
            paber.setUserData(paberPapp.getLiik());
            bioj22de.setToggleGroup(m2nguValikud);
            bioj22de.setUserData(bio.getLiik());
            pakend.setToggleGroup(m2nguValikud);
            pakend.setUserData(klaaspakend.getLiik());
            elektro.setToggleGroup(m2nguValikud);
            elektro.setUserData(elektroonika.getLiik());
            oht.setToggleGroup(m2nguValikud);
            oht.setUserData(ohtlikud.getLiik());
            centerBox.getChildren().addAll(m2ng, kuhuViskaksid, paber, bioj22de, pakend, elektro, oht);
            VBox oigeVastusBox = new VBox(); // layout 6igele vastusele, mis l2heb centerBoxi
            m2nguValikud.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                    oigeVastusBox.getChildren().clear(); //iga kord, kui kasutaja vahetab radiobuttonit, kirjutatakse 6ige vastus yle
                    Label vastus = new Label();
                    if (m2nguValikud.getSelectedToggle() != null) {
                        if (juhuslikKonteiner[0].kasKasutajaArvasAra(m2nguValikud.getSelectedToggle().getUserData().toString())) {
                            vastus.setText("Õige");
                            oigeVastusBox.getChildren().add(vastus);
                           // m2ngi();
                        } else {
                            vastus.setText("Vale, mõtle järele");
                            oigeVastusBox.getChildren().add(vastus);
                        }
                        vastus.setStyle("-fx-font: 12 helvetica;-fx-font-weight: bold");
                    }
                }
            });
            centerBox.getChildren().add(oigeVastusBox);
    }
}
