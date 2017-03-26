package nl.hu.fnt.gsos.service;


public class ServiceProvider {
	private static GarageServiceImpl garageService = new GarageServiceImpl();
	public static GarageServiceImpl getGarageService(){
		return garageService;
	}
}