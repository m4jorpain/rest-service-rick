package nl.hu.fnt.gsos.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rick vd Gun on 24-3-2017.
 */
public class GarageServiceImpl {
    private ArrayList<Garage> garages = new ArrayList<Garage>();

    public GarageServiceImpl() {
        garages.add(new Garage(1, "Fiets parkeergarage UCS", "Straat1", false, 200, 0));
        garages.add(new Garage(2, "Auto parkeergarage UCS", "Straat1", true, 300, 2.50));
    }

    public Garage getGarageById(int id){
        for (Garage g : garages){
            if (g.getId() == id){
                return g;
            }
        }
        return null;
    }

    public ArrayList<Garage> getGarages(){
        return garages;
    }

    public boolean removeGarage(Garage g){
        return garages.remove(g);
    }

    public boolean addGarage(Garage g){
        return garages.add(g);
    }
}
