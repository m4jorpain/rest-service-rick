package nl.hu.fnt.gsos.service;

/**
 * Created by Rick vd Gun on 24-3-2017.
 */
public class Plek {
    private int id;
    private boolean bezet;

    public Plek(int id) {
        this.id = id;
        this.bezet = false;
    }

    @Override
    public String toString() {
        return "Plek{" +
                "id=" + id +
                ", bezet=" + bezet +
                '}';
    }

    public int getId() {
        return id;
    }

    public boolean isBezet() {
        return bezet;
    }

    public void setBezet(boolean bezet) {
        this.bezet = bezet;
    }
}