package mainproject.busReservation.ServiceTest;

import mainproject.busReservation.dto.BusBookingDto;
import mainproject.busReservation.dto.PassengerDto;
import mainproject.busReservation.entity.BusBooking;
import mainproject.busReservation.entity.Passenger;
import mainproject.busReservation.repository.BusBookingRepository;
import mainproject.busReservation.repository.PassengerRepository;
import mainproject.busReservation.service.impl.BusBookingImpl;
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
public class BusBookingServiceTest {

    private BusBooking busBooking;
    @Spy
    private ModelMapper modelMapper=new ModelMapper();
    @Mock
    private BusBookingRepository busBookingRepository;
    @InjectMocks
    private BusBookingImpl busBookingImpl;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse("2024-03-30", dateFormat);
    @BeforeEach
    public  void setup(){
    Passenger passenger = Passenger.builder()
            .passengerName("john")
            .aadharId(1234567L)
            .contactNo(987654321L)
            .email("test@123")
            .age(28)
            .cancelStat(false)
            .journeyStat(true)
            .build();

    busBooking=BusBooking.builder()
            .bookingId("1234")
                .bookingDate("2024-03-30")
                .passenger(passenger)
                .busName("Mss")
                .fee(1000)
                .build();
}
    @DisplayName("Test when an List of BusBooking Given returns List Of Bus Booking")
    @Test
    public void giveBookingList_whenGetAll_thenReturnBookingList() {
        Passenger passenger1 = Passenger.builder()
                .passengerName("Peter")
                .aadharId(1234567L)
                .contactNo(987654321L)
                .email("test12@123")
                .age(28)
                .cancelStat(false)
                .journeyStat(true)
                .build();

        BusBooking busBooking1 = BusBooking.builder()
                .bookingId("12345")
                .bookingDate("2024-03-30")
                .passenger(passenger1)
                .busName("Karudas")
                .fee(1000)
                .build();
        given(busBookingRepository.findAll()).willReturn(List.of(busBooking1,busBooking));
        List<BusBookingDto> BusDtos=busBookingImpl.getAll();
        assertThat(BusDtos).isNotNull();
        assertThat(BusDtos.size()).isGreaterThan(0);
    }
//    @DisplayName("Test when an List of Booking Given returns List Of Booking")
//    @Test
//    public void givePassengerId_whenGetPassenger_thenReturnPassenger() {
//        given(busBookingRepository.findById(busBooking.getId())).willReturn(Optional.of(busBooking));
//        BusBookingDto BookingDto=modelMapper.map(busBooking,BusBookingDto.class);
//        BusBookingDto bookDto=busBookingImpl.getById(busBooking.getId());
//        BusBooking busBooking1=modelMapper.map(bookDto,BusBooking.class);
//        assertThat(busBooking1).isNotNull();
//    }

}
