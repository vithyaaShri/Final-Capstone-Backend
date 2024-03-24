package mainproject.busReservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//Email Already Exists Exception Class
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExsist extends RuntimeException{
    private  String message;
    public  EmailAlreadyExsist(String message){
        super(message);
    }
}
