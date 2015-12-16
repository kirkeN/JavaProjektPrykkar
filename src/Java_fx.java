import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
    Konteiner bio = new Konteiner("Biolagunevad jaatmed");   //loon uue Konteiner tyypi objekti, mille liik on biol. j22tmed
    Konteiner elektroonika = new Konteiner("Vanametall"); //loon uue Konteineri tyypi objekti, mille liik on vana elektroonika (k�lmkapid, arvutid, telekad)
    Konteiner ohtlikud = new Konteiner("Ohtlikud jaatmed"); //loon uue Konteineri tyypi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
    PakendiKonteiner metallpakend = new PakendiKonteiner("Pakend", "Metallpakend"); //loon uue PakendiKonteineri tyypi objekti, mille liik on metallpakend
    PakendiKonteiner klaaspakend = new PakendiKonteiner("Pakend", "Klaaspakend"); //loon uue PakendiKonteineri tyypi objekti
    PakendiKonteiner plastpakend = new PakendiKonteiner("Pakend", "Plastpakend"); //loon uue PakendiKonteineri tyypi objekti
    Konteiner ehitusprygi = new Konteiner("Ehitusprygi ja segaaatmed"); //Selle eest tuleb maksta j22tmejaamas. 20€ kuupmeeter.

    public void start(Stage entryStage)throws Exception {
        primaryStage = entryStage;
        entryStage.setResizable(true);
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        BorderPane border = new BorderPane();
        scene = new Scene(border, 500, 200);
        entryStage.setScene(scene);

        //Visuaalid
        Label kysimus = new Label("Mida soovid sorteerida?");
        TextField kasutajaInput = new TextField();
        Button sorteeriNupp = new Button("Sorteeri!");
        Button paberNupp = new Button("Paber ja kartong");
        Button bioNupp = new Button("Biolagunevad jäätmed");
        Button metallNupp = new Button("Vanametall");
        ChoiceBox pakendiBox = new ChoiceBox (FXCollections.observableArrayList(
                "Pakendid", "Metallpakend", "Klaaspakend", "Plastpakend")
        );
        pakendiBox.getSelectionModel().selectFirst();
        Button nipidNupp = new Button("Nipid");
        vbox.setSpacing(5);
        vbox.getChildren().addAll(kysimus, kasutajaInput, sorteeriNupp);
        hbox.getChildren().addAll(paberNupp, bioNupp, metallNupp, pakendiBox);
        border.setTop(vbox);
        //vbox.getChildren().addAll(kysimus, kasutajaInput,sorteeriNupp, paberNupp, bioNupp, metallNupp, nipidNupp);
        border.setCenter(hbox);
        border.setBottom(nipidNupp);

        //eri liiki prygi listid l2hevad eri liiki konteineritesse
        paberPapp.setPrygi(jarjend(new File("paber.txt")));
        bio.setPrygi(jarjend(new File("bio.txt")));
        elektroonika.setPrygi(jarjend(new File("elekter.txt")));
        metallpakend.setPrygi(jarjend(new File("metallpakend.txt")));
        klaaspakend.setPrygi(jarjend(new File("klaaspakend.txt")));
        plastpakend.setPrygi(jarjend(new File("plastpakend.txt")));

        //"Sorteeri!" nupp ACTION!
        sorteeriNupp.setOnAction(event -> {
            VBox sobivKonteinerLayout = new VBox();
            Scene sobivKonteinerScene = new Scene(sobivKonteinerLayout, 300,300);
            String input = kasutajaInput.getText().toLowerCase();
            String sobivKonteiner = "";
            if (bio.kuhuVisata(input) != "") {
                sobivKonteiner = bio.kuhuVisata(input);
            } else if (elektroonika.kuhuVisata(input) != "") {
                sobivKonteiner = elektroonika.kuhuVisata(input);
            } else if (paberPapp.kuhuVisata(input) != "") {
                sobivKonteiner = paberPapp.kuhuVisata(input);
            } else if (voimalikPrygiList.isEmpty()){
                sobivKonteiner = "Sorry, programm on alles poolik, ei leidnud hetkel sobivat konteinerit";
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
            Label nipp = new Label(randomNipp.getNipp().toString());
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
        nupp.setOnAction(event -> {
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
