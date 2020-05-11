package com.capg.flightmgt.airportms.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.flightmgt.airportms.dao.IAirportDao;
import com.capg.flightmgt.airportms.entities.Airport;
import com.capg.flightmgt.airportms.exceptions.InvalidAirportCodeException;
import com.capg.flightmgt.airportms.exceptions.InvalidArgumentException;

import javax.transaction.Transactional;

@Service
@Transactional
public class AirportServiceImpl implements IAirportService {
    private IAirportDao dao;

    public IAirportDao getDao() {
        return dao;
    }

    @Autowired
    public void setDao(IAirportDao dao) {
        this.dao = dao;
    }

  
    @Override
    public Airport addAirport(Airport airport) {
        if(airport==null){
            throw new InvalidArgumentException("Airport can't be null");
        }
        airport = dao.save(airport);
        return airport;
    }

    
    @Override
    public List<Airport> viewAllAirports() {
        List<Airport> airportList = dao.findAll();
        return airportList;
    }

   
    @Override
    public Airport viewAirport(String airportCode) {
       
        Optional<Airport> optionalAirport = dao.findById(airportCode);
        if (optionalAirport.isPresent()) {
            Airport airport = optionalAirport.get();
            return airport;
        }
        throw new InvalidAirportCodeException("Invalid Airport Code, Airport Not Exist for given code");

    }

}

