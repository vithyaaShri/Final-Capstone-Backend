package mainproject.busReservation.repository;

import mainproject.busReservation.entity.BusBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusBookingRepository extends JpaRepository<BusBooking,Long> {
    BusBooking findByPassenger_id(Long id);
}
