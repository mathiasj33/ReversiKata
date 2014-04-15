package de.rmrw.ReversiKata.code;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import de.rmrw.ReversiKata.views.IFSpielView;

@XmlRootElement
public class ReversiSpiel implements IFSpielModel {

    private ArrayList<IFSpielView> views = new ArrayList<>();
    private Spielfeld spielfeld;
    private Hashtable<Integer, Spieler> spielerSet = null;
    private ArrayList<Spielfeld> spielfeldList = new ArrayList<>();
    private int positionInSpielfeldList = 0;

    public ReversiSpiel() {
    }

    public void initSpiel(int size_, ArrayList<IFSpielView> views_) {
        views = views_;
        spielerSet = new Hashtable<Integer, Spieler>();
        spielfeld = new Spielfeld();
        spielfeld.init(size_);
        getSpielfeld().setForInit(Colors.WHITE, new Pos(getSize() / 2 - 1, getSize() / 2 - 1));
        getSpielfeld().setForInit(Colors.BLACK, new Pos(getSize() / 2, getSize() / 2 - 1));
        getSpielfeld().setForInit(Colors.BLACK, new Pos(getSize() / 2 - 1, getSize() / 2));
        getSpielfeld().setForInit(Colors.WHITE, new Pos(getSize() / 2, getSize() / 2));
        spielfeldList.add(new Spielfeld(spielfeld));
        updateAllViews();
    }

    private void updateAllViews() {
        for (IFSpielView v : views) {
            v.update();
        }
    }

    public Spieler registriereSpieler(String name) {
        if (spielerSet.size() > 2) {
            return null;
        }
        Spieler neuerSpieler = new Spieler();
        neuerSpieler.init(name,
                (spielerSet.size() == 0) ? Colors.WHITE : Colors.BLACK,
                (spielerSet.size() == 0) ? true : false
        );
        spielerSet.put(spielerSet.size() + 1, neuerSpieler);
        updateAllViews();
        return neuerSpieler;
    }

    public int getSize() {
        return getSpielfeld().getSize();
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public void setSpielfeld(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

    public Hashtable<Integer, Spieler> getSpielerSet() {
        return spielerSet;
    }

    public void setSpielerSet(Hashtable<Integer, Spieler> spielerSet) {
        this.spielerSet = spielerSet;
    }

    public Set<Pos> woKann(Spieler s) {
        return getSpielfeld().woKann(s.getColor());
    }

    public void setzeSpielstein(Spieler s, Pos p) {
        spielfeldList.set(positionInSpielfeldList, new Spielfeld(spielfeld));
        getSpielfeld().setzeSpielstein(s.getColor(), p);
        if (getSpielfeld().woKann(getGegner(s).getColor()).size() > 0) {
            getGegner(s).setAmZug(true);
            s.setAmZug(false);
        }
        updateAllViews();
        spielfeldList.removeIf((sp) -> spielfeldList.indexOf(sp) > positionInSpielfeldList);  //Lambda expression
        spielfeldList.add(new Spielfeld(spielfeld));
        positionInSpielfeldList = spielfeldList.size() - 1;
        System.out.println("Position: " + positionInSpielfeldList + "; Anzahl: " + spielfeldList.size());
        System.out.println(spielfeldList.get(1));
    }

    @Override
    public void undo() { //TODO: fertig implementieren, test bearbeiten, alle test grün kriegen; Wann wwird das currentspielfeld hinzugfefügt
        try {
            positionInSpielfeldList--;
            spielfeld = getSpielfeldFromPositionInList();
            invertSpielerAmZug();
            System.out.println("Position: " + positionInSpielfeldList + "; Anzahl: " + spielfeldList.size());
            System.out.println(spielfeldList.get(1));
          //  System.out.println(spielfeld);
        } catch (Exception e) {
            e.printStackTrace();
            positionInSpielfeldList++;
        }
        updateAllViews();
    }
    
    public Spielfeld getSpielfeldFromPositionInList() {
        return spielfeldList.get(positionInSpielfeldList);
    }

    @Override
    public void redo() {
        try {
            positionInSpielfeldList++;
            spielfeld = getSpielfeldFromPositionInList();
            System.out.println("Position: " + positionInSpielfeldList + "; Anzahl: " + spielfeldList.size());
            invertSpielerAmZug();
        } catch (Exception e) {
            e.printStackTrace();
            positionInSpielfeldList--;
        }
        updateAllViews();
    }

    /*public Spielfeld getLetztesSpielfeld() {
     return undoList.get(undoList.size() - 1);
     }
    
     public Spielfeld getNaechstesSpielfeld() {
     return redoList.get(redoList.size() - 1);
     } */
    public void invertSpielerAmZug() {
        Enumeration<Spieler> spielerEnum = spielerSet.elements();
        while (spielerEnum.hasMoreElements()) {
            Spieler s = spielerEnum.nextElement();
            if (s.isAmZug()) {
                s.setAmZug(false);
            } else {
                s.setAmZug(true);
            }
        }
        updateAllViews();
    }

    public Spieler spielerAmZug() {
        Enumeration<Spieler> spielerEnum = spielerSet.elements();
        while (spielerEnum.hasMoreElements()) {
            Spieler s = spielerEnum.nextElement();
            if (s.isAmZug()) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void setzeSpielstein(int spielerNummer, int zeile, int spalte) {
        setzeSpielstein(spielerSet.get(spielerNummer), new Pos(zeile, spalte));
    }

    @Override
    public SpielfeldFeldZustand getFeldZustand(int zeile, int spalte) {
        Pos pos = new Pos(zeile, spalte);
        Colors colorFeld = getSpielfeld().getColor(pos);

        for (Colors playerColor : Colors.PLAYERCOLORS) {
            if (colorFeld.equals(playerColor)) {
                if (getSpielerNummerFromColor(playerColor) == 1) {
                    return SpielfeldFeldZustand.BESETZT1;
                } else {
                    return SpielfeldFeldZustand.BESETZT2;
                }
            }
        }

        // Ab hier ist klar, dass das Feld leer ist
        Colors colorSpielerAmZug = spielerAmZug().getColor();
        if (getSpielfeld().esGibtEinenWegVonPosZuFarbe(pos, colorSpielerAmZug)) {
            if (getSpielerNummerFromColor(colorSpielerAmZug) == 1) {
                return SpielfeldFeldZustand.LEER_UND_BESETZBAR1;
            } else {
                return SpielfeldFeldZustand.LEER_UND_BESETZBAR2;
            }
        }

        // Ab hier ist klar, dass das Feld leer und nicht besetzbar ist 
        return SpielfeldFeldZustand.LEER_UND_NICHT_BESETZBAR;
    }

    private int getSpielerNummerFromColor(Colors color) {
        if (spielerSet.get(1).getColor().equals(color)) {
            return 1;
        }
        if (spielerSet.get(2).getColor().equals(color)) {
            return 2;
        }
        return -1;
    }

    public Spieler getGegner(Spieler spielerX) {
        if (spielerX.equals(spielerSet.get(1))) {
            return spielerSet.get(2);
        }
        if (spielerX.equals(spielerSet.get(2))) {
            return spielerSet.get(1);
        }
        return null;
    }

    @Override
    public void addView(IFSpielView view) {
        views.add(view);
    }

    @Override
    public Spieler getSpieler(int i) {
        return spielerSet.get(i);
    }

    @Override
    public int getSteineAufFeld(int spielerNummer) {
        return getSpielfeld().anzahl(getSpieler(spielerNummer).getColor());
    }

    @Override
    public String getSpielerName(int spielerNummer) {
        return getSpieler(spielerNummer).getName();
    }

}
