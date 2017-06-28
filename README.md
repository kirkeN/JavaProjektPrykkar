# Prykkar
autor: Kirke Narusk
2015-2016

description in eng: Java program that helps to assort and recycle garbage.

description in en:

**EESMÄRK**

Prykkar on JAVA programm õpetamaks inimesi õigesti prügi sorteerima: mis jäätmed millisesse prügikonteinerisse, prügikasti käivad. 

**FUNKTSIONAALSUS**

Prykkar koosneb järgmistest komponentidest:
* Eri värvidega vastavalt jäätmete liigile tähistatud prügikonteinerid, mis sisaldavad näiteid jäätmetest, mis tuleb vastavasse konteinerisse panna.
* Prügi sorteerimise mäng, kus kasutaja peab ära arvama, millisesse konteinerisse tuleb programmi poolt pakutud prügi visata.
* Sisestuslahter sorteeritava prügi sisestamiseks, mis ütleb kasutajale, milline on sobiv konteiner või pakub tähekombinatsioonide põhjal sarnase prügi.
* Tallinna jäätmejaamade kaart ja nimekiri asjadest, mida jäätmejaamades elanikelt tasuta vastu võetakse.
* Nipi nupp, mis annab kasutajale juhusliku valikuga väärt keskkonda säästva nipi. 

**ARHITEKTUUR**

Prykkar koosneb järgmistest klassidest:
* Java_fx.java : põhiaken layout'iga BorderPane
* Konteiner.java : iga jäätmete liik läheb eri konteinerisse; sisaldab meetodeid prügi nimekirja kuvamiseks, kontrollimiseks, kas kasutaja poolt sisestatud prügi sobib sellesse konteinerisse, Prykkari mängu meetodeid.
* Pakendikonteiner.java : Konteiner.java alamklass konteineri liigi Pakendikonteineri jaoks
* TopView.java : põhiakna ülemine vaade; 
* LeftView.java :  põhiakna vasapoolne vaade; 
* CenterView.java : põhiakna keskmine vaade; Prykkari äraarvamis mäng - milline konteiner on prügi jaoks sobiv.
* BottomView.java : põhiakna alumine vaade; juhusliku nipi kuvamiseks.
* RightView.java : põhiakna parempoolne vaade; jäätmejaamades vastuvõetava prügi nimekiri, jäätmejaamade kaardi kuvamise nupp.
* Nipp.java : nippide lugemine failis, juhusliku nipi tagastamine.
* Kaardiaken.java : jäätmejaamade ja nende info kuvamine Google Map taustal; Google Map'i kasutmise tarvis Java fx-is laadisin GMapsFX paketi: https://rterp.wordpress.com/2014/04/25/gmapsfx-add-google-maps-to-your-javafx-application/ ; copyright Rob Terpilowski.
* Nupp.java : nupu konstruktor konteinerite sarnaste nuppude tarvis, defineeritud värv, nupu vihje, nimi.

**MIS EDASI?**

* Jäätmete loetelu võiks täiendada ja parema kuvamise eesmärgil grupeerida neid: kasutaja saab valida jäätmete rühma, näiteks toit, ja kuvatakse toidujäätmed.
* Jäätmejaamade kaarti täiendada kogu Eesti kohta, hetkel kaetud vaid Tallinn.
* Lisada konteineritesse mittesobiliku prügi kuvamise võimalus, nt eraldi nupp selleks.
* Mäng teha huvitavamaks, andes näiteks punkte vms.
