package mainproject.busReservation.repository;

import mainproject.busReservation.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
    Passenger findByEmail(String email);
}
