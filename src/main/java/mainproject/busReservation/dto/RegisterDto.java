package mainproject.busReservation.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//For Registration Form
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private  String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private  String email;
}
