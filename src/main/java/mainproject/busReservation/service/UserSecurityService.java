package mainproject.busReservation.service;

import mainproject.busReservation.dto.LoginDto;
import mainproject.busReservation.entity.UserSecurity;

public interface UserSecurityService {
    UserSecurity getUser(String name);

    boolean oldPasswordIsValid(UserSecurity userSecurity,String oldPassword);
    void changePassword(UserSecurity userSecurity, String newPassword);
}
