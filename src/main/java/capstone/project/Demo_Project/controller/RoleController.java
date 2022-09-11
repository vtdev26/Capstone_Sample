package capstone.project.Demo_Project.controller;

import capstone.project.Demo_Project.domain.request.RoleRequestDTO;
import capstone.project.Demo_Project.domain.response.RoleResponseDto;
import capstone.project.Demo_Project.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("")
    public RoleResponseDto create(@Valid @RequestBody RoleRequestDTO roleRequestDto){
        return roleService.create(roleRequestDto);
    }

    @GetMapping("/{id}")
    public RoleResponseDto findById(@Valid @PathVariable Long id) {
        return roleService.findById(id);
    }

    @GetMapping("/filter/{name}")
    public RoleResponseDto getByName(@Valid @PathVariable String name) {
        return roleService.findByName(name);
    }

    @PutMapping("/{id}")
    public RoleResponseDto update(@PathVariable Long id, @Valid @RequestBody RoleRequestDTO roleRequestDto) {
        return roleService.update(id, roleRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
    }


}
