package mainproject.busReservation.service.impl;

import lombok.AllArgsConstructor;
import mainproject.busReservation.dto.BusDto;
import mainproject.busReservation.entity.Bus;
import mainproject.busReservation.repository.BusRepository;
import mainproject.busReservation.service.BusService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BusServiceImpl implements BusService {
    private ModelMapper modelMapper;
    private BusRepository busRepository;


    //Add a bus to backend
    @Override
    public BusDto addBus(BusDto busDto) {
        Bus bus=modelMapper.map(busDto, Bus.class);
        Bus savedBus=busRepository.save(bus);
        return modelMapper.map(savedBus,BusDto.class);

    }

    //Get Single Bus using id
    @Override
    public BusDto getBusById(Long id) {
        Bus bus=busRepository.findById(id).get();
        return modelMapper.map(bus,BusDto.class);
    }
    //Get All buses from repository
    @Override
    public List<BusDto> getAllBus() {
        List<Bus> busList= busRepository.findAll();
        return busList.stream().map(bus->modelMapper.map(bus,BusDto.class)).collect(Collectors.toList());

    }
    //Update Bus Details Based on id
    @Override
    public BusDto updateBus(BusDto busDto, Long id) {
        Bus bus= busRepository.findById(id).get();
        bus.setBusName(busDto.getBusName());
        bus.setPickupLocation(busDto.getPickupLocation());
        bus.setDropLocation(busDto.getDropLocation());
        bus.setPrice(busDto.getPrice());
        bus.setSeats(busDto.getSeats());
        bus.setAvailable_seats(busDto.getAvailable_seats());
        bus.setDepartureDate(busDto.getDepartureDate());
        Bus updatedBus=busRepository.save(bus);
        return modelMapper.map(updatedBus,BusDto.class);
    }

    @Override
    public void deleteBus(Long id) {
        Bus bus=busRepository.findById(id).get();
        busRepository.delete(bus);

    }

}
