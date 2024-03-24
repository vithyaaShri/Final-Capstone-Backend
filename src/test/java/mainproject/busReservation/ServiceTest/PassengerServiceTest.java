package mainproject.busReservation.ServiceTest;

import mainproject.busReservation.dto.PassengerDto;
import mainproject.busReservation.entity.Passenger;
import mainproject.busReservation.exception.EmailAlreadyExsist;
import mainproject.busReservation.repository.PassengerRepository;
import mainproject.busReservation.service.PassengerService;
import mainproject.busReservation.service.impl.PassengerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PassengerServiceTest {
    private Passenger passenger;
    @Spy
    private ModelMapper modelMapper=new ModelMapper();
    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerServiceImpl passengerService;
    @BeforeEach
    public void setup() {

        passenger=Passenger.builder()
                .passengerName("john")
                .aadharId(1234567L)
                .contactNo(987654321L)
                .email("test@123")
                .age(28)
                .cancelStat(false)
                .journeyStat(true)
                .build();
    }
    @DisplayName("Test when an List of Passenger Given returns List Of Passenger")
    @Test
    public void givePassengerList_whenGetAllPassenger_thenReturnPassengerList() {
        Passenger passenger1 = Passenger.builder()
                .passengerName("Peter")
                .aadharId(1234597L)
                .contactNo(987754321L)
                .email("test123@123")
                .age(28)
                .cancelStat(false)
                .journeyStat(true)
                .build();
        given(passengerRepository.findAll()).willReturn(List.of(passenger1,passenger));
        List<PassengerDto> passengerDtos=passengerService.getAllPassenger();
        assertThat(passengerDtos).isNotNull();
        assertThat(passengerDtos.size()).isGreaterThan(0);
    }
    @DisplayName("Test when an List of Passenger Given returns List Of passenger")
    @Test
    public void givePassengerId_whenGetPassenger_thenReturnPassenger() {
        given(passengerRepository.findById(passenger.getId())).willReturn(Optional.of(passenger));
        PassengerDto passengerDto=modelMapper.map(passenger,PassengerDto.class);
        PassengerDto passDto=passengerService.getPassengerById(passenger.getId());
        Passenger passenger1=modelMapper.map(passDto,Passenger.class);
        assertThat(passenger1).isNotNull();
    }
    @DisplayName("Test when an Passenger id Given returns Updated Passenger")
    @Test
    public void givenPassenger_whenUpdatePassenger_thenReturnUpdatedPassenger(){
        given(passengerRepository.save(passenger)).willReturn(passenger);
        given(passengerRepository.findById(passenger.getId())).willReturn(Optional.of(passenger));
        passenger.setEmail("john@gmail.com");
        PassengerDto passengerDto = modelMapper.map(passenger, PassengerDto.class);
        PassengerDto savedPassengerDto = passengerService.updatePassenger(passengerDto,passenger.getId());
        Passenger savedPassenger = modelMapper.map(savedPassengerDto, Passenger.class);
        assertThat(savedPassenger.getEmail()).isEqualTo("john@gmail.com");
    }

}
