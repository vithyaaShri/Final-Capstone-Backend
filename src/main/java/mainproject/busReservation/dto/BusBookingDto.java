package mainproject.busReservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mainproject.busReservation.entity.Passenger;

import java.time.LocalDate;
//For Booking Entity Class
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusBookingDto {
    private Long id;
    private String bookingId;
    private String bookingDate;
    private String busName;
    private int fee;
    private LocalDate departureDate;
    private Passenger passenger;

}
