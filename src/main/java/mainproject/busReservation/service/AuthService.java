package mainproject.busReservation.service;


import mainproject.busReservation.dto.JwtAuthResponse;
import mainproject.busReservation.dto.LoginDto;
import mainproject.busReservation.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    JwtAuthResponse login(LoginDto loginDto);
}
