import java.io.File;
import java.util.Arrays;
import java.util.*;
import java.lang.*;
/**
 * Created by kasutaja on 14.10.2015.
 */
public class TestPrykkar {

    static String randomNipp;

    // nippide lugemine failist
    static void nippideJarjend () throws Exception {
        File nippideFail = new File("nipid.txt"); // txt failid peavad olema proj. samas kaustas
        Scanner sc1 = new Scanner(nippideFail);
        List<String> listNipid = new ArrayList<String>();
        while (sc1.hasNextLine()) {
            String rida = sc1.nextLine();//rida tuleb eraldi muutujasse salvestada
            listNipid.add(rida);
        }
        //System.out.println(listNipid.size());
        sc1.close();

        randomNipp = listNipid.get((int) (Math.random() * (listNipid.size()))); //randomiga valin nipi
        System.out.println(randomNipp);
    }


    //PEAMEETOD

    public static void main(String[] args) throws Exception {

        Konteiner paberPapp = new Konteiner("Paber & Kartong"); //loon uue Konteiner tüüpi objekti, mille liik on paber ja papp
        Konteiner bio = new Konteiner("Biolagunevad jäätmed");   //loon uue Konteiner tüüpi objekti, mille liik on biol. jäätmed
        Konteiner elektroonika = new Konteiner("Vanametall"); //loon uue Konteineri tüüpi objekti, mille liik on vana elektroonika (külmkapid, arvutid, telekad)
        Konteiner ohtlikud = new Konteiner("Ohtlikud jäätmed"); //loon uue Konteineri tüüpi objekti, mille liik on ohtlikud jäätmed (värvid, kodukeemia, akud) (NB! nende vastuvõtmine on piiratud koguseliselt)
        Konteiner pakend = new Konteiner("Segapakendid"); //loon uue Konteineri tüüpi objekti, mille liik on Papp,kilepakendid,igast segapakendid ja pakkimisvahendid (kui ei ole sorteeritud, tuleb maksta)
        Konteiner ehitusprygi = new Konteiner("Ehitusprügi ja segajäätmed"); //Selle eest tuleb maksta jäätmejaamas. 20€ kuupmeeter.

        String [] n2idisprygi = new String[]{"paber", "ajaleht", "pappkast", "vihik", "paberkott", "kataloog", "raamat", "äää"}; // teoorias võiks lubatud konteineri sisu tulla mõnest failist;
        String [] bion2idis = new String[]{"kartulikoored", "kohvipaks", "kompott", "potimuld"};

        paberPapp.setPrygi(n2idisprygi);
        System.out.println(Arrays.toString(paberPapp.getPrygi())); //prügikasti sobiva prügi saab välja printida nii
        bio.setPrygi(bion2idis);
        System.out.println(Arrays.toString(bio.getPrygi()));
        //või nii:
       /* for (int i=0; paberPapp.getPrygi().length >i; i++){
            System.out.println(paberPapp.getPrygi()[i]);

        }
*/
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Mis prügi soovid sorteerida?"); // küsin kasutajalt sisendit
        String kasutajaPrygi = sc.next();

        int abiMuutuja = 0;
        for (int i=0; paberPapp.getPrygi().length >i; i++){
            //kontrollin, kas kasutaja prügi sobib paberi&papi konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
            if(kasutajaPrygi.equals(paberPapp.getPrygi()[i]))
                System.out.println("Viska see konteinerisse paber & papp"); //+ paberPapp.getLiik())
            if(kasutajaPrygi.equals(bio.getPrygi()[i]))
                System.out.println("Viska see konteinerisse Biolagunevad jäätmed");
            else{
                abiMuutuja++;
            }
            if (abiMuutuja == paberPapp.getPrygi().length){
                System.out.println("Sorry, programm on alles poolik, varsti ytlen, kuhu visata!");
            }
        }
        nippideJarjend();
    }
}
