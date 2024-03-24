package mainproject.busReservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
//Api exception
@Getter
@AllArgsConstructor
public class UserApiException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;
}
