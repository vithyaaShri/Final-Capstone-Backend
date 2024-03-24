package mainproject.busReservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
//For Bus Dto class
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {
    private Long Id;

    private String busName;

    private String pickupLocation;

    private String dropLocation;

    private int seats;
    private  int available_seats;
    private LocalDate departureDate;

    private int price;
}
