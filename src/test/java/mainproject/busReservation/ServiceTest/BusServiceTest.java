package mainproject.busReservation.ServiceTest;

import mainproject.busReservation.dto.BusDto;
import mainproject.busReservation.dto.PassengerDto;
import mainproject.busReservation.entity.Bus;
import mainproject.busReservation.entity.Passenger;
import mainproject.busReservation.repository.BusRepository;
import mainproject.busReservation.repository.PassengerRepository;
import mainproject.busReservation.service.impl.BusServiceImpl;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BusServiceTest {
    private Bus bus;
    @Spy
    private ModelMapper modelMapper=new ModelMapper();
    @Mock
    private BusRepository busRepository;
    @InjectMocks
    private BusServiceImpl busService;
    @BeforeEach
    public  void setup(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2024-03-30", dateFormat);
        bus=Bus.builder()
                .busName("Mss")
                .departureDate(date)
                .pickupLocation("chennai")
                .dropLocation("Mettur")
                .price(900)
                .available_seats(30)
                .seats(30)
                .build();
    }
    @DisplayName("Test when an List of Bus Given returns List Of Bus")
    @Test
    public void giveBusList_whenGetAllBus_thenReturnBusList() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2024-03-30", dateFormat);
        Bus bus1 = Bus.builder()
                .busName("KSRTC")
                .departureDate(date)
                .pickupLocation("chennai")
                .dropLocation("Trichy")
                .price(1000)
                .available_seats(30)
                .seats(30)
                .build();
        given(busRepository.findAll()).willReturn(List.of(bus1,bus));
        List<BusDto> busDtos=busService.getAllBus();
        assertThat(busDtos).isNotNull();
        assertThat(busDtos.size()).isGreaterThan(0);
    }
    @DisplayName("Test when an List of Bus Given returns List Of Bus")
    @Test
    public void giveBusId_whenGetBus_thenReturnBus() {
        given(busRepository.findById(bus.getId())).willReturn(Optional.of(bus));
        BusDto busDto=modelMapper.map(bus,BusDto.class);
        BusDto BusDto=busService.getBusById(bus.getId());
        Bus bus1=modelMapper.map(busDto,Bus.class);
        assertThat(bus1).isNotNull();
    }
    @DisplayName("Test when an Bus Id Given returns Updated bus")
    @Test
    public void givenBusId_whenUpdateBus_thenReturnUpdatedBus(){
        given(busRepository.save(bus)).willReturn(bus);
        given(busRepository.findById(bus.getId())).willReturn(Optional.of(bus));
        bus.setPrice(800);
        BusDto busDto = modelMapper.map(bus, BusDto.class);
        BusDto savedDto = busService.updateBus(busDto,bus.getId());
        Bus savedBus = modelMapper.map(savedDto, Bus.class);
        assertThat(savedBus.getPrice()).isEqualTo(800);
    }

}
