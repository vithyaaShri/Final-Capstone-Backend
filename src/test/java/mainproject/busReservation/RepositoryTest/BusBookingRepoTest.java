package mainproject.busReservation.RepositoryTest;

import mainproject.busReservation.dto.BusBookingDto;
import mainproject.busReservation.dto.BusDto;
import mainproject.busReservation.entity.Bus;
import mainproject.busReservation.entity.BusBooking;
import mainproject.busReservation.entity.Passenger;
import mainproject.busReservation.repository.BusBookingRepository;
import mainproject.busReservation.repository.BusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BusBookingRepoTest {
    @Autowired
    private BusBookingRepository busBookingRepository;
    @Autowired
    private ModelMapper modelMapper;
    private BusBooking busBooking;
    @BeforeEach
    public  void setup(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2024-03-30", dateFormat);
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
    @DisplayName("Test an BusBooking getting saved in DB")
    @Test
    public void givenBusBooking_whenSaved_returnSavedBusBooking(){
        BusBooking savedBusBooking=busBookingRepository.save(busBooking);
        assertThat(savedBusBooking).isNotNull();
        assertThat(savedBusBooking.getId()).isGreaterThan(0);
    }
    @DisplayName("Test an BusBooking getting converted to DTO class")
    @Test
    public void testEntityToDtoConversion() {
        // Create an Order entity


        // Convert the entity to DTO
        BusBookingDto busBookingDto = modelMapper.map(busBooking,BusBookingDto.class);

        // Assert that the DTO contains the expected values
        assertNotNull(busBookingDto);
        assertEquals("1234",busBookingDto.getBookingId());
        assertEquals("john",busBookingDto.getPassenger().getPassengerName());
    }
    @DisplayName("Test when given an list of BusBooking return the List of BusBooking")
    @Test
    public void givenBusBookingList_whenFindAll_returnSavedBusBookingList(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2024-03-30", dateFormat);
        Passenger passenger1 = Passenger.builder()
                .passengerName("peter")
                .aadharId(1234567L)
                .contactNo(987654321L)
                .email("test123@123")
                .age(28)
                .cancelStat(false)
                .journeyStat(true)
                .build();

        BusBooking busBooking1 = BusBooking.builder()
                .bookingId("1234")
                .bookingDate("2024-03-30")
                .passenger(passenger1)
                .busName("Mss")
                .fee(1000)
                .build();
        busBookingRepository.save(busBooking1);
        busBookingRepository.save(busBooking);
        List<BusBooking> busList=busBookingRepository.findAll();
        assertThat(busList).isNotNull();
        assertThat(busList.size()).isEqualTo(2);

    }



}
