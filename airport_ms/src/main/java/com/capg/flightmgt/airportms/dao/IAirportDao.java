package com.capg.flightmgt.airportms.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.flightmgt.airportms.entities.Airport;


public interface IAirportDao extends JpaRepository<Airport,String> {
	
}
