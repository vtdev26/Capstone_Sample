package capstone.project.Demo_Project.controller;

import capstone.project.Demo_Project.domain.payload.request.ChangePasswordRequest;
import capstone.project.Demo_Project.domain.request.UserRequestDto;
import capstone.project.Demo_Project.domain.response.UserResponseDto;
import capstone.project.Demo_Project.domain.response.PaginationResponse;
import capstone.project.Demo_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserResponseDto findById(@Valid @PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/filter/{name}")
    public UserResponseDto getByUsername(@Valid @PathVariable String name) {
        return userService.findUserByUsername(name);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.update(id, userRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("")
    public PaginationResponse filter(@RequestParam("filterBy") String filter, @RequestParam("key") String key,
                                     Pageable pageable) {
        return userService.filter(filter, key, pageable);
    }

    @PutMapping("/{id}/disable")
    public void disable(@PathVariable Long id) {
        userService.disable(id);
    }

    @PutMapping("/{id}/change-password")
    public void changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest request){
        userService.changePassword(id, request);
    }

    @GetMapping("/current-user")
    public UserDetails getCurrentUser(){
        return userService.getCurrentUser();
    }
}
