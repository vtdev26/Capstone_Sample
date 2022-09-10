package capstone.project.Demo_Project.services;

import capstone.project.Demo_Project.domain.payload.request.ChangePasswordRequest;
import capstone.project.Demo_Project.domain.request.UserRequestDto;
import capstone.project.Demo_Project.domain.response.UserResponseDto;
import capstone.project.Demo_Project.domain.response.PaginationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserResponseDto findUserByUsername(String userName);

    UserResponseDto findById(Long userId);

    UserResponseDto create(UserRequestDto userRequestDto);

    UserResponseDto update(Long id, UserRequestDto userRequestDto);

    void deleteById(Long id);

    PaginationResponse filter(String filter, String key, Pageable pageable);

    void disable(Long userId);

    void changePassword(Long userId, ChangePasswordRequest request);

    UserDetails getCurrentUser();
}
