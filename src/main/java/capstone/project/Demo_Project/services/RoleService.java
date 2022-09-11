package capstone.project.Demo_Project.services;

import capstone.project.Demo_Project.domain.entities.Role;
import capstone.project.Demo_Project.domain.request.RoleRequestDTO;
import capstone.project.Demo_Project.domain.response.RoleResponseDto;

public interface RoleService {

    RoleResponseDto findById(Long id);

    RoleResponseDto findByName(String roleName);

    RoleResponseDto create(RoleRequestDTO roleRequestDTO);

    RoleResponseDto update(Long id, RoleRequestDTO roleRequestDTO);

    void deleteById(Long id);


}
