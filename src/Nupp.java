import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * Created by Kirke on 22.12.2015.
 */
public class Nupp extends Button{
    //nupu konstruktor nime, vihje ja värvi jaoks
    Nupp(String nimi, String tip, String varv) {
        this.setText(nimi);
        Tooltip toolTip = new Tooltip(tip);
        toolTip.setWrapText(true);
        toolTip.setMaxWidth(150);
        this.setTooltip(toolTip);
        this.setStyle("-fx-base:" + varv + "; -fx-text-fill: #2c333a; -fx-background-radius: 4;-fx-font: 12 helvetica");
        this.setMaxWidth(140);
        this.setMinWidth(140);
    }
}
