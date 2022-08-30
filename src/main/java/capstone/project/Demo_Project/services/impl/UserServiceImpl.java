package capstone.project.Demo_Project.services.impl;

import capstone.project.Demo_Project.domain.entities.User;
import capstone.project.Demo_Project.domain.request.UserRequestDto;
import capstone.project.Demo_Project.domain.response.UserResponseDto;
import capstone.project.Demo_Project.domain.response.PaginationResponse;
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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private  static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto findUserByUserName(String userName) {
//        User user = userRepository.findByUserName(userName);
        User user = null;
        if (ObjectUtils.isEmpty(user)) {
            logger.error("Can not found user with user name: {}", userName);
            throw new DemoException(ErrorTypes.USER_NOT_FOUND, userName);
        }
        return BeanUtility.converValue(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto findUserById(Long userId) {
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
//        User userDuplicate = userRepository.findByUserName(request.getUserName());
        User userDuplicate = null;
        if (ObjectUtils.isNotEmpty(userDuplicate)) {
            logger.error("user with user name: {} existing!", request.getUserName());
            throw new DemoException(ErrorTypes.USER_WITH_NAME_ALREADY_EXISTS, request.getUserName());
        }
        User user = BeanUtility.converValue(request, User.class);
        User newUser = userRepository.save(user);
        logger.info("user created success!");
        return BeanUtility.converValue(newUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto request) {
        logger.info("Starting update user!");
        if (ObjectUtils.isEmpty(request)) {
            logger.error("Request is empty with user id {}", id);
            return null;
        }
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            logger.error("Can not found user with id {}", id);
            return null;
        }
        User user = BeanUtility.converValue(request, User.class);
        user.setUserId(id);
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
            case "status":
                page = userRepository.findAllByStatusEquals(Integer.parseInt(key), pageable);
                break;
            case "user_name":
                page = userRepository.findAllByUserNameContains(key, pageable);
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
}
