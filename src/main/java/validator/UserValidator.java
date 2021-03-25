package validator;

import dto.UserDto;
import entity.User;
import exception.IllegalValueException;
import exception.IncorrectCredentialsException;
import exception.UserExceptionMessages;
import exception.UserNotFoundException;

public class UserValidator {

    public UserValidator(){}

    public void validateLogin(User user, String enteredPassword) throws UserNotFoundException, IncorrectCredentialsException {
        if(user == null)
            throw new UserNotFoundException(UserExceptionMessages.EMAIL_NOT_EXISTS);
        if(!user.getPassword().equals(enteredPassword)){

            throw new IncorrectCredentialsException(UserExceptionMessages.INCORRECT_CREDENTIALS);
        }
    }

    public void validateRegister(User user) throws IllegalValueException {
        if(user.getFirstName().equals("") || user.getFirstName()==null || user.getLastName().equals("") || user.getLastName()==null)
            throw new IllegalValueException(UserExceptionMessages.ILLEGAL_NAME);
        if(user.getEmail().equals("") || user.getEmail() == null)
            throw new IllegalValueException(UserExceptionMessages.ILLEGAL_EMAIL);
        validateEmail(user.getEmail());
        if(user.getCNP().equals("") || user.getCNP()==null)
            throw new IllegalValueException(UserExceptionMessages.ILLEGAL_CNP);
        validateCNP(user.getCNP());
        if(user.getPassword().equals("") || user.getPassword()==null)
            throw new IllegalValueException(UserExceptionMessages.ILLEGAL_PASSWORD);
        validatePassword(user.getPassword());
    }

    public void validateRegister(UserDto user) throws IllegalValueException {
        if(user.getFirstName().equals("") || user.getFirstName()==null || user.getLastName().equals("") || user.getLastName()==null)
            throw new IllegalValueException(UserExceptionMessages.ILLEGAL_NAME);
        if(user.getEmail().equals("")|| user.getEmail() == null)
            throw new IllegalValueException(UserExceptionMessages.ILLEGAL_EMAIL);
        validateEmail(user.getEmail());
        if(user.getCNP().equals("") || user.getCNP()==null)
            throw new IllegalValueException(UserExceptionMessages.ILLEGAL_CNP);
        validateCNP(user.getCNP());
        if(user.getPassword().equals("") || user.getPassword()==null)
            throw new IllegalValueException(UserExceptionMessages.ILLEGAL_PASSWORD);
        validatePassword(user.getPassword());
    }

    public void validateMatchingPasswords(String password, String confirmedPassword) throws IllegalValueException {
        if(!password.equals(confirmedPassword))
            throw new IllegalValueException(UserExceptionMessages.PASSWORDS_NOT_MATCH);
    }

    public void validateEmail(String email) throws IllegalValueException {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(!email.matches(regex))
            throw new IllegalValueException(UserExceptionMessages.ILLEGAL_EMAIL_FORMAT);
    }

    public void validatePassword(String password) throws IllegalValueException{
        if(password.length()<6)
            throw new IllegalValueException(UserExceptionMessages.PASSWORD_LENGTH_ERROR);
    }

    public void validateCNP(String CNP) throws IllegalValueException{
        if(CNP.length()!=13)
            throw new IllegalValueException(UserExceptionMessages.INVALID_CNP);
    }

}
