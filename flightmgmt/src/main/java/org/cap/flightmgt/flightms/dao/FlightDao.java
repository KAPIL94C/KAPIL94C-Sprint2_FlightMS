package org.cap.flightmgt.flightms.dao;

import java.math.BigInteger;

import org.cap.flightmgt.flightms.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDao extends JpaRepository<Flight,BigInteger> {
	
	
}
