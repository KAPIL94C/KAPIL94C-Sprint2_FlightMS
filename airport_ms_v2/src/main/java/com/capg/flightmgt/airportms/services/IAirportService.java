package com.capg.flightmgt.airportms.services;
import java.util.List;

import com.capg.flightmgt.airportms.entities.Airport;

public interface IAirportService{

	public Airport addAirport(Airport airport);
	public List<Airport> viewAllAirports();
	public Airport viewAirport(String airportCode);
}
