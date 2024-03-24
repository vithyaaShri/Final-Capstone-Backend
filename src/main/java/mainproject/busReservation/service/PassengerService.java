package mainproject.busReservation.service;

import mainproject.busReservation.dto.PassengerDto;

import java.util.List;

public interface PassengerService {
    PassengerDto addPassenger(PassengerDto passengerDto);

    PassengerDto getPassengerById(Long id);

    List<PassengerDto> getAllPassenger();

    PassengerDto updatePassenger(PassengerDto passengerDto, Long id);

    void deletePassenger(Long id);
    PassengerDto findByEmail(String email);
    PassengerDto cancelTicket(Long id);
}
