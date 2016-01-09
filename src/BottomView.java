import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * BorderPane Bottom view
 */
public class BottomView {
    static Button nipidNupp;
    static Nipp randomNipp;
    static VBox bottomBox;
    static VBox nipidLayout;

    public static void bottomVisuaalid() {
        bottomBox = new VBox();
        bottomBox.setSpacing(5);
        bottomBox.setPrefHeight(80);
        nipidLayout = new VBox(); //läheb bottomBoxi BorderPane's

        nipidNupp = new Button("Nipp");

        nupuvajutus();

        bottomBox.getChildren().addAll(nipidNupp, nipidLayout);
        Java_fx.border.setBottom(bottomBox);
    }

    public static void nupuvajutus() {
        //"Nipid" nupp ACTION!
        nipidNupp.setOnAction(event -> {
            nipidLayout.getChildren().clear();
            try {
                randomNipp = new Nipp("uus nipp");
                randomNipp.setNipp(randomNipp.nippideJarjend());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Label nipp = new Label(randomNipp.getNipp());
            nipp.setWrapText(true);
            nipidLayout.getChildren().add(nipp);
        });
    }
}
