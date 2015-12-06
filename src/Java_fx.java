import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Kirke on 24.11.2015.
 */
public class Java_fx extends Application {
    static String randomNipp;
    static Scanner sc;
    static List<String> voimalikPrygiList = new ArrayList<>(); //kasutaja poolt sisestatud prygiga sarnaste sonede list

    Konteiner paberPapp = new Konteiner("Paber ja kartong"); //loon uue Konteiner tyypi objekti, mille liik on paber ja papp
    Konteiner bio = new Konteiner("Biolagunevad jaatmed");   //loon uue Konteiner tyypi objekti, mille liik on biol. j22tmed
    Konteiner elektroonika = new Konteiner("Vanametall"); //loon uue Konteineri tyypi objekti, mille liik on vana elektroonika (k�lmkapid, arvutid, telekad)
    Konteiner ohtlikud = new Konteiner("Ohtlikud jaatmed"); //loon uue Konteineri tyypi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
    Konteiner pakend = new Konteiner("Segapakendid"); //loon uue Konteineri tyypi objekti, mille liik on Papp,kilepakendid,igast segapakendid ja pakkimisvahendid (kui ei ole sorteeritud, tuleb maksta)
    Konteiner ehitusprygi = new Konteiner("Ehitusprygi ja segaaatmed"); //Selle eest tuleb maksta j22tmejaamas. 20€ kuupmeeter.

    public void start(Stage primaryStage)throws Exception {
        primaryStage.setResizable(true);
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox, 500, 200);
        primaryStage.setScene(scene);

        //Visuaalid
        Label kysimus = new Label("Mida soovid sorteerida?");
        TextField kasutajaInput = new TextField();
        Button sorteeriNupp = new Button("Sorteeri!");
        Button paberNupp = new Button("Paber ja kartong");
        Button bioNupp = new Button("Biolagunevad jäätmed");
        Button metallNupp = new Button("Vanametall");
        Button nipidNupp = new Button("Nipid");
        vbox.setSpacing(5);
        vbox.getChildren().addAll(kysimus, kasutajaInput,sorteeriNupp, paberNupp, bioNupp, metallNupp, nipidNupp);

        Button tagasiNupp = new Button("Tagasi");

        //eri liiki prygi listid l2hevad eri liiki konteineritesse
        paberPapp.setPrygi(jarjend(new File("paber.txt")));
        bio.setPrygi(jarjend(new File("bio.txt")));
        elektroonika.setPrygi(jarjend(new File("elekter.txt")));

        //mis prygi soovid sorteerida vaated (kohe ei n2ita)
        VBox sobivKonteinerLayout = new VBox();
        Scene sobivKonteinerScene = new Scene(sobivKonteinerLayout, 400,400);

        //"Sorteeri!" nupp ACTION!
        sorteeriNupp.setOnAction(event -> {
            String input = kasutajaInput.getText();
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
            }
            Label sobivKonteinerLabel = new Label(sobivKonteiner);
            sobivKonteinerLayout.getChildren().addAll(sobivKonteinerLabel, tagasiNupp);
            primaryStage.setScene(sobivKonteinerScene);
            tagasiNupp.setOnAction(event2 -> {
                sobivKonteinerLayout.getChildren().remove(sobivKonteinerLabel);
                primaryStage.setScene(scene);
            }); // -- EI TÖÖTA! ilmselt vaja muuta kasutajaInput interaktiivseks vms

        });

        //konteineri sisu vaade (kohe ei n2ita)
        // #1 Bio
        VBox bioj22tmedLayout = new VBox();
        Scene bioj22tmedScene = new Scene(bioj22tmedLayout, 400, 600);
        // #2 Paber
        VBox paberLayout = new VBox();
        Scene paberScene = new Scene(paberLayout, 400, 600);
        // #3 elektroonika
        VBox elektroonikaLayout = new VBox();
        Scene elektroonikaScene = new Scene(elektroonikaLayout, 400, 600);

        //"Biolagunevad j22tmed" nupp ACTION!
        bioNupp.setOnAction(event -> {
            Label bioLabel = new Label(prindiKonteineriList(bio).toString());
            bioj22tmedLayout.getChildren().addAll(bioLabel, tagasiNupp);
            primaryStage.setScene(bioj22tmedScene);
            tagasiNupp.setOnAction(event2 -> {
                bioj22tmedLayout.getChildren().remove(bioLabel);
                primaryStage.setScene(scene);
            });
        });
        //"Paber ja kartong" nupp ACTION!
        paberNupp.setOnAction(event -> {
            Label paberLabel = new Label(prindiKonteineriList(paberPapp).toString());
            paberLayout.getChildren().addAll(paberLabel, tagasiNupp);
            primaryStage.setScene(paberScene);
            tagasiNupp.setOnAction(event2 -> {
                paberLayout.getChildren().remove(paberLabel);
                primaryStage.setScene(scene);
            });
        });
        //"Vanametall" nupp ACTION!
        metallNupp.setOnAction(event -> {
            Label elektroonikaLabel = new Label(prindiKonteineriList(elektroonika).toString());
            elektroonikaLayout.getChildren().addAll(elektroonikaLabel, tagasiNupp);
            primaryStage.setScene(elektroonikaScene);
            tagasiNupp.setOnAction(event2 -> {
                elektroonikaLayout.getChildren().remove(elektroonikaLabel);
                primaryStage.setScene(scene);
            });
        });

        //nippide vaade (kohe ei n2ita)
        VBox nipidLayout = new VBox();
        Scene nipidScene = new Scene (nipidLayout, 500, 200);

        //"Nipid" nupp ACTION!
        nipidNupp.setOnAction(event -> {
            try {
                nippideJarjend();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Label nipp = new Label(randomNipp);
            nipp.setWrapText(true);
                nipidLayout.getChildren().addAll(nipp, tagasiNupp);
                primaryStage.setScene(nipidScene);
            tagasiNupp.setOnAction(event2 -> {
                nipidLayout.getChildren().remove(nipp);
                primaryStage.setScene(scene);
            });
        });

        primaryStage.setTitle("Prykkar");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.exit(0));
    } //STAGE LÕPP

    // MEETODID ALGAVAD SIIT
    // nippide lugemine failist
    static void nippideJarjend () throws Exception {
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
   public static ArrayList<String> jarjend(File fail) throws Exception {
        sc = new Scanner(fail);
        ArrayList<String> jaatmeList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();
            jaatmeList.add(rida);}
        sc.close();
        return jaatmeList;
    }
    //MEETOD "prindiKonteineriList": prindib välja konteineri sisu
    public static StringBuilder prindiKonteineriList(Konteiner konteiner) {
        List<String> prygiList = konteiner.getPrygi();
        Collections.sort(prygiList);
        StringBuilder sb = new StringBuilder();
        for (String s : prygiList) {
            sb.append(s);
            sb.append("\n");
        }return sb;
    }
    //MEETOD "prindiArrayList": prindib välja arraylisti sisu
    public static StringBuilder prindiArrayList(List<String> massiiv) {
        StringBuilder sb = new StringBuilder();
        for (String s : massiiv) {
            sb.append(s);
            sb.append("\n");
        }return sb;
    }
    //MEETOD "kuhuVisata": ytleb kasutajale, millisesse konteinerisse prygi visata
    public static String kuhuVisata(Konteiner prygiKonteiner, String kasutajaPrygi) {
        String sobivKonteiner = "";
        for (int i = 0; prygiKonteiner.getPrygi().size() > i; i++) {
            if (prygiKonteiner.getPrygi().get(i).equals(kasutajaPrygi)) {//kontrollin, kas kasutaja prygi sobib antud konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
                sobivKonteiner = prygiKonteiner.getLiik();
            }
            else{
                sarnanePrygiNimi(kasutajaPrygi, prygiKonteiner.getPrygi().get(i)); //kui tapset vastet konteinerist ei leita, otsitakse sanraseid t2hekombinatsioone sisaldavaid vasteid; meetod meetodi sees
            }
        } return sobivKonteiner;
    }
    // MEETOD "sarnanePrygiNimi" : kui t2pset kasutaja sisestatud sone ei leita, siis hakatakse otsima sarnast prygi
    public static void sarnanePrygiNimi (String kasutajaPrygi, String konteineriPrygi) {
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
