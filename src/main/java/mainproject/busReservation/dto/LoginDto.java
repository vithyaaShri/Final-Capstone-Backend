package mainproject.busReservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//For First Login Page
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private  String username;
    private String password;
}
