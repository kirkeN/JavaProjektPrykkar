import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kasutaja on 14.10.2015.
 */
public class Konteiner {
    public String liik; //konteineri liik
    public List<String> prygi; //konteinerisse sobiv prygi

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
    //MEETOD "prindiKonteinerList" prindib välja konteinerisse sobiva prygi
    public  StringBuilder prindiKonteineriList() {
        List<String> prygiList = this.getPrygi();
        Collections.sort(prygiList);
        StringBuilder sb = new StringBuilder();
        for (String s : prygiList) {
            sb.append(s);
            sb.append("\n");
        }return sb;
    }
    //MEETOD "kuhuVisata": ytleb kasutajale, millisesse konteinerisse prygi visata
    public String kuhuVisata(String kasutajaPrygi) {
        String sobivKonteiner = "";
        for (int i = 0; this.getPrygi().size() > i; i++) {
            if (this.getPrygi().get(i).equals(kasutajaPrygi)) {//kontrollin, kas kasutaja prygi sobib antud konteinerisse; stringide puhul toimib meetod equals()!!! mitte ==
                sobivKonteiner = this.getLiik();
            } else{
                sarnanePrygiNimi(kasutajaPrygi, this.getPrygi().get(i)); //kui tapset vastet konteinerist ei leita, otsitakse sanraseid t2hekombinatsioone sisaldavaid vasteid; meetod meetodi sees
            }
        } return sobivKonteiner;
    }
    // MEETOD "sarnanePrygiNimi" : kui t2pset kasutaja sisestatud sone ei leita, siis hakatakse otsima sarnast prygi
    public void sarnanePrygiNimi (String kasutajaPrygi, String konteineriPrygi) {
        char[] kasutajaPrygiChars = kasutajaPrygi.toCharArray();
        char[] konteineriPrygiChars = konteineriPrygi.toCharArray();
        String[] tahekomplekt1 = new String[kasutajaPrygiChars.length-2];  //kontrollin kattuvusi 3-tahelistes kombinatsioonides, selleks teen massiivid
        String[] tahekomplekt2 = new String[konteineriPrygiChars.length-2];
        //kasutaja prygist tehakse massiiv, kus on 3-tähelised kombinatsioonid sõnast, nt "piim": ["pii"; "iim"]
        for (int i = 0;  tahekomplekt1.length > i ; i++) {
            tahekomplekt1[i] = Character.toString(kasutajaPrygiChars[i])+Character.toString(kasutajaPrygiChars[i+1])+Character.toString(kasutajaPrygiChars[i+2]); //Character.toString(char)
        }
        //konteineri prygist tehakse massiiv, kus on 3-tähelised kombinatsioonid sõnast, nt "paber": [pab; abe; ber]
        for (int i = 0; i < tahekomplekt2.length; i++) {
            tahekomplekt2[i] = Character.toString(konteineriPrygiChars[i])+Character.toString(konteineriPrygiChars[i+1])+Character.toString(konteineriPrygiChars[i+2]);
        }
        //kontrollin, kas kasutaja prügi sisaldab konteineri prygiga sarnaseid tahekombinatsioone
        for (int i = 0; i < tahekomplekt1.length; i++) {
            for (int j = 0; j <tahekomplekt2.length; j++) {
                if(tahekomplekt1[i].equals(tahekomplekt2[j]) && ! Java_fx.voimalikPrygiList.contains(konteineriPrygi)){
                    Java_fx.voimalikPrygiList.add(konteineriPrygi);
                }
            }
        }
    }

}

