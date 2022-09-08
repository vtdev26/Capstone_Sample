package capstone.project.Demo_Project.services.impl;

import capstone.project.Demo_Project.domain.entities.User;
import capstone.project.Demo_Project.domain.payload.request.ChangePasswordRequest;
import capstone.project.Demo_Project.domain.request.UserRequestDto;
import capstone.project.Demo_Project.domain.response.PaginationResponse;
import capstone.project.Demo_Project.domain.response.UserResponseDto;
import capstone.project.Demo_Project.errorhandling.DemoException;
import capstone.project.Demo_Project.errorhandling.ErrorTypes;
import capstone.project.Demo_Project.repositories.UserRepository;
import capstone.project.Demo_Project.services.UserService;
import capstone.project.Demo_Project.utilities.BeanUtility;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserResponseDto findUserByUsername(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        if (!user.isPresent()) {
            logger.error("Can not found user with user name: {}", userName);
            throw new DemoException(ErrorTypes.USER_NOT_FOUND, userName);
        }
        return BeanUtility.converValue(user.get(), UserResponseDto.class);
    }

    @Override
    public UserResponseDto findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            logger.error("Can not found user with user name: {}", userId);
            throw new DemoException(ErrorTypes.USER_NOT_FOUND, userId.intValue());
        }
        return BeanUtility.converValue(user.get(), UserResponseDto.class);
    }

    @Override
    public UserResponseDto create(UserRequestDto request) {
        logger.info("Starting create user!");
        if (ObjectUtils.isEmpty(request)) {
            logger.error("Request must not be empty!");
            throw new DemoException(ErrorTypes.INVALID_REQUEST);

        }
        Optional<User> userDuplicate = userRepository.findByUsername(request.getUsername());
        if (userDuplicate.isPresent()) {
            logger.error("user with user name: {} existing!", request.getUsername());
            throw new DemoException(ErrorTypes.USER_WITH_NAME_ALREADY_EXISTS, request.getUsername());
        }
        User user = BeanUtility.converValue(request, User.class);
        user.setCreatedDate(new Date());
        User newUser = userRepository.save(user);
        logger.info("user created success!");
        return BeanUtility.converValue(newUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto request) {
        logger.info("Starting update user!");
        if (ObjectUtils.isEmpty(request)) {
            logger.error("Request is empty with user id {}", id);
            throw new DemoException(ErrorTypes.INVALID_REQUEST);
        }
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            logger.error("Can not found user with id {}", id);
            throw new DemoException(ErrorTypes.USER_NOT_FOUND);
        }
        User user = BeanUtility.converValue(request, User.class);
        user.setUserId(id);
        user.setUpdatedDate(new Date());
        userRepository.save(user);
        logger.info("Update user successfully!");
        return BeanUtility.converValue(user, UserResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            logger.error("Can not found user with id {}", id);
            return;
        }
        userRepository.deleteById(id);
        logger.info("Delete user successfully!");
    }

    @Override
    public PaginationResponse filter(String filter, String key, Pageable pageable) {
        Page<User> page = null;
        switch (filter) {
            case "disable":
                if (User.DISABLE_TRUE.equalsIgnoreCase(key)) {
                    page = userRepository.findByDisabledTrue(pageable);
                } else {
                    page = userRepository.findByDisabledFalse(pageable);
                }
                break;
            case "user_name":
                page = userRepository.findAllByUsernameContains(key, pageable);
                break;
            default:
                page = userRepository.findAll(pageable);
        }

        return PaginationResponse.builder()
                .total(page.getTotalElements())
                .numberOfPage(page.getTotalPages())
                .items(page.getContent())
                .build();
    }

    @Override
    public void disable(Long userId) {
        logger.info("Starting disable user with id {}", userId);
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            logger.error("Can not found user with id {}", userId);
            throw new DemoException(ErrorTypes.USER_NOT_FOUND);
        }
        user.get().setDisabled(true);
        userRepository.save(user.get());
        logger.info("Successfully disable user with id {}", userId);
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequest request) {
        logger.info("Starting change password with user id {}", userId);
        if (ObjectUtils.isEmpty(request)) {
            logger.error("Request is empty with user id {}", userId);
            throw new DemoException(ErrorTypes.INVALID_REQUEST);
        }
        Optional<User> existingUser = userRepository.findById(userId);
        if (!existingUser.isPresent()) {
            logger.error("Can not found user with id {}", userId);
            throw new DemoException(ErrorTypes.USER_NOT_FOUND);
        }
        if (encoder.matches(existingUser.get().getPassword(), request.getOldPassword())) {
            logger.error("Password is incorrect with user id {}", userId);
            throw new DemoException(ErrorTypes.PASSWORD_IS_INCORRECT);
        }
        existingUser.get().setPassword(encoder.encode(request.getNewPassword()));
        existingUser.get().setUpdatedDate(new Date());
        userRepository.save(existingUser.get());
        logger.info("Successfully change password with user id {}", userId);
    }

    @Override
    public UserDetails getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDetails.getUsername();
        userDetails.getAuthorities();
        return userDetails;
    }
}
