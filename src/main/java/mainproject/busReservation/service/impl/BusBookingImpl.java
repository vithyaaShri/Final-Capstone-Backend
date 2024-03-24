package mainproject.busReservation.service.impl;

import lombok.AllArgsConstructor;
import mainproject.busReservation.dto.BusBookingDto;
import mainproject.busReservation.entity.BusBooking;
import mainproject.busReservation.entity.Passenger;
import mainproject.busReservation.repository.BusBookingRepository;
import mainproject.busReservation.service.BusBookingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class BusBookingImpl implements BusBookingService {
    private ModelMapper modelMapper;
    private BusBookingRepository bookingRepository;
    @Override
    public BusBookingDto add(BusBookingDto busBooking) {
        BusBooking bus=modelMapper.map(busBooking, BusBooking.class);
        BusBooking savedBus=bookingRepository.save(bus);
        return modelMapper.map(savedBus, BusBookingDto.class);

    }

    @Override
    public BusBookingDto getById(Long id) {
        return null;
    }

    //To get All Bus Detail
    @Override
    public List<BusBookingDto> getAll() {
        List<BusBooking> busBookingList= bookingRepository.findAll();
        return busBookingList.stream().map(bus->modelMapper.map(bus, BusBookingDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BusBookingDto> getByPassenger(Passenger passenger) {
        return null;
    }

    //to get one passenger details based on id
    @Override
    public BusBookingDto getByPassengerId(Long id) {
        BusBooking busBooking=bookingRepository.findByPassenger_id(id);
        return modelMapper.map(busBooking,BusBookingDto.class);
    }

}
