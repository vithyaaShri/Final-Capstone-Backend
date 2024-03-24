package mainproject.busReservation.controller;

import mainproject.busReservation.dto.PassengerDto;
import mainproject.busReservation.dto.PasswordRequestUtil;
import mainproject.busReservation.entity.UserSecurity;
import mainproject.busReservation.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserSecurityController {
    @Autowired
    UserSecurityService userSecurityService;
    //Get User Based On userName
    @GetMapping("/{Name}")
    public ResponseEntity<UserSecurity> getPassengerByName(@PathVariable("Name")String name)
    {
        UserSecurity getUser=userSecurityService.getUser(name);
        return new ResponseEntity<UserSecurity>(getUser, HttpStatus.OK);
    }

    //Post Mapping to Check for Old Password and New Password changed if old password entered is matched withDB
    @PostMapping
    public String passwordChange(@RequestBody PasswordRequestUtil passwordRequestUtil){
        UserSecurity userSecurity=userSecurityService.getUser(passwordRequestUtil.getUsername());
        if(!userSecurityService.oldPasswordIsValid(userSecurity,passwordRequestUtil.getOldPassword())){
            return "Incorrect Old Password";
        }
        else{
            userSecurityService.changePassword(userSecurity,passwordRequestUtil.getNewPassword());
            return "Password changed Successfully";
        }
    }

}
