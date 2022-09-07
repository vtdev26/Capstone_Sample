package capstone.project.Demo_Project.services;

import capstone.project.Demo_Project.domain.request.UserRequestDto;
import capstone.project.Demo_Project.domain.response.UserResponseDto;
import capstone.project.Demo_Project.domain.response.PaginationResponse;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponseDto findUserByUserName(String userName);

    UserResponseDto findUserById(Long userId);

    UserResponseDto create(UserRequestDto userRequestDto);

    UserResponseDto update(Long id, UserRequestDto userRequestDto);

    void deleteById(Long id);

    PaginationResponse filter(String filter, String key, Pageable pageable);
}
