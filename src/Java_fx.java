import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import javafx.scene.control.Alert;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

import static javafx.geometry.Pos.*;

/**
 * Created by Kirke on 24.11.2015.
 */
public class Java_fx extends Application {
    Nipp randomNipp;
    Scanner sc;
    public static List<String> voimalikPrygiList = new ArrayList<>(); //kasutaja poolt sisestatud prygiga sarnaste sonede list
    Button tagasiNupp = new Button("Tagasi");
    Stage primaryStage;
    Scene scene;

    Konteiner paberPapp = new Konteiner("Paber ja kartong"); //loon uue Konteiner tyypi objekti, mille liik on paber ja papp
    Konteiner bio = new Konteiner("Biolagunevad jäätmed");   //loon uue Konteiner tyypi objekti, mille liik on biol. j22tmed
    Konteiner elektroonika = new Konteiner("Kodutehnika"); //loon uue Konteineri tyypi objekti, mille liik on vana elektroonika (k�lmkapid, arvutid, telekad)
    Konteiner ohtlikud = new Konteiner("Ohtlikud jäätmed"); //loon uue Konteineri tyypi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
    PakendiKonteiner metallpakend = new PakendiKonteiner("Pakendid", "Metallpakend"); //loon uue PakendiKonteineri tyypi objekti, mille liik on metallpakend
    PakendiKonteiner klaaspakend = new PakendiKonteiner("Pakendid", "Klaaspakend"); //loon uue PakendiKonteineri tyypi objekti
    PakendiKonteiner plastpakend = new PakendiKonteiner("Pakendid", "Plastpakend"); //loon uue PakendiKonteineri tyypi objekti
    Konteiner ehitusprygi = new Konteiner("Ehitusprygi ja segaaatmed"); //Selle eest tuleb maksta j22tmejaamas. 20€ kuupmeeter.

    public void start(Stage entryStage)throws Exception {
        primaryStage = entryStage;
        entryStage.setResizable(true);
        VBox vbox = new VBox();
        //FlowPane flowpane = new FlowPane();
        HBox konteineridHbox = new HBox();
        konteineridHbox.setSpacing(5);
        VBox konteineridVbox = new VBox();
        konteineridHbox.setAlignment(CENTER);
        BorderPane border = new BorderPane();
        border.setStyle("-fx-padding: 15");
        scene = new Scene(border, 760, 500);
        entryStage.setScene(scene);

        //Visuaalid
        Label kysimus = new Label("MIDA SOOVID SORTEERIDA?");
        TextField kasutajaInput = new TextField();
        kasutajaInput.setMaxWidth(130);
        Button sorteeriNupp = new Button("Sorteeri");
        sorteeriNupp.setStyle("-fx-font: 12 helvetica");

        Label selgitusKonteineritele = new Label ("PRÜGIKONTEINERID");
        Nupp paberNupp = new Nupp("PABER JA PAPP", "Pane biojääde SINISESSE konteinerisse!","#99ccff");
        Nupp bioNupp = new Nupp("BIOJÄÄDE","Pane biojääde PRUUNI konteinerisse!","#d89474");
        Nupp metallNupp = new Nupp("VANAMETALL", "Vii elektroonika elektroonikakauplusesse või jäätmejaama!", "#B0C4DE ");
        //metallNupp.setStyle("-fx-background-color:linear-gradient(#B0C4DE 50%, #778899 900%);-fx-text-fill: #1a1a1a; -fx-background-radius: 4; -fx-background-insets: 0,1,4,5,6");
        Nupp ohtlikNupp = new Nupp("OHTLIKUD JÄÄTMED","Vii ohtlikud jäätmed jäätmejaama või ohtlike jäätmete konteinerisse","#ff9380" );
        ChoiceBox pakendiBox = new ChoiceBox (FXCollections.observableArrayList(
                "PAKENDID", "Metallpakend", "Klaaspakend", "Plastpakend")
        );
        pakendiBox.setMinWidth(140);
        pakendiBox.setStyle("-fx-base: #ffe34d; -fx-background-radius: 4;-fx-font-weight: bold; -fx-font: 12 helvetica;");
        pakendiBox.getSelectionModel().selectFirst();
        Button nipidNupp = new Button("Nipp");

        Button m2ng = new Button("Prykkari MÄNG");
        VBox manguBox = new VBox();
        manguBox.getChildren().addAll(m2ng);

        m2ng.setOnAction(event -> {
            Konteiner[] konteineriteList = {paberPapp, bio, elektroonika, klaaspakend, plastpakend, ohtlikud}; //teen j2rjendi k6ikide konteinerite (ja nende sisu) kohta
            Konteiner juhuslikKonteiner = konteineriteList[(int) (Math.random() * 6)];
            String juhuslikPrygi = juhuslikKonteiner.randomPrygi();
            Label kuhuViskaksid = new Label("Kuhu viskaksid sellise prügi nagu: " + juhuslikPrygi);
            ToggleGroup m2nguValikud = new ToggleGroup();
            RadioButton paber = new RadioButton(paberPapp.getLiik());
            RadioButton bioj22de = new RadioButton(bio.getLiik());
           /* RadioButton klaas = new RadioButton(klaaspakend.getAlamliik());
            RadioButton metall = new RadioButton(metallpakend.getAlamliik());
            RadioButton plast = new RadioButton(plastpakend.getAlamliik());*/
            RadioButton pakend = new RadioButton(klaaspakend.getLiik());
            RadioButton elektro = new RadioButton(elektroonika.getLiik());
            RadioButton oht = new RadioButton(ohtlikud.getLiik());
            paber.setToggleGroup(m2nguValikud);
            paber.setUserData(paberPapp.getLiik());
            bioj22de.setToggleGroup(m2nguValikud);
            bioj22de.setUserData(bio.getLiik());
           /* metall.setToggleGroup(m2nguValikud);
            metall.setUserData(metallpakend.getAlamliik());
            plast.setToggleGroup(m2nguValikud);
            plast.setUserData(plastpakend.getAlamliik());
            klaas.setToggleGroup(m2nguValikud);
            klaas.setUserData(klaaspakend.getAlamliik());*/
            pakend.setToggleGroup(m2nguValikud);
            pakend.setUserData(klaaspakend.getLiik());
            elektro.setToggleGroup(m2nguValikud);
            elektro.setUserData(elektroonika.getLiik());
            oht.setToggleGroup(m2nguValikud);
            oht.setUserData(ohtlikud.getLiik());
            manguBox.getChildren().addAll(kuhuViskaksid, paber, bioj22de, pakend, elektro, oht);
            m2nguValikud.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                public void changed(ObservableValue<? extends Toggle> ov,
                                            Toggle old_toggle, Toggle new_toggle) {
                            if (m2nguValikud.getSelectedToggle() != null) {
                                Label vastus = new Label (juhuslikKonteiner.kasKasutajaArvasAra(m2nguValikud.getSelectedToggle().getUserData().toString()));
                                manguBox.getChildren().add(vastus);
                            }
                        }
                    });
                });

        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5));
        vbox.getChildren().addAll(kysimus, kasutajaInput, sorteeriNupp, nipidNupp);
        konteineridHbox.getChildren().addAll(pakendiBox,paberNupp, bioNupp, metallNupp, ohtlikNupp);
        konteineridVbox.getChildren().addAll(selgitusKonteineritele, konteineridHbox);
        border.setLeft(vbox);
        border.setTop(konteineridVbox);
        border.setCenter(manguBox);
        //eri liiki prygi listid l2hevad eri liiki konteineritesse
        paberPapp.setPrygi(jarjend(new File("paber.txt")));
        bio.setPrygi(jarjend(new File("bio.txt")));
        elektroonika.setPrygi(jarjend(new File("elekter.txt")));
        metallpakend.setPrygi(jarjend(new File("metallpakend.txt")));
        klaaspakend.setPrygi(jarjend(new File("klaaspakend.txt")));
        plastpakend.setPrygi(jarjend(new File("plastpakend.txt")));
        ohtlikud.setPrygi(jarjend(new File("ohtlik.txt")));

        //"Sorteeri!" nupp ACTION!
        sorteeriNupp.setOnAction(event -> {
            VBox sobivKonteinerLayout = new VBox();
            Scene sobivKonteinerScene = new Scene(sobivKonteinerLayout, 300,300);
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
            }  else if (voimalikPrygiList.isEmpty()){
                sobivKonteiner = "Kahjuks ei leidnud hetkel sobivat konteinerit, vaata äkki leiad midagi sarnast pürgikonteineritele klikkides.";
            }else{
                sobivKonteiner = "Seda pügi ei leitud, äkki mõtlesid hoopis midagi neist : " + "\n" +  prindiArrayList(voimalikPrygiList).toString();
                voimalikPrygiList.clear();
                kasutajaInput.clear();
            }
            Label sobivKonteinerLabel = new Label(sobivKonteiner);
            sobivKonteinerLayout.getChildren().addAll(sobivKonteinerLabel, tagasiNupp);
            entryStage.setScene(sobivKonteinerScene);
            tagasiNupp.setOnAction(event2 -> {
                entryStage.setScene(scene);
            });
        });
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

        nupuvajutus(bioNupp,bio,new Image("toidujaatmed.jpg")); //"Biolagunevad j22tmed" nupp ACTION!
        nupuvajutus(paberNupp, paberPapp, new Image("metalman.jpg")); //"Paber ja kartong" nupp ACTION!
        nupuvajutus(metallNupp, elektroonika, new Image("metalman.jpg"));  //"Vanametall" nupp ACTION!
        nupuvajutus(ohtlikNupp, ohtlikud, new Image("metalman.jpg"));  //"Vanametall" nupp ACTION!

        //"Nipid" nupp ACTION!
        nipidNupp.setOnAction(event -> {
            try {
                randomNipp = new Nipp("uus nipp");
                randomNipp.setNipp(randomNipp.nippideJarjend());
            } catch (Exception e) {
                e.printStackTrace();
            }
            VBox nipidLayout = new VBox();
            Scene nipidScene = new Scene (nipidLayout, 500, 200);
            Label nipp = new Label(randomNipp.getNipp());
            nipp.setWrapText(true);
                nipidLayout.getChildren().addAll(nipp, tagasiNupp);
                entryStage.setScene(nipidScene);
            tagasiNupp.setOnAction(event2 -> {
                entryStage.setScene(scene);
            });
        });

        entryStage.setTitle("Prykkar");
        entryStage.show();
        entryStage.setOnCloseRequest(event -> System.exit(0));
    } //STAGE LÕPP

    // MEETODID ALGAVAD SIIT
    //konteineri nuppude ACTION MEETOD: kui konteineri nuppu vajutatakse, kuvatakse sinna konteinerisse sobiv prygi ja illustreeriv pilt
    public void nupuvajutus (Button nupp, Konteiner konteiner, Image pilt ) {
        nupp.setOnMouseClicked(event -> {
            VBox konteinerLayout = new VBox();
            Scene konteinerScene = new Scene(konteinerLayout, 300, 600);
            Label konteinerLabel = new Label(konteiner.prindiKonteineriList().toString());
            ImageView imv = new ImageView(); //pildivaade
            imv.setImage(pilt);
            VBox pictureRegion = new VBox();
            pictureRegion.getChildren().add(imv);
            konteinerLayout.getChildren().addAll(konteinerLabel, pictureRegion, tagasiNupp);
            primaryStage.setScene(konteinerScene);
            tagasiNupp.setOnAction(event2 -> {
                primaryStage.setScene(scene);
            });
        });
    }
    //ChoiceBoxi (pakendikonteinerid) ACTION meetod
    public void choiceBoxiValik (PakendiKonteiner pakend) {
        VBox choiceLayout = new VBox();
        Scene choiceScene = new Scene(choiceLayout, 300, 600);
        Label choiceLabel = new Label(pakend.prindiKonteineriList().toString());
        choiceLayout.getChildren().addAll(choiceLabel, tagasiNupp);
        primaryStage.setScene(choiceScene);
        tagasiNupp.setOnAction(event2 -> {
            primaryStage.setScene(scene);
        });
    }
    //MEETOD "jarjend": loeb failist prügi ja viskab selle arraylisti, mille ka tagastab
   public  ArrayList<String> jarjend(File fail) throws Exception {
        sc = new Scanner(fail);
        ArrayList<String> jaatmeList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();
            jaatmeList.add(rida);}
        sc.close();
        return jaatmeList;
    }
    //MEETOD "prindiArrayList": prindib välja arraylisti sisu
    public  StringBuilder prindiArrayList(List<String> massiiv) {
        StringBuilder sb = new StringBuilder();
        for (String s : massiiv) {
            sb.append(s);
            sb.append("\n");
        }return sb;
    }
}
