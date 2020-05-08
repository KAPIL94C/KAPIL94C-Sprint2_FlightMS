package com.capg.flightmgt.airportms.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airports")
public class Airport {

    private String airportName;

    @Id
    private String airportCode;

    private String airportLocation;

    public Airport() {

    }
    
     public Airport(String airportName, String airportCode, String airportLocation) {
        this.airportName = airportName;
        this.airportCode = airportCode;
        this.airportLocation = airportLocation;
    }


    /**
     *
     * @return airport Name
     */
    public String getAirportName() {
        return airportName;
    }

   
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

  
    public String getAirportLocation() {
        return airportLocation;
    }

   
    public void setAirportLocation(String airportLocation) {
        this.airportLocation = airportLocation;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof Airport)) return false;
        Airport airport = (Airport) object;
        return this.airportCode.equals(airport.getAirportCode());
    }
    @Override
    public int hashCode() {
        return airportCode.hashCode();
    }

  
    @Override
    public String toString() {
        return "Airport Name :" + airportName + " " + "Airport Location :" + airportLocation + " " + "Airport Code :" + airportLocation;
    }

}
