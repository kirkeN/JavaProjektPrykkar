/**
 * Created by Kirke on 12.12.2015.
 */
public class PakendiKonteiner extends Konteiner{

public String alamliik;

    PakendiKonteiner(String liik, String alamliik) { //metallpakend, klaaspakend, plastpakend
        super(liik);
        this.alamliik = alamliik;
    }
    String getAlamliik() {
        return alamliik;
    }

    //kirjutan konteineri meetodi yle, et tagastataks pakendikonteineri liik (konteineri alamliik)
    public String kuhuVisata(String kasutajaPrygi) {
        String sobivKonteiner = "";
        for (int i = 0; this.getPrygi().size() > i; i++) {
            if (this.getPrygi().get(i).equals(kasutajaPrygi)) {//kontrollin, kas kasutaja prygi sobib antud konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
                sobivKonteiner = this.getAlamliik();
            } else{
                sarnanePrygiNimi(kasutajaPrygi, this.getPrygi().get(i)); //kui tapset vastet konteinerist ei leita, otsitakse sanraseid t2hekombinatsioone sisaldavaid vasteid; meetod meetodi sees
            }
        } return sobivKonteiner;
    }
}
