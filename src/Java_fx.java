import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Kirke on 24.11.2015.
 */
public class Java_fx extends Application {
    static String randomNipp;
    static Scanner sc;
    Konteiner paberPapp = new Konteiner("Paber ja kartong"); //loon uue Konteiner tyypi objekti, mille liik on paber ja papp
    Konteiner bio = new Konteiner("Biolagunevad jaatmed");   //loon uue Konteiner tyypi objekti, mille liik on biol. j22tmed
    Konteiner elektroonika = new Konteiner("Vanametall"); //loon uue Konteineri tyypi objekti, mille liik on vana elektroonika (k�lmkapid, arvutid, telekad)
    Konteiner ohtlikud = new Konteiner("Ohtlikud jaatmed"); //loon uue Konteineri tyypi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
    Konteiner pakend = new Konteiner("Segapakendid"); //loon uue Konteineri tyypi objekti, mille liik on Papp,kilepakendid,igast segapakendid ja pakkimisvahendid (kui ei ole sorteeritud, tuleb maksta)
    Konteiner ehitusprygi = new Konteiner("Ehitusprygi ja segaaatmed"); //Selle eest tuleb maksta j22tmejaamas. 20€ kuupmeeter.
    static int abiMuutuja = 0; //selleks, et vaadata, kas kasutaja sisestatud prygi on

    public void start(Stage primaryStage)throws Exception {
        primaryStage.setResizable(true);
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox, 500, 800);
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

        //eri liiki prygi listid l2hevad eri liiki konteineritesse
        paberPapp.setPrygi(jarjend(new File("paber.txt")));
        bio.setPrygi(jarjend(new File("bio.txt")));
        elektroonika.setPrygi(jarjend(new File("elekter.txt")));

        //nippide vaade (kohe ei n2ita)
        StackPane nipidLayout = new StackPane();
        Scene nipidScene = new Scene (nipidLayout, 900, 75);

        //mis prygi soovid sorteerida vaade (kohe ei n2ita)
        StackPane sobivKonteinerLayout = new StackPane();
        Scene sobivKonteinerScene = new Scene(sobivKonteinerLayout, 400,400);

        //Nipid nupp ACTION!
        nipidNupp.setOnAction(event -> {
            try {
                nippideJarjend();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Label nipp = new Label(randomNipp);
                nipidLayout.getChildren().add(nipp);
                primaryStage.setScene(nipidScene);

        });

        //sorteeri prygi ACTION!
        sorteeriNupp.setOnAction(event -> {
                    String input = kasutajaInput.getText();
                    String sobivKonteiner = "";
                    if (kuhuVisata(bio, input) != "") {
                        sobivKonteiner = kuhuVisata(bio, input);
                    } else if (kuhuVisata(elektroonika, input) != ""){
                        sobivKonteiner = kuhuVisata(elektroonika, input);
                    } else if (kuhuVisata(paberPapp, input)!= ""){
                        sobivKonteiner = kuhuVisata(paberPapp, input);
                    }
            Label sobivKonteiner2 = new Label(sobivKonteiner);
            sobivKonteinerLayout.getChildren().add(sobivKonteiner2);
            primaryStage.setScene(sobivKonteinerScene);
        });

        primaryStage.setTitle("Prykkar");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.exit(0));
    }

    // MEETODID ALGAVAD SIIT
    // nippide lugemine failist
    static void nippideJarjend () throws Exception {
        File nippideFail = new File("nipid.txt"); // txt failid peavad olema proj. samas kaustas
        sc = new Scanner(nippideFail);
        List<String> listNipid = new ArrayList<>();
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();//rida tuleb eraldi muutujasse salvestada
            listNipid.add(rida);}
        //System.out.println(listNipid.size());
        sc.close();

        randomNipp = listNipid.get((int) (Math.random() * (listNipid.size()))); //randomiga valin nipi
       // System.out.println(randomNipp);
    }
    //loeb failist prügi ja viskab selle arraylisti, mille ka tagastab
   public static ArrayList<String> jarjend(File fail) throws Exception {
        sc = new Scanner(fail);
        ArrayList<String> jaatmeList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();
            jaatmeList.add(rida);}
        sc.close();
        return jaatmeList;
    }
    //prindib välja konteineri sisu
    public static void prindiKonteineriList(List<String> prygiList) {
        StringBuilder sb = new StringBuilder();
        for (String s : prygiList) {
            sb.append(s);
            sb.append("\t");
        }
        System.out.println(prygiList.toString());
    }
    //ytleb kasutajale, millisesse konteinerisse prygi visata
    public static String kuhuVisata(Konteiner prygiKonteiner, String kasutajaPrygi) {
        String sobivKonteiner = "";
        for (int i = 0; prygiKonteiner.getPrygi().size() > i; i++) {
            if (prygiKonteiner.getPrygi().get(i).equals(kasutajaPrygi)) {//kontrollin, kas kasutaja prygi sobib antud konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
                //System.out.println("Viska see konteinerisse " + prygiKonteiner.getLiik());
                abiMuutuja++;
                sobivKonteiner = prygiKonteiner.getLiik();
            }
        }
        return sobivKonteiner;
    }
}
