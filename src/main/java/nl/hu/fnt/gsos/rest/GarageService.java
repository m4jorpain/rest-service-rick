package nl.hu.fnt.gsos.rest;

import nl.hu.fnt.gsos.service.Garage;
import nl.hu.fnt.gsos.service.GarageServiceImpl;
import nl.hu.fnt.gsos.service.Plek;
import nl.hu.fnt.gsos.service.ServiceProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Rick vd Gun on 24-3-2017.
 */
@Path("/garages")
public class GarageService {
    ServiceProvider sp = new ServiceProvider();
    GarageServiceImpl gs = new GarageServiceImpl();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Garage> getGarages(){
        return gs.getGarages();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Garage getGarageById(@PathParam("id") int id){
        return gs.getGarageById(id);
    }

    @GET
    @Path("/plekken")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAantalVrijePlekken(@QueryParam("id") int id){
        Garage garage = gs.getGarageById(id);
        int vrij = garage.getAantal() - garage.getAantalBezet();
        return "Er zijn nog "+vrij+" plekken van de "+garage.getAantal()+" over.";
    }

    @GET
    @Path("/plekken/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getVrijePlek(@PathParam("id") int id, @QueryParam("plek") int plekid) {
        Garage garage = gs.getGarageById(id);
        Plek plek = garage.getPlekById(plekid);
        return "Deze plek is momenteel " + (plek.isBezet() ? "bezet" : "vrij");
    }

    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON})
    public String addGarage(
                        @QueryParam("id") int id,
                        @QueryParam("naam") String naam,
                        @QueryParam("straat") String straat,
                        @QueryParam("bewaakt") boolean bewaakt,
                        @QueryParam("aantal") int aantal,
                        @QueryParam("prijs") double prijs) {
        Garage g = new Garage(id,naam,straat,bewaakt,aantal,prijs);
        boolean uitslag = gs.addGarage(g);
        return (uitslag ? "Nieuwe garage toegevoegd: \n"+g.toString() : "Niet gelukt om garage toe te voegen");
    }

    @DELETE
    @Path("/delete")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteGarage(@QueryParam("id") int id){
        Garage garage = gs.getGarageById(id);
        boolean uitslag = gs.removeGarage(garage);
        return (uitslag ? "Garage verwijderd: \n"+garage.toString() : "Niet gelukt om garage te verwijderen");
    }

    @PUT
    @Path("/update/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String updateGarage(
                        @PathParam("id") int id,
                        @QueryParam("naam") String naam,
                        @QueryParam("straat") String straat,
                        @QueryParam("bewaakt") boolean bewaakt,
                        @QueryParam("aantal") int aantal,
                        @QueryParam("prijs") double prijs) {
        Garage garage = gs.getGarageById(id);
        garage.setNaam(naam);
        garage.setStraatnaam(straat);
        garage.setBewaakt(bewaakt);
        garage.setAantal(aantal);
        garage.setPrijs(prijs);
        return "Garage met id '" + id + "' geupdate naar: \n" + garage.toString();
    }

    @PUT
    @Path("/reserveer/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String reserveerPlekById(@PathParam("id") int garageid, @QueryParam("plekid") int plekid){
        Garage garage = gs.getGarageById(garageid);
        boolean uitslag = garage.reserveerPlekById(plekid);
        return (uitslag ? "Plek '"+plekid+"' gereserveerd bij garage '"+garage.getNaam()+"' Hier wordt "+garage.getPrijs()+" euro per uur voor in rekening gebracht" : "Reserveren niet gelukt op plek '"+plekid+"' bij garage '"+garage.getNaam()+"'. De plek is al bezet.");
    }

    @PUT
    @Path("/reserveer")
    @Produces({MediaType.APPLICATION_JSON})
    public String reserveerPlek(@QueryParam("id") int id) {
        Garage garage = gs.getGarageById(id);
        Integer uitslag = garage.reserveerPlek();
        String output = "";
        if (uitslag != null){
            output = "Plek '"+uitslag+"' gereserveerd bij garage '"+garage.getNaam()+"' Hier wordt "+garage.getPrijs()+" euro per uur voor in rekening gebracht";
        } else {
            output = "Plek kon niet gereserveerd worden. De garage is al vol";
        }
        return output;
    }
}
