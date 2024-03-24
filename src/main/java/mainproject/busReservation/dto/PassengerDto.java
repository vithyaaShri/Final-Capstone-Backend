package mainproject.busReservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Passenger Detail
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {
    private Long Id;

    private  String passengerName;

    private Long aadharId;

    private Long contactNo;

    private  String email;
    private int age;
    public boolean cancelStat;

    private boolean journeyStat;

}
