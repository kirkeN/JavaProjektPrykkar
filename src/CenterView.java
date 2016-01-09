import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

/**
 * BorderPane Center view
 */
public class CenterView {
   public static VBox centerBox;
   public static Button m2ng;

   public static void centerVisuaalid(){
       centerBox = new VBox(); //borderPane's keskele
       centerBox.setSpacing(10);
       centerBox.setPadding(new Insets(15,10,10,10));
       m2ng = new Button("MÄNGI");

       //Picture
       Image manguPilt = new Image("reuse2.svg");
       ImageView imv = new ImageView();
       imv.setImage(manguPilt);
       Pane pictureRegion = new Pane();
       pictureRegion.getChildren().add(imv);
       imv.setTranslateX(30);
       imv.setTranslateY(30);

       centerBox.getChildren().addAll(m2ng, pictureRegion);

       m2ng.setOnAction(event -> {
           m2ngi();
       });
       Java_fx.border.setCenter(centerBox);
    }

    //Nupp "Mängi" Action
    public static void m2ngi() {
        centerBox.getChildren().clear();
        Konteiner[] konteineriteList = {TopView.paberPapp, TopView.bio,  TopView.elektroonika,  TopView.klaaspakend,
                TopView.plastpakend, TopView.metallpakend,  TopView.ohtlikud}; //teen j2rjendi k6ikide konteinerite (ja nende sisu) kohta
        Konteiner[] juhuslikKonteiner = {konteineriteList[(int) (Math.random() * 7)]};
        String juhuslikPrygi = juhuslikKonteiner[0].randomPrygi(); //tahan otsida juhuslikust konteinerist juhuslikku prygi
        Label kuhuViskaksid = new Label("Kuhu viskaksid sellise prügi nagu: " + juhuslikPrygi);
        kuhuViskaksid.setWrapText(true);
        ToggleGroup m2nguValikud = new ToggleGroup();
        RadioButton paber = new RadioButton( TopView.paberPapp.getLiik());
        RadioButton bioj22de = new RadioButton( TopView.bio.getLiik());
        RadioButton pakend = new RadioButton( TopView.klaaspakend.getLiik());
        RadioButton elektro = new RadioButton( TopView.elektroonika.getLiik());
        RadioButton oht = new RadioButton( TopView.ohtlikud.getLiik());
        paber.setToggleGroup(m2nguValikud);
        paber.setUserData(TopView.paberPapp.getLiik());
        bioj22de.setToggleGroup(m2nguValikud);
        bioj22de.setUserData(TopView.bio.getLiik());
        pakend.setToggleGroup(m2nguValikud);
        pakend.setUserData(TopView.klaaspakend.getLiik());
        elektro.setToggleGroup(m2nguValikud);
        elektro.setUserData(TopView.elektroonika.getLiik());
        oht.setToggleGroup(m2nguValikud);
        oht.setUserData(TopView.ohtlikud.getLiik());
        centerBox.getChildren().addAll(m2ng, kuhuViskaksid, paber, bioj22de, pakend, elektro, oht);
        VBox oigeVastusBox = new VBox(); // layout 6igele vastusele, mis l2heb centerBoxi
        m2nguValikud.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                oigeVastusBox.getChildren().clear(); //iga kord, kui kasutaja vahetab radiobuttonit, kirjutatakse 6ige vastus yle
                Label vastus = new Label();
                if (m2nguValikud.getSelectedToggle() != null) {
                    if (juhuslikKonteiner[0].kasKasutajaArvasAra(m2nguValikud.getSelectedToggle().getUserData().toString())) {
                        vastus.setText("Õige");
                        oigeVastusBox.getChildren().add(vastus);
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(1000),
                                ae -> m2ngi())); //kui 6ige vastus, siis kuvatakse uus kysimus
                        timeline.play();
                    }
                    else {
                        vastus.setText("Vale, mõtle järele");
                        oigeVastusBox.getChildren().add(vastus);
                    }
                    vastus.setStyle("-fx-font: 12 helvetica;-fx-font-weight: bold");
                }
            }
        });
        centerBox.getChildren().add(oigeVastusBox);
    }
}
