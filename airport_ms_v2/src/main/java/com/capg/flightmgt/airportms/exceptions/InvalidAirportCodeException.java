package com.capg.flightmgt.airportms.exceptions;

public class InvalidAirportCodeException extends RuntimeException {
    public InvalidAirportCodeException(String msg){
        super(msg);
    }
}
