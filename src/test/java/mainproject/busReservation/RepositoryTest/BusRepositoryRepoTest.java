package mainproject.busReservation.RepositoryTest;

import mainproject.busReservation.dto.BusDto;
import mainproject.busReservation.dto.PassengerDto;
import mainproject.busReservation.entity.Bus;
import mainproject.busReservation.entity.Passenger;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BusRepositoryRepoTest {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private ModelMapper modelMapper;
    private Bus bus;

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
    @DisplayName("Test an Bus getting saved in DB")
    @Test
    public void givenBus_whenSaved_returnSavedBus(){
        Bus savedBus=busRepository.save(bus);
        assertThat(savedBus).isNotNull();
        assertThat(savedBus.getId()).isGreaterThan(0);
    }

    @DisplayName("Test an Bus getting converted to DTO class")
    @Test
    public void testEntityToDtoConversion() {
        // Create an Order entity


        // Convert the entity to DTO
        BusDto busDto = modelMapper.map(bus,BusDto.class);

        // Assert that the DTO contains the expected values
        assertNotNull(busDto);
        assertEquals("Mss",busDto.getBusName());
        assertEquals("chennai", busDto.getPickupLocation());
        assertEquals("Mettur",busDto.getDropLocation());
    }
    @DisplayName("Test when given an list of Bus return the List of Bus")
    @Test
    public void givenBusList_whenFindAll_returnSavedBusList(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2024-03-26", dateFormat);
        Bus bus1 = Bus.builder()
                .busName("SETC")
                .departureDate(date)
                .pickupLocation("chennai")
                .dropLocation("Trichy")
                .price(900)
                .available_seats(30)
                .seats(30)
                .build();
        busRepository.save(bus);
        busRepository.save(bus1);
        List<Bus> busList=busRepository.findAll();
        assertThat(busList).isNotNull();
        assertThat(busList.size()).isEqualTo(2);

    }
    @DisplayName("Test when given Bus ID  update the Bus details")
    @Test
    public void givenBus_WhenUpdateBus_ThenReturnUpdatedBus(){
        busRepository.save(bus);
        Bus savedbus=busRepository.findById(bus.getId()).get();
        savedbus.setPrice(1000);
        Bus updatedBus=busRepository.save(savedbus);
        assertThat(updatedBus.getPrice()).isEqualTo(1000);
    }
    @DisplayName("Test when given Bus details to delete removes info from database")
    @Test
    public void givenBus_WhenDeleteBus_ThenRemoveFromDb() {
        busRepository.save(bus);
        busRepository.deleteById(bus.getId());
        Optional<Bus> optionalBus=busRepository.findById(bus.getId());
        assertThat(optionalBus).isEmpty();
    }
    @DisplayName("Test when given an Bus ID return the Bus from DB")
    @Test
    public void givenBusId_whenFind_returnSavedBus(){
        busRepository.save(bus);
        Bus savedBus=busRepository.findById(bus.getId()).get();
        assertThat(savedBus).isNotNull();

    }

}
