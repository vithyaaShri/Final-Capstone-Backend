package mainproject.busReservation.service.impl;

import mainproject.busReservation.entity.UserSecurity;
import mainproject.busReservation.exception.ResourceNotFoundNameException;
import mainproject.busReservation.repository.UserSecurityRepo;
import mainproject.busReservation.service.UserSecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityRepoImpl implements UserSecurityService {
    @Autowired
    UserSecurityRepo userSecurityRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    //Single user details is get
    @Override
    public UserSecurity getUser(String name) {
        UserSecurity userSecurity=userSecurityRepo.findByUsername(name)
                .orElseThrow(()->new ResourceNotFoundNameException("User","name",name));;
        return userSecurity;
    }

    //To check Old Password is Correct
    public boolean oldPasswordIsValid(UserSecurity userSecurity,String oldPassword){
        return passwordEncoder.matches(oldPassword,userSecurity.getPassword());
    }

    //Change Password by put mapping
    @Override
    public void changePassword(UserSecurity userSecurity, String newPassword) {
        userSecurity.setPassword(passwordEncoder.encode(newPassword));
        userSecurityRepo.save(userSecurity);
    }


}
