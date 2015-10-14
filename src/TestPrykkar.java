import java.util.Arrays;
import java.util.*;
/**
 * Created by kasutaja on 14.10.2015.
 */
public class TestPrykkar {
    public static void main(String[] args) {

        Konteiner paberPapp = new Konteiner("Paber & Kartong"); //loon uue Konteiner t��pi objekti, mille liik on paber ja papp
        Konteiner bio = new Konteiner("Biolagunevad j��tmed");   //loon uue Konteiner t��pi objekti, mille liik on biol. j��tmed

        String [] n2idisprygi = new String[]{"paber", "ajaleht", "pappkast", "vihik", "paberkott", "kataloog", "raamat"}; // teoorias v�iks lubatud konteineri sisu tulla m�nest failist

        paberPapp.setPrygi(n2idisprygi);
        System.out.println(Arrays.toString(paberPapp.getPrygi())); //pr�gikasti sobiva pr�gi saab v�lja printida nii

        //v�i nii:
        for (int i=0; paberPapp.getPrygi().length >i; i++){
            System.out.println(paberPapp.getPrygi()[i]);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("\n Mis pr�gi soovid sorteerida?"); // k�sin kasutajalt sisendit
        String kasutajaPrygi = sc.next();

        int abiMuutuja = 0;
        for (int i=0; paberPapp.getPrygi().length >i; i++){
            if(kasutajaPrygi.equals(paberPapp.getPrygi()[i])) {//kontrollin, kas kasutaja pr�gi sobib paberi&papi konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
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
