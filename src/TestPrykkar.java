import java.util.Arrays;
import java.util.*;
/**
 * Created by kasutaja on 14.10.2015.
 */
public class TestPrykkar {
    public static void main(String[] args) {

        Konteiner paberPapp = new Konteiner("Paber & Kartong"); //loon uue Konteiner tüüpi objekti, mille liik on paber ja papp
        Konteiner bio = new Konteiner("Biolagunevad jäätmed");   //loon uue Konteiner tüüpi objekti, mille liik on biol. jäätmed

        String [] n2idisprygi = new String[]{"paber", "ajaleht", "pappkast", "vihik", "paberkott", "kataloog", "raamat"}; // teoorias võiks lubatud konteineri sisu tulla mõnest failist

        paberPapp.setPrygi(n2idisprygi);
        System.out.println(Arrays.toString(paberPapp.getPrygi())); //prügikasti sobiva prügi saab välja printida nii

        //või nii:
        for (int i=0; paberPapp.getPrygi().length >i; i++){
            System.out.println(paberPapp.getPrygi()[i]);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("\n Mis prügi soovid sorteerida?"); // küsin kasutajalt sisendit
        String kasutajaPrygi = sc.next();

        int abiMuutuja = 0;
        for (int i=0; paberPapp.getPrygi().length >i; i++){
            if(kasutajaPrygi.equals(paberPapp.getPrygi()[i])) {//kontrollin, kas kasutaja prügi sobib paberi&papi konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
                System.out.println("Viska see konteinerisse paber & papp"); //+ paberPapp.getLiik())
            }
            else{
                abiMuutuja++;
            }
            if (abiMuutuja == paberPapp.getPrygi().length){
                System.out.println("Sorry, programm on alles poolik, varsti ytlen, kuhu visata!");
            }
        }
    }
}
