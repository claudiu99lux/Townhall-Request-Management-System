package service;


import dto.AddressDto;
import dto.SecureUserDto;
import entity.Address;
import entity.Request;
import entity.User;
import mapper.AddressMapper;
import repository.AddressRepo;
import repository.RequestRepo;
import repository.UserRepo;
import validator.AddressValidator;

import java.util.ArrayList;
import java.util.List;

public class AddressService {
    AddressRepo addressRepo;
    RequestRepo requestRepo;
    UserRepo userRepo;
    AddressValidator addressValidator;
    AddressMapper mapper;

    public AddressService(){
        addressRepo = new AddressRepo();
        userRepo = new UserRepo();
        requestRepo = new RequestRepo();
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

    public void deleteAddress(AddressDto address){
        Address a = addressRepo.findAddressById(address.getId());
        for(Request r : a.getRequests()){
            requestRepo.deleteRequest(r);
        }
        addressRepo.deleteAddress(a);
    }

    public AddressDto getAddressById(String id){
        return mapper.addressToAddressDto(addressRepo.findAddressById(id));
    }

}
