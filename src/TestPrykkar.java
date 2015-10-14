import java.util.Arrays;

/**
 * Created by kasutaja on 14.10.2015.
 */
public class TestPrykkar {
    public static void main(String[] args) {

        Konteiner paberPapp = new Konteiner("Paber & Kartong");
        Konteiner bio = new Konteiner("Biolagunevad j‰‰tmed");

        String [] n2idisprygi = new String[]{"paber", "ajaleht", "pappkast", "vihik", "paberkott", "kataloog", "raamat"}; // teoorias vıiks lubatud konteineri sisu tulla mınest failist

        paberPapp.setPrygi(n2idisprygi);
        System.out.println(Arrays.toString(paberPapp.getPrygi()));
        

    }
}
