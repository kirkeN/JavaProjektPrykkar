/**
 * Created by Kirke on 12.12.2015.
 */
public class PakendiKonteiner extends Konteiner{

public String alamliik;

    PakendiKonteiner(String liik, String alamliik) { //metallpakend, klaaspakend, plastpakend
        super(liik);
    }
    String getAlamliik() {
        return alamliik;
    }
    void setAlamliik(String alamliik){
        this.liik = alamliik;
    }
}
