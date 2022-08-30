package capstone.project.Demo_Project.controller;

import capstone.project.Demo_Project.domain.request.UserRequestDto;
import capstone.project.Demo_Project.domain.response.UserResponseDto;
import capstone.project.Demo_Project.domain.response.PaginationResponse;
import capstone.project.Demo_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public UserResponseDto getById(@Valid @PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/filter/{name}")
    public UserResponseDto getByName(@Valid @PathVariable String name) {
        return userService.findUserByUserName(name);
    }

    @PostMapping("")
    public UserResponseDto create(@RequestBody UserRequestDto userRequestDto) {
        return userService.create(userRequestDto);
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
                                     Pageable pageable){
        return userService.filter(filter, key, pageable);
    }
}
