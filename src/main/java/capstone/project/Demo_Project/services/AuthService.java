package capstone.project.Demo_Project.services;

import capstone.project.Demo_Project.domain.payload.request.LoginRequest;
import capstone.project.Demo_Project.domain.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signupRequest);

    ResponseEntity<?> logout();
}

