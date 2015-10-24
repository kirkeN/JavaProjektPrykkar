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
    static void bioJarjend () throws Exception {
        File bioFail = new File("bio.txt");
        Scanner sc2 = new Scanner(bioFail);
        List<String> listBio = new ArrayList<>();
        while (sc2.hasNextLine()) {
            String rida = sc2.nextLine();
            listBio.add(rida);
        }
        System.out.println(listBio);
        sc2.close();

    }

    //PEAMEETOD
    public static void main(String[] args) throws Exception {

        Konteiner paberPapp = new Konteiner("Paber & Kartong"); //loon uue Konteiner tüüpi objekti, mille liik on paber ja papp
        Konteiner bio = new Konteiner("Biolagunevad jäätmed");   //loon uue Konteiner tüüpi objekti, mille liik on biol. jäätmed
        Konteiner elektroonika = new Konteiner("Vanametall"); //loon uue Konteineri tüüpi objekti, mille liik on vana elektroonika (külmkapid, arvutid, telekad)
        Konteiner ohtlikud = new Konteiner("Ohtlikud jäätmed"); //loon uue Konteineri tüüpi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
        Konteiner pakend = new Konteiner("Segapakendid"); //loon uue Konteineri tüüpi objekti, mille liik on Papp,kilepakendid,igast segapakendid ja pakkimisvahendid (kui ei ole sorteeritud, tuleb maksta)
        Konteiner ehitusprygi = new Konteiner("Ehitusprügi ja segajäätmed"); //Selle eest tuleb maksta jäätmejaamas. 20€ kuupmeeter.

        String [] n2idisprygi = new String[]{"paber", "ajaleht", "pappkast", "vihik", "paberkott", "kataloog", "raamat"}; // teoorias võiks lubatud konteineri sisu tulla mõnest failist;
        String [] elen2idis = new String[]{"televiisor", "pesumasin", "arvuti", "külmkapp", "robotkoer"};



        bioJarjend (); //loeb bioloogilised jäätmed failist sisse ja kuvab need siia.
        paberPapp.setPrygi(n2idisprygi);
        System.out.println(Arrays.toString(paberPapp.getPrygi())); //prügikasti sobiva prügi saab välja printida nii
        elektroonika.setPrygi(elen2idis);
        System.out.println(Arrays.toString(elektroonika.getPrygi()));

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
   //     for (int j=0; bio.getPrygi().length > j; j++){
     //       if (kasutajaPrygi.equals(bio.getPrygi()[j])) {
       //         System.out.println("Viska see konteinerisse Biolagunevad jäätmed");}}
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
