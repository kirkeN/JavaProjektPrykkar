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
    String randomNipp;
    Scanner sc;
    List<String> voimalikPrygiList = new ArrayList<>(); //kasutaja poolt sisestatud prygiga sarnaste sonede list
    Button tagasiNupp = new Button("Tagasi");
    Stage primaryStage;
    Scene scene;

    Konteiner paberPapp = new Konteiner("Paber ja kartong"); //loon uue Konteiner tyypi objekti, mille liik on paber ja papp
    Konteiner bio = new Konteiner("Biolagunevad jaatmed");   //loon uue Konteiner tyypi objekti, mille liik on biol. j22tmed
    Konteiner elektroonika = new Konteiner("Vanametall"); //loon uue Konteineri tyypi objekti, mille liik on vana elektroonika (k�lmkapid, arvutid, telekad)
    Konteiner ohtlikud = new Konteiner("Ohtlikud jaatmed"); //loon uue Konteineri tyypi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
    Konteiner metallpakend = new PakendiKonteiner("Pakend", "Metallpakend"); //loon uue PakendiKonteineri tyypi objekti, mille liik on metallpakend
    Konteiner klaaspakend = new PakendiKonteiner("Pakend", "Klaaspakend"); //loon uue PakendiKonteineri tyypi objekti
    Konteiner plastpakend = new PakendiKonteiner("Pakend", "Plastpakend"); //loon uue PakendiKonteineri tyypi objekti
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
            if (kuhuVisata(bio, input) != "") {
                sobivKonteiner = kuhuVisata(bio, input);
            } else if (kuhuVisata(elektroonika, input) != "") {
                sobivKonteiner = kuhuVisata(elektroonika, input);
            } else if (kuhuVisata(paberPapp, input) != "") {
                sobivKonteiner = kuhuVisata(paberPapp, input);
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
                               // if(pakendiBox.getValue().equals("Klaaspakend")){
                            String pakendiValue = pakendiBox.getValue().toString();
                            switch (pakendiValue) {
                                case "Klaaspakend":
                                    VBox pakendLayout = new VBox();
                                    Scene pakendiScene = new Scene(pakendLayout, 300, 600);
                                    Label klaasLabel = new Label(prindiKonteineriList(klaaspakend).toString());
                                    pakendLayout.getChildren().addAll(klaasLabel, tagasiNupp);
                                    entryStage.setScene(pakendiScene);
                                    tagasiNupp.setOnAction(event2 -> {
                                    entryStage.setScene(scene);
                                    }); break;
                                case "Metallpakend":
                                    pakendLayout = new VBox();
                                    pakendiScene = new Scene(pakendLayout, 300, 600);
                                    Label metallLabel = new Label(prindiKonteineriList(metallpakend).toString());
                                    pakendLayout.getChildren().addAll(metallLabel, tagasiNupp);
                                    entryStage.setScene(pakendiScene);
                                    tagasiNupp.setOnAction(event2 -> {
                                        entryStage.setScene(scene);
                                    }); break;
                                case "Plastpakend":
                                    pakendLayout = new VBox();
                                    pakendiScene = new Scene(pakendLayout, 300, 600);
                                    Label plastLabel = new Label(prindiKonteineriList(plastpakend).toString());
                                    pakendLayout.getChildren().addAll(plastLabel, tagasiNupp);
                                    entryStage.setScene(pakendiScene);
                                    tagasiNupp.setOnAction(event2 -> {
                                        entryStage.setScene(scene);
                                    }); break;
                                }
                        });

        nupuvajutus(bioNupp,bio,new Image("toidujaatmed.jpg")); //"Biolagunevad j22tmed" nupp ACTION!
        nupuvajutus(paberNupp, paberPapp, new Image("metalman.jpg")); //"Paber ja kartong" nupp ACTION!
        nupuvajutus(metallNupp, elektroonika, new Image("metalman.jpg"));  //"Vanametall" nupp ACTION!

        //"Nipid" nupp ACTION!
        nipidNupp.setOnAction(event -> {
            try {
                nippideJarjend();
            } catch (Exception e) {
                e.printStackTrace();
            }
            VBox nipidLayout = new VBox();
            Scene nipidScene = new Scene (nipidLayout, 500, 200);
            Label nipp = new Label(randomNipp);
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
    //ACTION MEETOD
    public void nupuvajutus (Button nupp, Konteiner konteiner, Image pilt ) {
        nupp.setOnAction(event -> {
            VBox konteinerLayout = new VBox();
            Scene konteinerScene = new Scene(konteinerLayout, 300, 600);
            Label konteinerLabel = new Label(prindiKonteineriList(konteiner).toString());
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
    /*//ChoiceBoxi ACTION meetod
    public void choiceBoxiValik ()
    VBox choiceLayout = new VBox();
    Scene choiceScene = new Scene(choiceLayout, 300, 600);
    Label choiceLabel = new Label(prindiKonteineriList(klaaspakend).toString());
    choiceLayout.getChildren().addAll(choiceLabel, tagasiNupp);
    entryStage.setScene(paberScene);
    tagasiNupp.setOnAction(event2 -> {
        entryStage.setScene(scene);
    });*/

    // nippide lugemine failist
     void nippideJarjend () throws Exception {
        File nippideFail = new File("nipid.txt"); // txt failid peavad olema proj. samas kaustas
        sc = new Scanner(nippideFail);
        List<String> listNipid = new ArrayList<>();
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();//rida tuleb eraldi muutujasse salvestada
            listNipid.add(rida);}
        sc.close();

        randomNipp = listNipid.get((int) (Math.random() * (listNipid.size()))); //randomiga valin nipi
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
   //MEETOD "prindiKonteineriList": prindib välja konteineri sisu
    public  StringBuilder prindiKonteineriList(Konteiner konteiner) {
        List<String> prygiList = konteiner.getPrygi();
        Collections.sort(prygiList);
        StringBuilder sb = new StringBuilder();
        for (String s : prygiList) {
            sb.append(s);
            sb.append("\n");
        }return sb;
    }
    //MEETOD "prindiArrayList": prindib välja arraylisti sisu
    public  StringBuilder prindiArrayList(List<String> massiiv) {
        StringBuilder sb = new StringBuilder();
        for (String s : massiiv) {
            sb.append(s);
            sb.append("\n");
        }return sb;
    }
    //MEETOD "kuhuVisata": ytleb kasutajale, millisesse konteinerisse prygi visata
    public String kuhuVisata(Konteiner prygiKonteiner, String kasutajaPrygi) {
        String sobivKonteiner = "";
        for (int i = 0; prygiKonteiner.getPrygi().size() > i; i++) {
            if (prygiKonteiner.getPrygi().get(i).equals(kasutajaPrygi)) {//kontrollin, kas kasutaja prygi sobib antud konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
                sobivKonteiner = prygiKonteiner.getLiik();
            } else{
                sarnanePrygiNimi(kasutajaPrygi, prygiKonteiner.getPrygi().get(i)); //kui tapset vastet konteinerist ei leita, otsitakse sanraseid t2hekombinatsioone sisaldavaid vasteid; meetod meetodi sees
            }
        } return sobivKonteiner;
    }
    // MEETOD "sarnanePrygiNimi" : kui t2pset kasutaja sisestatud sone ei leita, siis hakatakse otsima sarnast prygi
    public void sarnanePrygiNimi (String kasutajaPrygi, String konteineriPrygi) {
        char[] kasutajaPrygiChars = kasutajaPrygi.toCharArray();
        char[] konteineriPrygiChars = konteineriPrygi.toCharArray();
        String[] tahekomplekt1 = new String[kasutajaPrygiChars.length-2];  //kontrollin kattuvusi 3-tahelistes kombinatsioonides, selleks teen massiivid
        String[] tahekomplekt2 = new String[konteineriPrygiChars.length-2];
        //kasutaja prygist tehakse massiiv, kus on 3-tähelised kombinatsioonid sõnast, nt "piim": ["pii"; "iim"]
        for (int i = 0;  tahekomplekt1.length > i ; i++) {
            tahekomplekt1[i] = Character.toString(kasutajaPrygiChars[i])+Character.toString(kasutajaPrygiChars[i+1])+Character.toString(kasutajaPrygiChars[i+2]); //Character.toString(char)
        }
        //konteineri prygist tehakse massiiv, kus on 3-tähelised kombinatsioonid sõnast, nt "paber": [pab; abe; ber]
        for (int i = 0; i < tahekomplekt2.length; i++) {
            tahekomplekt2[i] = Character.toString(konteineriPrygiChars[i])+Character.toString(konteineriPrygiChars[i+1])+Character.toString(konteineriPrygiChars[i+2]);
        }
        //kontrollin, kas kasutaja prügi sisaldab konteineri prygiga sarnaseid tahekombinatsioone
        for (int i = 0; i < tahekomplekt1.length; i++) {
            for (int j = 0; j <tahekomplekt2.length; j++) {
                if(tahekomplekt1[i].equals(tahekomplekt2[j]) && ! voimalikPrygiList.contains(konteineriPrygi)){
                    voimalikPrygiList.add(konteineriPrygi);
                }
            }
        }
    }
}
