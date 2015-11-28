import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.*;
/**
 * Created by kasutaja on 14.10.2015.
 */
public class TestPrykkar {

    static String randomNipp;  //annab kasutajale randomiga masiivist ühe hea nipi
    static int abiMuutuja = 0; //selleks, et vaadata, kas kasutaja sisestatud prygi on meil listides olemas
    static Scanner sc;
    static Stage stage;

    // nippide lugemine failist
    static void nippideJarjend() throws Exception {
        File nippideFail = new File("nipid.txt"); // txt failid peavad olema proj. samas kaustas
        sc = new Scanner(nippideFail);
        List<String> listNipid = new ArrayList<>();
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();//rida tuleb eraldi muutujasse salvestada
            listNipid.add(rida);
        }
        //System.out.println(listNipid.size());
        sc.close();

        randomNipp = listNipid.get((int) (Math.random() * (listNipid.size()))); //randomiga valin nipi
        System.out.println(randomNipp);
    }

    //loeb failist prügi ja viskab selle arraylisti, mille ka tagastab
    static List<String> jarjend(File fail) throws Exception {
        sc = new Scanner(fail);
        List<String> jaatmeList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();
            jaatmeList.add(rida);
        }
        sc.close();
        return jaatmeList;
    }

    //meetod prindib välja konteineri (listi) sisu
    public static void prindiKonteineriList(List<String> prygiList) {
        StringBuilder sb = new StringBuilder();
        for (String s : prygiList) {
            sb.append(s);
            sb.append("\t");
        }
        System.out.println(prygiList.toString());
    }

    //PEAMEETOD
    public static void main(String[] args) throws Exception {
        List<String> bioJarjend = new ArrayList<>(jarjend(new File("bio.txt"))); //iga prügiliigi kohta andmed eraldi failides hetkel. loetud prygi salvestan j2rjendisse
        List<String> pappJarjend = new ArrayList<>(jarjend(new File("paber.txt")));
        List<String> elekterJarjend = new ArrayList<>(jarjend(new File("elekter.txt")));

        Konteiner paberPapp = new Konteiner("Paber & Kartong"); //loon uue Konteiner tyypi objekti, mille liik on paber ja papp
        Konteiner bio = new Konteiner("Biolagunevad jaatmed");   //loon uue Konteiner tyypi objekti, mille liik on biol. j22tmed
        Konteiner elektroonika = new Konteiner("Vanametall"); //loon uue Konteineri tyypi objekti, mille liik on vana elektroonika (k�lmkapid, arvutid, telekad)
        Konteiner ohtlikud = new Konteiner("Ohtlikud jaatmed"); //loon uue Konteineri tyypi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
        Konteiner pakend = new Konteiner("Segapakendid"); //loon uue Konteineri tyypi objekti, mille liik on Papp,kilepakendid,igast segapakendid ja pakkimisvahendid (kui ei ole sorteeritud, tuleb maksta)
        Konteiner ehitusprygi = new Konteiner("Ehitusprygi ja segaaatmed"); //Selle eest tuleb maksta j22tmejaamas. 20€ kuupmeeter.

        //eri liiki prygi listid l2hevad eri liiki konteineritesse
        elektroonika.setPrygi(elekterJarjend);
        prindiKonteineriList(elektroonika.getPrygi());
        bio.setPrygi(bioJarjend);
        prindiKonteineriList(bio.getPrygi());
        paberPapp.setPrygi(pappJarjend);
        prindiKonteineriList(paberPapp.getPrygi());

        Scanner sc = new Scanner(System.in);
        System.out.println("\n Mis prygi soovid sorteerida?"); // kysin kasutajalt sisendit, mis prygi ta tahab sorteerida ja salvestan selle muutujasse "kasutajaPrygi"
        String kasutajaPrygi = sc.next();

        kuhuVisata(bio, kasutajaPrygi);
        kuhuVisata(elektroonika, kasutajaPrygi);
        kuhuVisata(paberPapp, kasutajaPrygi);

        if (abiMuutuja == 0) {
            System.out.println("Sorry, programm on alles poolik, ei leidnud hetkel sobivat konteinerit");
        }

        nippideJarjend();

    }

    public static void kuhuVisata(Konteiner prygiKonteiner, String kasutajaPrygi) {
        for (int i = 0; prygiKonteiner.getPrygi().size() > i; i++) {
            if (prygiKonteiner.getPrygi().get(i).equals(kasutajaPrygi)) {//kontrollin, kas kasutaja pr�gi sobib antud konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
                System.out.println("Viska see konteinerisse " + prygiKonteiner.getLiik());
                abiMuutuja++;
            }
        }
    }
}

