package mainproject.busReservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="passenger")

public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private  String passengerName;

    private Long aadharId;

    private Long contactNo;
    @Column(unique = true)
    private  String email;
    private int age;
    @Column(name="TicketCancelled")
    public boolean cancelStat;
    @Column(name="JourneyStatus")
    private boolean journeyStat;

}
