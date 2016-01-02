import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Kirke on 15.12.2015.
 */
public class Nipp {
    public String nipp;  //nipp - n6uanne, mis aitab vähem prygi tekitada, keskkonnas6bralikum olla.
    Nipp(String nipp) {  //nipi konstruktor
        this.nipp = nipp;
    }
    String getNipp() {
        return nipp;
    }
    void setNipp(String nipp){
        this.nipp = nipp;
    }

    // nippide lugemine failist ja yhe v22rt nipi tagastamine :)
    public String nippideJarjend () throws Exception {
        File nippideFail = new File("nipid.txt"); // txt failid peavad olema proj. samas kaustas antud juhul
        Scanner sc = new Scanner(nippideFail);
        List<String> listNipid = new ArrayList<>(); //failist loetud nipid salvestatakse kausta
        while (sc.hasNextLine()) {
            String rida = sc.nextLine();//rida tuleb eraldi muutujasse salvestada
            listNipid.add(rida);}
        sc.close();
        return listNipid.get((int) (Math.random() * (listNipid.size()))); //randomiga valitakse nipp
    }

}
