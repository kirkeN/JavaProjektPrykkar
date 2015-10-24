import java.io.File;
import java.util.Arrays;
import java.util.*;
/**
 * Created by kasutaja on 14.10.2015.
 */
public class TestPrykkar {

    static String randomNipp;
    static int abiMuutuja = 0; //selleks, et vaadata, kas kasutaja sisestatud prygi on meil listides olemas

    // nippide lugemine failist
    static void nippideJarjend () throws Exception {
        File nippideFail = new File("nipid.txt"); // txt failid peavad olema proj. samas kaustas
        Scanner sc1 = new Scanner(nippideFail);
        List<String> listNipid = new ArrayList<>();
        while (sc1.hasNextLine()) {
            String rida = sc1.nextLine();//rida tuleb eraldi muutujasse salvestada
            listNipid.add(rida);}
        //System.out.println(listNipid.size());
        sc1.close();

        randomNipp = listNipid.get((int) (Math.random() * (listNipid.size()))); //randomiga valin nipi
        System.out.println(randomNipp);
    }

    static List<String> jarjend(File fail) throws Exception {
        Scanner sc0 = new Scanner(fail);
        List<String> jaatmeList = new ArrayList<>();
        while (sc0.hasNextLine()) {
            String rida = sc0.nextLine();
            jaatmeList.add(rida);}
        sc0.close();
        return jaatmeList;
    }

    //PEAMEETOD
    public static void main(String[] args) throws Exception {
        File elekterFail = new File("elekter.txt"); //iga prygiliigi kohta andmed eri failides hetkel
        File pappFail = new File("paber.txt");
        File bioFail = new File("bio.txt");
        List<String> bioJarjend = new ArrayList<>(jarjend(bioFail)); //failist loetud prygi salvestan j2rjendisse
        List<String> pappJarjend = new ArrayList<>(jarjend(pappFail));
        List<String> elekterJarjend = new ArrayList<>(jarjend(elekterFail));

        Konteiner paberPapp = new Konteiner("Paber & Kartong"); //loon uue Konteiner tüüpi objekti, mille liik on paber ja papp
        Konteiner bio = new Konteiner("Biolagunevad jäätmed");   //loon uue Konteiner tüüpi objekti, mille liik on biol. jäätmed
        Konteiner elektroonika = new Konteiner("Vanametall"); //loon uue Konteineri tüüpi objekti, mille liik on vana elektroonika (külmkapid, arvutid, telekad)
        Konteiner ohtlikud = new Konteiner("Ohtlikud jäätmed"); //loon uue Konteineri tüüpi objekti, mille liik on ohtlikud jÃ¤Ã¤tmed (vÃ¤rvid, kodukeemia, akud) (NB! nende vastuvÃµtmine on piiratud koguseliselt)
        Konteiner pakend = new Konteiner("Segapakendid"); //loon uue Konteineri tüüpi objekti, mille liik on Papp,kilepakendid,igast segapakendid ja pakkimisvahendid (kui ei ole sorteeritud, tuleb maksta)
        Konteiner ehitusprygi = new Konteiner("Ehitusprügi ja segaäätmed"); //Selle eest tuleb maksta jäätmejaamas. 20â‚¬ kuupmeeter.


        String [] pappN2idis = new String [pappJarjend.size()];
        for (int i = 0; pappJarjend.size() > i; i++) {
            pappN2idis[i]=(pappJarjend.get(i));
        }
        String [] bioN2idis = new String [bioJarjend.size()];
        for (int i = 0; bioJarjend.size() > i; i++) {
            bioN2idis[i]=(bioJarjend.get(i));
        }
        String [] eleN2idis = new String [elekterJarjend.size()];
        for (int i = 0; elekterJarjend.size() > i; i++) {
            eleN2idis[i]=(elekterJarjend.get(i));
        }

        elektroonika.setPrygi(eleN2idis); //eri liiki prygi massiivid lähevad eri liiki konteineritesse
        System.out.println(Arrays.toString(elektroonika.getPrygi()));
        bio.setPrygi(bioN2idis);
        System.out.println(Arrays.toString(bio.getPrygi()));
        paberPapp.setPrygi(pappN2idis);
        System.out.println(Arrays.toString(paberPapp.getPrygi()));

        //või nii:
        /*
       for (int i=0; paberPapp.getPrygi().length >i; i++){
            System.out.println(paberPapp.getPrygi()[i]);
        }
        */

        Scanner sc = new Scanner(System.in);
        System.out.println("\n Mis prügi soovid sorteerida?"); // küsin kasutajalt sisendit
        String kasutajaPrygi = sc.next();

        kuhuVisata(bio, kasutajaPrygi);
        kuhuVisata(elektroonika, kasutajaPrygi);
        kuhuVisata(paberPapp, kasutajaPrygi);

        if (abiMuutuja == 0) {
            System.out.println("Sorry, programm on alles poolik, ei leidnud hetkel sobivat konteinerit");
        }

        nippideJarjend ();
    }
    public static void kuhuVisata(Konteiner prygiKonteiner, String kasutajaPrygi) {
        for (int i = 0; prygiKonteiner.getPrygi().length > i; i++) {
            if (kasutajaPrygi.equals(prygiKonteiner.getPrygi()[i])) {//kontrollin, kas kasutaja prügi sobib antud konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
                System.out.println("Viska see konteinerisse " + prygiKonteiner.getLiik());
                abiMuutuja++;
            }
        }
    }
}
