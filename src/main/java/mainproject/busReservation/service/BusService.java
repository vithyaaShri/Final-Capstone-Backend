package mainproject.busReservation.service;

import mainproject.busReservation.dto.BusDto;

import java.util.List;

public interface BusService {
    BusDto addBus(BusDto busDto);

    BusDto getBusById(Long id);

    List<BusDto> getAllBus();

    BusDto updateBus(BusDto busDto, Long id);

    void deleteBus(Long id);
}
