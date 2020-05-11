package com.capg.flightmgt.airportms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.capg.flightmgt.airportms.dto.AirportDto;
import com.capg.flightmgt.airportms.entities.Airport;
import com.capg.flightmgt.airportms.exceptions.InvalidAirportCodeException;
import com.capg.flightmgt.airportms.services.IAirportService;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import java.util.List;

@RequestMapping("/airports")
@RestController
@Validated
public class AirportRestController {
    /**
     * Record the log in file
     */
    private static final Logger Log = LoggerFactory.getLogger(AirportRestController.class);

    @Autowired
    IAirportService service;

    /**
     * This method will add all the airport to the database
     */
    @PostMapping("add")
    ResponseEntity<AirportDto>  addAirport(@RequestBody @Valid AirportDto dto){
    	Airport airport = convert(dto);
    	airport = service.addAirport(airport);
    	AirportDto airportDto = convertToDto(airport);
    	ResponseEntity<AirportDto> response = new ResponseEntity<>(airportDto, HttpStatus.OK);
        return response;
    	
    }
    
    private AirportDto convertToDto(Airport airport) {
		AirportDto dto = new AirportDto();
		dto.setAirportCode(airport.getAirportCode());
		dto.setAirportLocation(airport.getAirportLocation());
		dto.setAirportName(airport.getAirportName());
		return dto;
	}

	public  Airport convert(AirportDto dto) {
		Airport airport=new Airport();
		airport.setAirportCode(dto.getAirportCode());
		airport.setAirportName(dto.getAirportName());
		airport.setAirportLocation(dto.getAirportLocation());
		return airport;
	}

	@PostConstruct
    public void addAirports() {
        Airport airport = new Airport();
        airport.setAirportLocation("Mumbai");
        airport.setAirportCode("MUB");
        airport.setAirportName("Mumbai International Airport");
        service.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Bhopal Airport");
        airport.setAirportCode("BHO");
        airport.setAirportLocation("Bhopal");
        service.addAirport(airport);

        airport = new Airport();
        airport.setAirportLocation("Bangalore");
        airport.setAirportName("Bangalore International Airport");
        airport.setAirportCode("BLR");
        service.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Gwalior Airport");
        airport.setAirportCode("GWL");
        airport.setAirportLocation("Gwalior");
        service.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Indira Gandhi International Airport");
        airport.setAirportLocation("New Delhi");
        airport.setAirportCode("DEL");
        service.addAirport(airport);

        airport = new Airport();
        airport.setAirportLocation("Jabalpur");
        airport.setAirportCode("JLR");
        airport.setAirportName("Jabalpur Airport");
        service.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Amritsar  Airport");
        airport.setAirportLocation("Ludhiana");
        airport.setAirportCode("LUH");
        service.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Netaji Subhash Chandra Bose International Airport");
        airport.setAirportLocation("Kolkata");
        airport.setAirportCode("CCU");
        service.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Srinagar Airport");
        airport.setAirportCode("SXR");
        airport.setAirportLocation("Srinagar");
        service.addAirport(airport);
    }

    /**
     * fetch all the airports from database
     * @return airport list and response to server
     */
    @GetMapping()
    public ResponseEntity<List<Airport>> fetchAllAirports() {
        List<Airport> airportList = service.viewAllAirports();
        ResponseEntity<List<Airport>> response = new ResponseEntity<>(airportList, HttpStatus.OK);
        return response;
    }

    /**
     * fetch airport object by airport code
     * @param airportCode
     * @return airport and response to server
     */
    @GetMapping("/get/{airportCode}")
    public ResponseEntity<Airport> fetchAirportByCode(@PathVariable("airportCode") String airportCode) {
        Airport airport = service.viewAirport(airportCode);
        ResponseEntity<Airport> response = new ResponseEntity<>(airport, HttpStatus.OK);
        return response;
    }

    /**
     * this method will run when InvalidAirportCodeException occur
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidAirportCodeException.class)
    public ResponseEntity<String> handleEmployeeNotFound(InvalidAirportCodeException ex) {
        Log.error("Invalid Airport Code Exception ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }

    /**
     * this method will run when ConstraintViolationException occur
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
        Log.error("constraint violation ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        return response;
    }

    /**
     * Blanket Exception Handler
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        Log.error("Something went wrong ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

}
