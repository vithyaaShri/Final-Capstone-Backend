package mainproject.busReservation.service;

import mainproject.busReservation.dto.BusBookingDto;
import mainproject.busReservation.entity.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusBookingService {
    BusBookingDto add(BusBookingDto busBooking);
    BusBookingDto getById(Long id);
    List<BusBookingDto> getAll();
    List<BusBookingDto> getByPassenger(Passenger passenger);
    BusBookingDto getByPassengerId(Long id);

}
