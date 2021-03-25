package service;


import dto.AddressDto;
import dto.SecureUserDto;
import entity.Address;
import entity.User;
import mapper.AddressMapper;
import repository.AddressRepo;
import repository.UserRepo;
import validator.AddressValidator;

import java.util.ArrayList;
import java.util.List;

public class AddressService {
    AddressRepo addressRepo;
    UserRepo userRepo;
    AddressValidator addressValidator;
    AddressMapper mapper;

    public AddressService(){
        addressRepo = new AddressRepo();
        userRepo = new UserRepo();
        mapper = new AddressMapper();
        addressValidator = new AddressValidator();
    }

    public List<AddressDto> getUserAddresses(SecureUserDto user){
        List<AddressDto> addresses = new ArrayList<AddressDto>();
        List<Address> foundAddresses = addressRepo.findAddressByUserId(user.getId());
        for(Address a : foundAddresses){
            addresses.add(mapper.addressToAddressDto(a));
        }
        return addresses;
    }

    public void addNewAddress(AddressDto address, String userID){
        Address a = mapper.addressDtoToAddress(address);
        User u = userRepo.findUserById(userID);
        a.setUser(u);
        u.getAddresses().add(a);
        addressRepo.insertNewAddress(a);
        userRepo.updateUser(u);
    }

    public void deleteAddress(AddressDto adddress){
        Address a = mapper.addressDtoToAddress(adddress);
        addressRepo.deleteAddress(a);
    }

    public AddressDto getAddressById(String id){
        return mapper.addressToAddressDto(addressRepo.findAddressById(id));
    }

}
