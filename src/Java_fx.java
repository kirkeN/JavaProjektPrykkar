import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Kirke on 24.11.2015.
 */
public class Java_fx extends Application {
    static String randomNipp;

    public void start(Stage primaryStage)throws Exception {
        primaryStage.setResizable(true);
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox, 500, 800);
        primaryStage.setScene(scene);

        //Visuaalid
        Label kysimus = new Label("Mida soovid sorteerida?");
        TextField kasutajaInput = new TextField();
        Button nipidNupp = new Button("Nipid");
        vbox.setSpacing(5);
        vbox.getChildren().addAll(kysimus, kasutajaInput,nipidNupp);

        //nippide vaade (kohe ei n2ita)
        StackPane nipidLayout = new StackPane();
        Scene nipidScene = new Scene (nipidLayout, 900, 75);



        //Nipid nupp ACTION!
        nipidNupp.setOnAction(event -> {
                File nippideFail = new File("nipid.txt"); // txt failid peavad olema proj. samas kaustas
            Scanner sc = null;
            try {
                sc = new Scanner(nippideFail);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            List<String> listNipid = new ArrayList<>();
                while (sc.hasNextLine()) {
                    String rida = sc.nextLine();//rida tuleb eraldi muutujasse salvestada
                    listNipid.add(rida);}
                sc.close();
                randomNipp = listNipid.get((int) (Math.random() * (listNipid.size()))); //randomiga valin nipi
                //System.out.println(randomNipp);
                Label nipp = new Label(randomNipp);
                nipidLayout.getChildren().add(nipp);
                primaryStage.setScene(nipidScene);

    });

        primaryStage.setTitle("Prykkar");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.exit(0));
    }


}
