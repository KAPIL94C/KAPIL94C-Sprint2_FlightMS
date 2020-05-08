package org.cap.flightmgt.flightms.controller;

import org.cap.flightmgt.flightms.dto.FlightDetailDto;
import org.cap.flightmgt.flightms.dto.FlightDto;
import org.cap.flightmgt.flightms.entities.Flight;
import org.cap.flightmgt.flightms.exceptions.FlightNotFoundException;
import org.cap.flightmgt.flightms.exceptions.InvalidArgumentException;
import org.cap.flightmgt.flightms.service.IFlightService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.classic.Logger;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/flights")
public class FlightController {
	
	private static final org.jboss.logging.Logger Log = LoggerFactory.logger(FlightController.class);
    @Autowired
    private IFlightService service;

    
    @GetMapping
    public ResponseEntity<List<FlightDetailDto>> fetchAll() {
        List<Flight> flight = service.viewAllFlight();
        List<FlightDetailDto> list = convertToFlightDto(flight);
        ResponseEntity<List<FlightDetailDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<FlightDetailDto> book(@RequestBody @Valid FlightDto flightDto) {
            Flight flight  = converToFlight(flightDto);
                   flight =service.addFlight(flight);
                   FlightDetailDto dto=  convertToFlightDto(flight);
        ResponseEntity<FlightDetailDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
        return response;

    }
    
    @DeleteMapping("/delete/{flightNumber}")
    public ResponseEntity<Boolean> deleteFlight(@PathVariable("flightNumber") BigInteger flightNo) {
        boolean result = service.deleteFlight(flightNo);
        ResponseEntity respose = new ResponseEntity(result, HttpStatus.OK);
        return respose;
        
    }
    
    @GetMapping("/find/{flightNumber}")
    public ResponseEntity<FlightDetailDto> findById(@PathVariable("flightNumber") BigInteger flightNo) {
    
        Flight flight = service.viewFlight(flightNo);
        FlightDetailDto   flightDto = convertToFlightDto(flight);
        ResponseEntity<FlightDetailDto> response = new ResponseEntity<>(flightDto, HttpStatus.OK);
        return response;
    }
    
    @PutMapping("/update/{flightNumber}")
    public ResponseEntity<Boolean> updateFlight(@RequestBody Flight flight, @PathVariable BigInteger flightNumber) {

          	Flight flight1 = service.viewFlight(flightNumber);
          					flight.setFlightNumber(flightNumber);
          					service.modifyFlight(flight);
          	ResponseEntity  response = new ResponseEntity(true, HttpStatus.OK);
    		return response;
    }


    public  Flight converToFlight(FlightDto flightDto) {
		Flight flight = new Flight();
		flight.setFlightModel(flightDto.getFlightModel());
		flight.setCarrierName(flightDto.getCarrierName());
		flight.setSeatCapacity(flightDto.getSeatCapacity());
		return flight;
	}


    public List<FlightDetailDto> convertToFlightDto(List<Flight> flights) {
        List<FlightDetailDto> list = new ArrayList<>();
        for (Flight flight : flights) {
        	FlightDetailDto detailsDto = convertToFlightDto(flight);
            list.add(detailsDto);
        }
        return list;
    }

    FlightDetailDto convertToFlightDto(Flight flight) {
    	FlightDetailDto detailsDto = new FlightDetailDto();
        detailsDto.setFlightNumber(flight.getFlightNumber());
        detailsDto.setCarrierName(flight.getCarrierName());
        detailsDto.setFlightModel(flight.getFlightModel());
        detailsDto.setSeatCapacity(flight.getSeatCapacity());
        return detailsDto;
    }
    
    @PostConstruct
    public void addFlights() {
    	
    	Flight flight = new Flight();
    	flight.setCarrierName("Airbus");
    	flight.setFlightModel("AS300");
    	flight.setSeatCapacity(200);
    	service.addFlight(flight);
    	
    	
         flight = new Flight();
    	flight.setCarrierName("Boeing");
    	flight.setFlightModel("BZ777");
    	flight.setSeatCapacity(200);
    	service.addFlight(flight);
    	
    	 flight = new Flight();
    	flight.setCarrierName("Douglas");
    	flight.setFlightModel("DC300");
    	flight.setSeatCapacity(270);
    	service.addFlight(flight);
    	
        flight = new Flight();
    	flight.setCarrierName("Douglas");
    	flight.setFlightModel("DZ307");
    	flight.setSeatCapacity(120);
    	service.addFlight(flight);
    	
        flight = new Flight();
    	flight.setCarrierName("Airbus");
    	flight.setFlightModel("AB330");
    	flight.setSeatCapacity(188);
    	service.addFlight(flight);
     
    }

    
    
   
    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFound(FlightNotFoundException ex) {
        Log.error("Invalid Flight Code Exception ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }
    
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<String> handleInvalidArgument(InvalidArgumentException ex) {
        Log.error("Invalid Argument ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
        Log.error("constraint violation ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        return response;
    }

   
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        Log.error("Something went wrong ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

}
