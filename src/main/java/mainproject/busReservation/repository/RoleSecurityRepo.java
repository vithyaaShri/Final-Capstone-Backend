package mainproject.busReservation.repository;



import mainproject.busReservation.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleSecurityRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
