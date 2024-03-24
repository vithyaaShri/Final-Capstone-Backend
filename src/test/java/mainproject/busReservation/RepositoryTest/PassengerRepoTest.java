package mainproject.busReservation.RepositoryTest;

import mainproject.busReservation.dto.PassengerDto;
import mainproject.busReservation.entity.Passenger;
import mainproject.busReservation.repository.PassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PassengerRepoTest {
    @Autowired
    private PassengerRepository passengerRepo;
    @Autowired
    private ModelMapper modelMapper;
    private Passenger passenger;

    @BeforeEach
    public  void setup(){
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
    @DisplayName("Test an Passenger getting saved in DB")
    @Test
    public void givenPassenger_whenSaved_returnSavedPassenger(){
        Passenger savedPassenger=passengerRepo.save(passenger);
        assertThat(savedPassenger).isNotNull();
        assertThat(savedPassenger.getId()).isGreaterThan(0);
    }
    @DisplayName("Test an Passenger getting converted to DTO class")
    @Test
    public void testEntityToDtoConversion() {
        // Create an Order entity


        // Convert the entity to DTO
        PassengerDto passDto = modelMapper.map(passenger, PassengerDto.class);

        // Assert that the DTO contains the expected values
        assertNotNull(passDto);
        assertEquals("john", passDto.getPassengerName());
        assertEquals(1234567L,passDto.getAadharId() );
        assertEquals("test@123", passDto.getEmail());
    }
    @DisplayName("Test when given an list of Passenger return the List of passenger")
    @Test
    public void givenPassengerList_whenFindAll_returnSavedPassengerList(){
        Passenger passenger1=Passenger.builder()
                .passengerName("john")
                .aadharId(1234567L)
                .contactNo(987654321L)
                .email("test123@123")
                .age(28)
                .cancelStat(false)
                .journeyStat(true)
                .build();
        passengerRepo.save(passenger);
        passengerRepo.save(passenger1);
        List<Passenger> passengerList=passengerRepo.findAll();
        assertThat(passengerList).isNotNull();
        assertThat(passengerList.size()).isEqualTo(2);

    }
    @DisplayName("Test when given email return Passenger detail")
    @Test
    public void givenPassengerEmail_WhenFindByEmail_ThenReturnEmployee(){
        passengerRepo.save(passenger);
        Passenger passengerDB=passengerRepo.findByEmail(passenger.getEmail());
        assertThat(passengerDB).isNotNull();
    }
    @DisplayName("Test when given Passenger ID  update the Passenger details")
    @Test
    public void givenPassenger_WhenUpdatePassenger_ThenReturnUpdatedPassenger(){
        passengerRepo.save(passenger);
        Passenger savedPassenger=passengerRepo.findById(passenger.getId()).get();
        savedPassenger.setEmail("john@gmail.com");
        Passenger updatedPassenger=passengerRepo.save(savedPassenger);
        assertThat(updatedPassenger.getEmail()).isEqualTo("john@gmail.com");
    }
    @DisplayName("Test when given Passenger details to delete removes info from database")
    @Test
    public void givenPassenger_WhenDeletePassenger_ThenRemovePassenger() {
        passengerRepo.save(passenger);
        passengerRepo.deleteById(passenger.getId());
        Optional<Passenger> optionalPassenger=passengerRepo.findById(passenger.getId());
        assertThat(optionalPassenger).isEmpty();
    }
    @DisplayName("Test when given an Passenger ID return the Passenger from DB")
    @Test
    public void givenPassengerId_whenFind_returnSavedPassenger(){
        passengerRepo.save(passenger);
        Passenger savedPassenger=passengerRepo.findById(passenger.getId()).get();
        assertThat(savedPassenger).isNotNull();

    }



}
