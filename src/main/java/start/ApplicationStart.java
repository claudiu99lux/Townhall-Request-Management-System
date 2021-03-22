package start;

import controller.Controller;
import dto.SecureUserDto;
import dto.UserDto;
import entity.*;
import repository.AddressRepo;
import repository.UserRepo;
import service.UserService;

import java.util.List;


public class ApplicationStart {

    public static void main(String[] args) {


        UserRepo ur = new UserRepo();
        AddressRepo ar = new AddressRepo();
        /*
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Ana");
        ur.insertNewUser(user);
         */

        Controller c = new Controller();


    }
}
