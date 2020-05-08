package com.capg.flightmgt.airportms.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class AirportDto {
	
	    @NotBlank(message = "Name is mandatory")
		private String airportName;
	    @NotBlank(message = "Code is mandatory")
	    private String airportCode;
	    @NotBlank(message = "location is mandatory")
	    private String airportLocation;

	    public AirportDto() {

	    }
	    
	    public AirportDto(String airportName, String airportCode, String airportLocation) {
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


}
