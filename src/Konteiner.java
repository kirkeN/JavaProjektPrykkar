/**
 * Created by kasutaja on 14.10.2015.
 */
public class Konteiner {
    private String liik; //konteineri liik
    private String[] prygi; //järjend asjade jaoks, mida võib konteinerisse panna

    Konteiner(String liik) {
        super();
        this.liik = liik;
    }
    Konteiner(String[] prygi) {
        this.prygi = prygi;
    }
    String getLiik() {
        return liik;
    }
    void setLiik(String liik){
        this.liik = liik;
    }
    String[] getPrygi() {
        return prygi;
    }
    void setPrygi(String[] prygi){
        this.prygi = prygi;
    }



}

