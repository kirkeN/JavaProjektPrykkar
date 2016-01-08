import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 Google Map kaardiakna klass, kus kuvatakse markerite abil jäätmejaamade asukohad koos lisainfoga.
 */
public class Kaardiaken extends Application implements MapComponentInitializedListener {

    GoogleMapView mapView;
    GoogleMap map;

    Kaardiaken (GoogleMap map, GoogleMapView mapView){
        this.map = map;
        this.mapView = mapView;

    }
    // järgnev kood: copyright Rob Terpilowski, https://rterp.wordpress.com/2014/04/25/gmapsfx-add-google-maps-to-your-javafx-application/
    // v.a. meetodid, mille tuletasin ise ja kohandasin programmi vajadustele vastavaks
    @Override
    public void start(Stage stage) throws Exception {
        //Create the JavaFX component and set this as a listener so we know when
        //the map has been initialized, at which point we can then begin manipulating it.
        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);

        Scene scene = new Scene(mapView);

        stage.setTitle("Tallinna jäätmejaamad");
        stage.setScene(scene);
        stage.show();

    }

    @Override

    public void mapInitialized() {
        //kaardi omadused
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(59.43, 24.76))
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(11);

        map = mapView.createMap(mapOptions);

        //lisan markerid kaardile
        Marker  paljassaare = marker(new LatLong(59.462831, 24.703856));
        markerInfo(paljassaare,"<h4><a href=\"http://www.kesto.ee/kogumispunktid-paljasaare.html\">Paljassaare põik 9a</a> </h4><h5> K, N, R 14-19<br/> L, P 10-15</h5>");
        Marker rahumae = marker(new LatLong(59.393354, 24.712312));
        markerInfo(rahumae, "<h4><a href=\"http://jaatmejaam.ee/rahumae-jaatmejaam/\">Rahumäe tee 5a</a> </h4><h5> T, K, N 14-19<br/>E, L, P 10-15</h5>");
        Marker parnamae = marker(new LatLong(59.472282, 24.900201));
        markerInfo(parnamae, "<h4><a href=\"http://jaatmejaam.ee/parnamae-jaatmejaam/\">Pärnamäe tee 36</a> </h4><h5> T, K, N 14-19 <br/> E, L, P 10-15 </h5>");
        Marker suursojamae = marker(new LatLong(59.416518, 24.858646));
        markerInfo(suursojamae,"<h4><a href=\"http://www.ragnsells.ee/kontaktid/jaatmejaamad/\"> Suur-Sõjamäe 31a</a></h4><h5> K, N, R 14-19<br/>L, P 10-15</h5>" );
        Marker raba = marker(new LatLong(59.360954, 24.644074));
        markerInfo(raba,"<h4><a href=\"http://www.ragnsells.ee/kontaktid/jaatmejaamad/\">Raba 40</a> </h4><h5> E, R 14-19<br/>K 8-13<br/>L, P 10-15</h5>" );

    }
    public Marker marker(LatLong xy){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(xy)
                .visible(Boolean.TRUE);

        Marker marker = new Marker(markerOptions);
        map.addMarker(marker);
        return marker;
    }
    public void markerInfo(Marker marker, String content){
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content(content);

        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, marker);
    }
    public static void main(String[] args) {
        launch(args);
    }
}