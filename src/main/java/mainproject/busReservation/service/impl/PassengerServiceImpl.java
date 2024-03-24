package mainproject.busReservation.service.impl;

import lombok.AllArgsConstructor;
import mainproject.busReservation.entity.Passenger;
import mainproject.busReservation.exception.ResourceNotFoundException;
import mainproject.busReservation.repository.PassengerRepository;
import mainproject.busReservation.service.PassengerService;
import mainproject.busReservation.dto.PassengerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {
    private ModelMapper modelMapper;
    private PassengerRepository passengerRepository;
    //To Add passenger Detail at repository
    @Override
    public PassengerDto addPassenger(PassengerDto passengerDto) {
        Passenger passenger=modelMapper.map(passengerDto, Passenger.class);
        Passenger savePassenger=passengerRepository.save(passenger);
        return modelMapper.map(savePassenger, PassengerDto.class);
    }
    //To get Single Passenger Based on id

    @Override
    public PassengerDto getPassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id).get();
        return modelMapper.map(passenger,PassengerDto.class);
    }
    //To get List of Passengers

    @Override
    public List<PassengerDto> getAllPassenger() {
        List<Passenger> passengerList= passengerRepository.findAll();
        return passengerList.stream().map(passenger->modelMapper.map(passenger,PassengerDto.class)).collect(Collectors.toList());
    }
    //To Update Passenger Based on id
    @Override
    public PassengerDto updatePassenger(PassengerDto passengerDto, Long id) {
        Passenger passenger=passengerRepository.findById(id).get();
        passenger.setPassengerName(passengerDto.getPassengerName());
        passenger.setAadharId(passengerDto.getAadharId());
        passenger.setContactNo(passengerDto.getContactNo());
        passenger.setEmail(passengerDto.getEmail());
        passenger.setAge(passengerDto.getAge());
        Passenger passenger1=passengerRepository.save(passenger);
        return modelMapper.map(passenger1,PassengerDto.class);
    }
    //To Find Passenger By Email
    public PassengerDto findByEmail(String email) {
        Passenger passenger=passengerRepository.findByEmail(email);
        return modelMapper.map(passenger,PassengerDto.class);
    }
    //To Change CancelStat to true is ticket Cancelled

    @Override
    public PassengerDto cancelTicket(Long id) {
        Passenger passenger=passengerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        if(!passenger.cancelStat) {
            passenger.setCancelStat(Boolean.TRUE);
        }
        Passenger updatedPassenger=passengerRepository.save(passenger);
        return modelMapper.map(updatedPassenger,PassengerDto.class);
    }
//To delet Passenger by making call to repository
    @Override
    public void deletePassenger(Long id) {
        Passenger passenger=passengerRepository.findById(id).get();
        passengerRepository.delete(passenger);

    }
}
