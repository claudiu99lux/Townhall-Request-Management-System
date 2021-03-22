package mapper;

import dto.SecureUserDto;
import dto.UserDto;
import entity.User;

public class UserMapper {

    public UserMapper(){}

    public User dtoToEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCNP(userDto.getCNP());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAddresses(userDto.getAddresses());
        return user;
    }

    public UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setCNP(user.getCNP());
        userDto.setAddresses(user.getAddresses());
        return userDto;
    }

    public SecureUserDto entityToSecureUserDto(User user){
        SecureUserDto userDto = new SecureUserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setCNP(user.getCNP());
        userDto.setAddresses(user.getAddresses());
        return userDto;
    }

    public User secureUserDtoToEntity(SecureUserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCNP(userDto.getCNP());
        user.setAddresses(userDto.getAddresses());
        return user;
    }
}
