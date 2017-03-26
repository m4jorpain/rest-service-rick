package nl.hu.fnt.gsos.service;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by Rick vd Gun on 24-3-2017.
 */
@XmlRootElement
public class Garage {
    private int id;
    private String naam;
    private String straatnaam;
    private boolean bewaakt;
    private int aantal;
    private double prijs;
    private ArrayList<Plek> plekken;

    public Garage(int id, String naam, String straatnaam, boolean bewaakt, int aantal, double prijs) {
        this.id = id;
        this.naam = naam;
        this.straatnaam = straatnaam;
        this.bewaakt = bewaakt;
        this.aantal = aantal;
        this.prijs = prijs;
        this.plekken = new ArrayList<Plek>();
        for(int i=1; i<=aantal; i++){
            plekken.add(new Plek(i));
        }
    }

    @Override
    public String toString() {
        return "Garage{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", straatnaam='" + straatnaam + '\'' +
                ", bewaakt=" + bewaakt +
                ", aantal=" + aantal +
                ", prijs=" + prijs +
                '}';
    }

    public Integer reserveerPlek(){
        if(!isVol()) {
            for (Plek p : plekken) {
                if (!p.isBezet()) {
                    p.setBezet(true);
                    return p.getId();
                }
            }
        }
        return null;
    }

    public boolean reserveerPlekById(int id){
        if(!isVol()) {
            Plek p = getPlekById(id);
            if (!p.isBezet()) {
                p.setBezet(true);
                return true;
            }
        }
        return false;
    }

    public Plek getPlekById(int id){
        Plek plek = null;
        for(Plek p : plekken) if (p.getId() == id) plek = p;
        return plek;
    }

    public int getAantalBezet(){
        int aantal = 0;
        for(Plek p : plekken) if (p.isBezet()) aantal++;
        return aantal;
    }

    public boolean isVol(){
        if (getAantalBezet() >= aantal) return true;
        else return false;
    }

    public ArrayList<Plek> getPlekken() {
        return plekken;
    }

    public void setAantal(int plekken) {
        if (this.plekken.size() > plekken) {
            for (int i = this.plekken.size()+1; i <= plekken; i++) {
                this.plekken.add(new Plek(i));
            }
        } else if (this.plekken.size() < plekken){
            for (int i=this.plekken.size(); i > plekken; i--){
                this.plekken.remove(i);
            }
        }
        this.aantal = plekken;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public boolean isBewaakt() {
        return bewaakt;
    }

    public void setBewaakt(boolean bewaakt) {
        this.bewaakt = bewaakt;
    }

    public int getAantal() {
        return aantal;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
}
