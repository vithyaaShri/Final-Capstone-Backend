package mainproject.busReservation.service.impl;
import lombok.AllArgsConstructor;
import mainproject.busReservation.dto.JwtAuthResponse;
import mainproject.busReservation.dto.LoginDto;
import mainproject.busReservation.dto.RegisterDto;
import mainproject.busReservation.entity.Role;
import mainproject.busReservation.entity.UserSecurity;
import mainproject.busReservation.exception.UserApiException;
import mainproject.busReservation.repository.RoleSecurityRepo;
import mainproject.busReservation.repository.UserSecurityRepo;
import mainproject.busReservation.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mainproject.busReservation.service.AuthService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    UserSecurityRepo userSecurityRepo;
    RoleSecurityRepo roleSecurityRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    @Override
   // To register User Detail to backend
    public String register(RegisterDto registerDto) {

        if(userSecurityRepo.existsByUsername(registerDto.getUsername())){
            throw new UserApiException(HttpStatus.BAD_REQUEST,"User name Already exsits");
        }
        if(userSecurityRepo.existsByEmail(registerDto.getEmail())){
            throw new UserApiException(HttpStatus.BAD_REQUEST,"Email Already exsits");
        }
        UserSecurity userSecurity =new UserSecurity();
        userSecurity.setName(registerDto.getName());
        userSecurity.setUsername(registerDto.getUsername());
        userSecurity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userSecurity.setEmail(registerDto.getEmail());
        Set<Role> roles=new HashSet<>();
        Role userrole=roleSecurityRepo.findByName("ROLE_USER");
        roles.add(userrole);
        userSecurity.setRoleSet(roles);
        userSecurityRepo.save(userSecurity);
        return "User Registered Successfully";
    }
    //Validate the Registered user and Login
    @Override
    public JwtAuthResponse login(LoginDto loginDto)
    {
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtUtil.generate(loginDto.getUsername());
        Optional<UserSecurity> user=userSecurityRepo.findByUsernameOrEmail(loginDto.getUsername(),loginDto.getUsername());
        String role=null;
        if(user.isPresent()){
            UserSecurity loggedInUser=user.get();
          // Optional<Role> optionalRoles= loggedInUser.getRoleSet().stream().findFirst();
           List<Role> roleList=loggedInUser.getRoleSet().stream()
                   .filter(_role->_role.getName().equalsIgnoreCase("ROLE_ADMIN"))
                   .collect(Collectors.toList());
            if(roleList.size()>0){
                Role dbrole=roleList.get(0);
                role=dbrole.getName();

            }
            else{
                Role userRole=loggedInUser.getRoleSet().stream().findFirst().get();
                role=userRole.getName();
            }
        }
        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
        jwtAuthResponse.setRole(role);
        jwtAuthResponse.setAccessToken(token);
        return jwtAuthResponse;
    }
}


