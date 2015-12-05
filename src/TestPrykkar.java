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
    static List<String> voimalikPrygiList = new ArrayList<>();

    // nippide lugemine failist
    static void nippideJarjend() throws Exception {
        File nippideFail = new File("nipid.txt"); // txt failid peavad olema proj. samas kaustas
        sc = new Scanner(nippideFail);
        List<String> listNipid = new ArrayList<>();
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();//rida tuleb eraldi muutujasse salvestada
            listNipid.add(rida);
        } //System.out.println(listNipid.size());
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
        } sc.close();
        return jaatmeList;
    }
    //meetod prindib välja konteineri (listi) sisu
    public static void prindiKonteineriList(List<String> prygiList) {
        StringBuilder sb = new StringBuilder();
        for (String s : prygiList) {
            sb.append(s);
            sb.append("\t");
        } System.out.println(prygiList.toString());
    }
    //PEAMEETOD
    public static void main(String[] args) throws Exception {
        //eri liiki prygi listid l2hevad eri liiki konteineritesse
        Konteiner paberPapp = new Konteiner("Paber & Kartong"); //loon uue Konteiner tyypi objekti, mille liik on paber ja papp
        paberPapp.setPrygi(jarjend(new File("paber.txt")));
        Konteiner bio = new Konteiner("Biolagunevad jaatmed");   //loon uue Konteiner tyypi objekti, mille liik on biol. j22tmed
        bio.setPrygi(jarjend(new File("bio.txt")));
        Konteiner elektroonika = new Konteiner("Vanametall"); //loon uue Konteineri tyypi objekti, mille liik on vana elektroonika (k�lmkapid, arvutid, telekad)
        elektroonika.setPrygi(jarjend(new File("elekter.txt")));
        Konteiner ohtlikud = new Konteiner("Ohtlikud jaatmed"); //loon uue Konteineri tyypi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
        Konteiner pakend = new Konteiner("Segapakendid"); //loon uue Konteineri tyypi objekti, mille liik on Papp,kilepakendid,igast segapakendid ja pakkimisvahendid (kui ei ole sorteeritud, tuleb maksta)
        Konteiner ehitusprygi = new Konteiner("Ehitusprygi ja segaaatmed"); //Selle eest tuleb maksta j22tmejaamas. 20€ kuupmeeter.

        prindiKonteineriList(elektroonika.getPrygi());
        prindiKonteineriList(bio.getPrygi());
        prindiKonteineriList(paberPapp.getPrygi());

        Scanner sc = new Scanner(System.in);
        System.out.println("\n Mis prygi soovid sorteerida?"); // kysin kasutajalt sisendit, mis prygi ta tahab sorteerida ja salvestan selle muutujasse "kasutajaPrygi"
        String kasutajaPrygi = sc.next();

        kuhuVisata(bio, kasutajaPrygi);
        kuhuVisata(elektroonika, kasutajaPrygi);
        kuhuVisata(paberPapp, kasutajaPrygi);
        //sarnanePrygiNimi(kasutajaPrygi, "televiisor");

        if (abiMuutuja == 0) {
            if (voimalikPrygiList.isEmpty()) {
                System.out.println("Sorry, programm on alles poolik, ei leidnud hetkel sobivat konteinerit");
            } else {
                System.out.println(voimalikPrygiList.toString());
            }
        }

        nippideJarjend();
    }

    public static void kuhuVisata(Konteiner prygiKonteiner, String kasutajaPrygi) {
        for (int i = 0; prygiKonteiner.getPrygi().size() > i; i++) {
            if (prygiKonteiner.getPrygi().get(i).equals(kasutajaPrygi)) {//kontrollin, kas kasutaja prygi sobib antud konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
                System.out.println("Viska see konteinerisse " + prygiKonteiner.getLiik());
                abiMuutuja++;
            }
            else{
                sarnanePrygiNimi(kasutajaPrygi, prygiKonteiner.getPrygi().get(i));
            }
        }
    }
    public static void sarnanePrygiNimi (String kasutajaPrygi, String konteineriPrygi) {
        char[] kasutajaPrygiChars = kasutajaPrygi.toCharArray();
        //System.out.println(Arrays.toString(kasutajaPrygiChars));
        char[] konteineriPrygiChars = konteineriPrygi.toCharArray();
        //System.out.println(Arrays.toString(konteineriPrygiChars));
        String[] tahekomplekt1 = new String[kasutajaPrygiChars.length-2];  //kontrollin kattuvusi 3-tahelistes kombinatsioonides, selleks teen massiivid
        String[] tahekomplekt2 = new String[konteineriPrygiChars.length-2];
        //kasutaja prygist tehakse massiiv, kus on 3-tähelised kombinatsioonid sõnast, nt "piim": ["pii"; "iim"]
        for (int i = 0;  tahekomplekt1.length > i ; i++) {
            tahekomplekt1[i] = Character.toString(kasutajaPrygiChars[i])+Character.toString(kasutajaPrygiChars[i+1])+Character.toString(kasutajaPrygiChars[i+2]); //Character.toString(char)
        } //System.out.println(Arrays.toString(tahekomplekt1));
        //konteineri prygist tehakse massiiv, kus on 3-tähelised kombinatsioonid sõnast, nt "paber": [pab; abe; ber]
        for (int i = 0; i < tahekomplekt2.length; i++) {
            tahekomplekt2[i] = Character.toString(konteineriPrygiChars[i])+Character.toString(konteineriPrygiChars[i+1])+Character.toString(konteineriPrygiChars[i+2]);
        } //System.out.println(Arrays.toString(tahekomplekt2));
        //kontrollin, kas kasutaja prügi sisaldab konteineri prygiga sarnaseid tahekombinatsioone
        for (int i = 0; i < tahekomplekt1.length; i++) {
            for (int j = 0; j <tahekomplekt2.length; j++) {
                if(tahekomplekt1[i].equals(tahekomplekt2[j])&& ! voimalikPrygiList.contains(konteineriPrygi)){
                    voimalikPrygiList.add(konteineriPrygi);
                }
            }
        }
    }
}



