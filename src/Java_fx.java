import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import javafx.scene.control.Alert;

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
    Konteiner bio = new Konteiner("Biolagunevad jäätmed");   //loon uue Konteiner tyypi objekti, mille liik on biol. j22tmed
    Konteiner elektroonika = new Konteiner("Kodutehnika"); //loon uue Konteineri tyypi objekti, mille liik on vana elektroonika (k�lmkapid, arvutid, telekad)
    Konteiner ohtlikud = new Konteiner("Ohtlikud jäätmed"); //loon uue Konteineri tyypi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
    PakendiKonteiner metallpakend = new PakendiKonteiner("Pakend", "Metallpakend"); //loon uue PakendiKonteineri tyypi objekti, mille liik on metallpakend
    PakendiKonteiner klaaspakend = new PakendiKonteiner("Pakend", "Klaaspakend"); //loon uue PakendiKonteineri tyypi objekti
    PakendiKonteiner plastpakend = new PakendiKonteiner("Pakend", "Plastpakend"); //loon uue PakendiKonteineri tyypi objekti
    Konteiner ehitusprygi = new Konteiner("Ehitusprygi ja segaaatmed"); //Selle eest tuleb maksta j22tmejaamas. 20€ kuupmeeter.

    public void start(Stage entryStage)throws Exception {
        primaryStage = entryStage;
        entryStage.setResizable(true);
        VBox vbox = new VBox();
        //FlowPane flowpane = new FlowPane();
        HBox hbox = new HBox();
        BorderPane border = new BorderPane();
        scene = new Scene(border, 500, 200);
        entryStage.setScene(scene);

        //Visuaalid
        Label kysimus = new Label("Mida soovid sorteerida?");
        kysimus.setTranslateX(4);
        kysimus.setTranslateY(2);
        TextField kasutajaInput = new TextField();
        kasutajaInput.setMaxWidth(125);
        Button sorteeriNupp = new Button("Sorteeri");
        kasutajaInput.setTranslateX(4);
        kasutajaInput.setTranslateY(2);
        sorteeriNupp.setStyle("-fx-font: 12 helvetica; -fx-base: #b6e7c9;");
        sorteeriNupp.setTranslateY(2);
        sorteeriNupp.setTranslateX(4);
        Nupp paberNupp = new Nupp("PABER JA PAPP", "Pane biojääde SINISESSE konteinerisse!","#4682B4");
        Nupp bioNupp = new Nupp("BIOJÄÄDE","Pane biojääde PRUUNI konteinerisse!","#d89474");
        Nupp metallNupp = new Nupp("VANAMETALL", "Vii elektroonika elektroonikakauplusesse või jäätmejaama!");
        //metallNupp.setStyle("-fx-background-color:linear-gradient(#B0C4DE 50%, #778899 900%);-fx-text-fill: #1a1a1a; -fx-background-radius: 4; -fx-background-insets: 0,1,4,5,6");
        Nupp ohtlikNupp = new Nupp("OHTLIKUD JÄÄTMED","Vii ohtlikud jäätmed jäätmejaama või ohtlike jäätmete konteinerisse" ); //linear-gradient(#C0C0C0,#77889
        ChoiceBox pakendiBox = new ChoiceBox (FXCollections.observableArrayList(
                "PAKENDID", "Metallpakend", "Klaaspakend", "Plastpakend")
        );
        pakendiBox.setStyle("-fx-base: #ffe34d; -fx-background-radius: 4;-fx-font-weight: bold; -fx-font: 12 helvetica;");
        pakendiBox.getSelectionModel().selectFirst();
        Button nipidNupp = new Button("Nipp");
        nipidNupp.setStyle("-fx-font: 12 helvetica; -fx-base: #b6e7c9; -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1)");
        vbox.setSpacing(5);
        vbox.getChildren().addAll(kysimus, kasutajaInput, sorteeriNupp, nipidNupp);
        hbox.getChildren().addAll(pakendiBox,paberNupp, bioNupp, metallNupp, ohtlikNupp);
       // flowpane.setStyle("-fx-base: #b6e7c9;");
        border.setLeft(vbox);
        border.setTop(hbox);

        //eri liiki prygi listid l2hevad eri liiki konteineritesse
        paberPapp.setPrygi(jarjend(new File("paber.txt")));
        bio.setPrygi(jarjend(new File("bio.txt")));
        elektroonika.setPrygi(jarjend(new File("elekter.txt")));
        metallpakend.setPrygi(jarjend(new File("metallpakend.txt")));
        klaaspakend.setPrygi(jarjend(new File("klaaspakend.txt")));
        plastpakend.setPrygi(jarjend(new File("plastpakend.txt")));
        //ohtlikud.setPrygi(jarjend(new File("ohtlik")));

        //"Sorteeri!" nupp ACTION!
        sorteeriNupp.setOnAction(event -> {
            VBox sobivKonteinerLayout = new VBox();
            Scene sobivKonteinerScene = new Scene(sobivKonteinerLayout, 300,300);
            String input = kasutajaInput.getText().toLowerCase();
            String sobivKonteiner = "";
            if (input.isEmpty()){
                sobivKonteiner = "Ära unusta pürgi sisestada!";
            } else if (bio.kuhuVisata(input) != "") {
                sobivKonteiner = bio.kuhuVisata(input);
            } else if (elektroonika.kuhuVisata(input) != "") {
                sobivKonteiner = elektroonika.kuhuVisata(input);
            } else if (paberPapp.kuhuVisata(input) != "") {
                sobivKonteiner = paberPapp.kuhuVisata(input);
            } /*else if (ohtlikud.kuhuVisata(input) != ""){
                sobivKonteiner = ohtlikud.kuhuVisata(input);
            }*/ else if (voimalikPrygiList.isEmpty()){
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
