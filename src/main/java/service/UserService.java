package service;

import dto.SecureUserDto;
import dto.UserDto;
import entity.User;
import exception.*;
import mapper.UserMapper;
import repository.UserRepo;
import validator.UserValidator;

public class UserService {
    UserRepo userRepo;
    UserValidator userValidator;
    UserMapper mapper;

    public UserService(){
        userRepo = new UserRepo();
        userValidator = new UserValidator();
        mapper = new UserMapper();
    }

    public SecureUserDto loginUser(String email, String password) throws UserNotFoundException, IncorrectCredentialsException {
        User user = userRepo.findUserByEmail(email);
        userValidator.validateLogin(user, password);
        SecureUserDto secureUserDto = mapper.entityToSecureUserDto(user);
        return secureUserDto;
    }

    public void registerUser(UserDto userDto) throws IllegalValueException, DuplicateEmailException {
        User user = mapper.dtoToEntity(userDto);
        userValidator.validateRegister(user);
        User found = userRepo.findUserByEmail(user.getEmail());
        if(found != null)
            throw new DuplicateEmailException(UserExceptionMessages.EMAIL_ALREADY_USED);
        userRepo.insertNewUser(user);
    }

    public void updatePassword(SecureUserDto user, String newPassword){
        User updatedUser = mapper.secureUserDtoToEntity(user);
        updatedUser.setPassword(newPassword);
        userRepo.updateUser(updatedUser);
    }
}
