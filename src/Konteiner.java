import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kasutaja on 14.10.2015.
 */
public class Konteiner {
    public String liik; //konteineri liik
    //private String[] prygi; //j�rjend asjade jaoks, mida v�ib konteinerisse panna
    public List<String> prygi;

    Konteiner(String liik) {
        this.liik = liik;
    }
    Konteiner(List<String> prygi) {
        this.prygi = prygi;
    }
    String getLiik() {
        return liik;
    }
    void setLiik(String liik){
        this.liik = liik;
    }
    List<String> getPrygi() {
        return prygi;
    }
    void setPrygi(List<String> prygi){
        this.prygi = prygi;
    }

}

