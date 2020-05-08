package org.cap.flightmgt.flightms.dto;

import java.math.BigInteger;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class FlightDetailDto {
	
	  private  BigInteger flightNumber;
	  @NotBlank(message = "Flight Model is mandatory")
	    private String flightModel;
	    @NotBlank(message = "Name is mandatory")
	    private String carrierName;
	    @Min(50)
	    @Max(500)
	    private Integer seatCapacity;
	    
	    public FlightDetailDto() {
	    	super();
	    }
	    
	    
		public BigInteger getFlightNumber() {
			return flightNumber;
		}
		public void setFlightNumber(BigInteger flightNumber) {
			this.flightNumber = flightNumber;
		}
		public String getFlightModel() {
			return flightModel;
		}
		public void setFlightModel(String flightModel) {
			this.flightModel = flightModel;
		}
		public String getCarrierName() {
			return carrierName;
		}
		public void setCarrierName(String carrierName) {
			this.carrierName = carrierName;
		}
		public Integer getSeatCapacity() {
			return seatCapacity;
		}
		public void setSeatCapacity(Integer seatCapacity) {
			this.seatCapacity = seatCapacity;
		}
	    
	    

}
