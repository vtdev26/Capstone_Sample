package capstone.project.Demo_Project.services.impl;

import capstone.project.Demo_Project.domain.entities.Role;
import capstone.project.Demo_Project.domain.entities.User;
import capstone.project.Demo_Project.domain.enums.ERole;
import capstone.project.Demo_Project.domain.request.RoleRequestDTO;
import capstone.project.Demo_Project.domain.response.RoleResponseDto;
import capstone.project.Demo_Project.domain.response.UserResponseDto;
import capstone.project.Demo_Project.errorhandling.DemoException;
import capstone.project.Demo_Project.errorhandling.ErrorTypes;
import capstone.project.Demo_Project.repositories.RoleRepository;
import capstone.project.Demo_Project.services.RoleService;
import capstone.project.Demo_Project.utilities.BeanUtility;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);


    @Autowired
    RoleRepository roleRepository;

    @Override
    public RoleResponseDto findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (!role.isPresent()) {
            logger.error("Can not found user with user id: {}", id);
            throw new DemoException(ErrorTypes.ROLE_NOT_FOUND, id.intValue());
        }
        return BeanUtility.converValue(role.get(), RoleResponseDto.class);
    }

    @Override
    public RoleResponseDto findByName(String roleName) {

        Optional<Role> role = roleRepository.findByName(ERole.valueOf(roleName));

        if (ObjectUtils.isEmpty(role)) {
            logger.error("Can not found user with user name: {}", roleName);
            throw new DemoException(ErrorTypes.USER_NOT_FOUND, roleName);
        }
        return BeanUtility.converValue(role, RoleResponseDto.class);
    }

    @Override
    public RoleResponseDto create(RoleRequestDTO request) {
        logger.info("Starting create role!");
        if (ObjectUtils.isEmpty(request)) {
            logger.error("Request must not be empty!");
            throw new DemoException(ErrorTypes.INVALID_REQUEST);

        }
        Optional<Role> roleDuplicate = roleRepository.findByName(ERole.valueOf(request.getRoleName()));
        if (ObjectUtils.isNotEmpty(roleDuplicate)) {
            logger.error("role with name: {} existing!", request.getRoleName());
            throw new DemoException(ErrorTypes.USER_WITH_NAME_ALREADY_EXISTS, request.getRoleName());
        }
        Role role = BeanUtility.converValue(request, Role.class);
        Role newRole = roleRepository.save(role);
        logger.info("role created success!");
        return BeanUtility.converValue(newRole, RoleResponseDto.class);
    }

    @Override
    public RoleResponseDto update(Long id, RoleRequestDTO request) {
        logger.info("Starting update role!");
        if (ObjectUtils.isEmpty(request)) {
            logger.error("Request is empty with role id {}", id);
            throw new DemoException(ErrorTypes.USER_NOT_FOUND);
        }

        Optional<Role> existingRole = roleRepository.findById(id);
        if (!existingRole.isPresent()) {
            logger.error("Can not found role with id {}", id);
            return null;
        }

        Role role = BeanUtility.converValue(request, Role.class);
        role.setRoleId(id);

        roleRepository.save(role);
        logger.info("Update role successfully!");
        return BeanUtility.converValue(role, RoleResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Role> existingRole = roleRepository.findById(id);
        if (!existingRole.isPresent()) {
            logger.error("Can not found role with id {}", id);
            return;
        }
        roleRepository.deleteById(id);
        logger.info("Delete role successfully!");
    }
}
