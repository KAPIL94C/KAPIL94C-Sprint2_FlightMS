package org.cap.flightmgt.flightms.service;


import org.cap.flightmgt.flightms.dao.FlightDao;
import org.cap.flightmgt.flightms.entities.Flight;
import org.cap.flightmgt.flightms.exceptions.FlightNotFoundException;
import org.cap.flightmgt.flightms.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

@Service
@Transactional
public class FlightServiceImpl implements IFlightService{

    @Autowired
    private FlightDao dao;



	@Override
	public Flight addFlight(Flight flight){
		if(flight == null) {
		throw new InvalidArgumentException("Flight can not be null");
		 
		}
	     flight = dao.save(flight);
		 return flight;
	}


	@Override
	public Flight modifyFlight(Flight flight) {
		    flight = dao.save(flight);
			return flight;
	}


	@Override
	public Flight viewFlight(BigInteger flightNo) {
		   Optional<Flight> optional = dao.findById(flightNo);
		   
		     if(optional.isPresent()) {
			  Flight flight = optional.get();
			  return flight;
		    }
		    throw new FlightNotFoundException("flight with id ="+flightNo+" do not exists"); 
	
	}


	@Override
	public List<Flight> viewAllFlight(){
		  List<Flight> list = dao.findAll();
		  return list;
	}


	@Override
	public boolean deleteFlight(BigInteger flightNo) {
	
			 dao.deleteById(flightNo);
		    return true;
	

   
	}

}
