import java.io.File;
import java.util.Arrays;
import java.util.*;
/**
 * Created by kasutaja on 14.10.2015.
 */
public class TestPrykkar {

    static String randomNipp;

    // nippide lugemine failist
    static void nippideJarjend () throws Exception {
        File nippideFail = new File("nipid.txt"); // txt failid peavad olema proj. samas kaustas
        Scanner sc1 = new Scanner(nippideFail);
        List<String> listNipid = new ArrayList<>();
        while (sc1.hasNextLine()) {
            String rida = sc1.nextLine();//rida tuleb eraldi muutujasse salvestada
            listNipid.add(rida);
        }
        //System.out.println(listNipid.size());
        sc1.close();

        randomNipp = listNipid.get((int) (Math.random() * (listNipid.size()))); //randomiga valin nipi
        System.out.println(randomNipp);
    }
    static List<String> bioJarjend() throws Exception {
        File bioFail = new File("bio.txt");
        Scanner sc2 = new Scanner(bioFail);
        List<String> listBio = new ArrayList<>();
        while (sc2.hasNextLine()) {
            String rida = sc2.nextLine();
            listBio.add(rida);
        }
        sc2.close();
        return listBio;

    }
    static List<String> pappJarjend() throws Exception {
        File pappFail = new File("paber.txt");
        Scanner sc3 = new Scanner(pappFail);
        List<String> listPapp = new ArrayList<>();
        while (sc3.hasNextLine()) {
            String rida = sc3.nextLine();
            listPapp.add(rida);
        }
        sc3.close();
        return listPapp;

    }
    static List<String> elekterJarjend() throws Exception {
        File elekterFail = new File("elekter.txt");
        Scanner sc4 = new Scanner(elekterFail);
        List<String> listElekter = new ArrayList<>();
        while (sc4.hasNextLine()) {
            String rida = sc4.nextLine();
            listElekter.add(rida);
        }
        sc4.close();
        return listElekter;

    }

    //PEAMEETOD
    public static void main(String[] args) throws Exception {

        Konteiner paberPapp = new Konteiner("Paber & Kartong"); //loon uue Konteiner tüüpi objekti, mille liik on paber ja papp
        Konteiner bio = new Konteiner("Biolagunevad jäätmed");   //loon uue Konteiner tüüpi objekti, mille liik on biol. jäätmed
        Konteiner elektroonika = new Konteiner("Vanametall"); //loon uue Konteineri tüüpi objekti, mille liik on vana elektroonika (külmkapid, arvutid, telekad)
        Konteiner ohtlikud = new Konteiner("Ohtlikud jäätmed"); //loon uue Konteineri tüüpi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
        Konteiner pakend = new Konteiner("Segapakendid"); //loon uue Konteineri tüüpi objekti, mille liik on Papp,kilepakendid,igast segapakendid ja pakkimisvahendid (kui ei ole sorteeritud, tuleb maksta)
        Konteiner ehitusprygi = new Konteiner("Ehitusprügi ja segajäätmed"); //Selle eest tuleb maksta jäätmejaamas. 20€ kuupmeeter.

        String [] elen2idis = new String[]{"televiisor", "pesumasin", "arvuti", "külmkapp", "robotkoer"};

        String [] pappN2idis = new String [pappJarjend().size()];
        for (int i = 0; pappJarjend().size() > i; i++) {
            pappN2idis[i]=(pappJarjend().get(i));
        }
        String [] bioN2idis = new String [bioJarjend().size()];
        for (int i = 0; bioJarjend().size() > i; i++) {
            bioN2idis[i]=(bioJarjend().get(i));
        }
        String [] eleN2idis = new String [elekterJarjend().size()];
        for (int i = 0; elekterJarjend().size() > i; i++) {
            eleN2idis[i]=(elekterJarjend().get(i));
        }

        elektroonika.setPrygi(elen2idis);
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

        int abiMuutuja = 0;
        for (int i=0; paberPapp.getPrygi().length >i; i++){
            if(kasutajaPrygi.equals(paberPapp.getPrygi()[i])) {//kontrollin, kas kasutaja prügi sobib paberi&papi konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
                System.out.println("Viska see konteinerisse paber & papp");}} //+ paberPapp.getLiik())
        for (int j=0; bio.getPrygi().length > j; j++){
            if (kasutajaPrygi.equals(bio.getPrygi()[j])) {
                System.out.println("Viska see konteinerisse Biolagunevad jäätmed");}}
        for (int g=0; elektroonika.getPrygi().length > g; g++){
            if (kasutajaPrygi.equals(elektroonika.getPrygi()[g])) {
                System.out.println("Tõsta see konteinerisse elektroonika");
            }
            else{
                abiMuutuja++;
            }
        }
        if (abiMuutuja == paberPapp.getPrygi().length){
            System.out.println("Sorry, programm on alles poolik, varsti ytlen, kuhu visata!");
        }
        nippideJarjend();
    }
}