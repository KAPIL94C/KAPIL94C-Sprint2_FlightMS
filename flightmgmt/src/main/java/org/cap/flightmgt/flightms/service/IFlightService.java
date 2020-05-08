package org.cap.flightmgt.flightms.service;




import java.math.BigInteger;
import java.util.List;

import org.cap.flightmgt.flightms.entities.Flight;

public interface IFlightService {
	
    Flight addFlight(Flight flight);
    
    Flight modifyFlight(Flight flight);
    
    Flight viewFlight(BigInteger flightNo);
    
    List<Flight> viewAllFlight();
     
    boolean deleteFlight(BigInteger flightNo);
    

  
}
