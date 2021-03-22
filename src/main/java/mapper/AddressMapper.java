package mapper;

import dto.AddressDto;
import dto.ApartmentAddressDto;
import dto.HouseAddressDto;
import entity.Address;
import entity.ApartmentAddress;
import entity.HouseAddress;

public class AddressMapper {
    public AddressMapper(){}

    public AddressDto addressToAddressDto(Address a){
        AddressDto address;
        if(a.getBuildingType()==1)
            address = addressToHouseAddressDto(a);
        else
            address = addressToApartmentAddressDto(a);

        return address;
    }

    private HouseAddressDto addressToHouseAddressDto(Address a){
        HouseAddressDto ha = new HouseAddressDto();
        ha.setId(a.getId());
        ha.setBuildingType(1);
        ha.setStreet(a.getStreet());
        ha.setNumber(a.getNumber());
        ha.setUser(a.getUser());

        return ha;
    }

    private ApartmentAddressDto addressToApartmentAddressDto(Address a){
        ApartmentAddressDto ap = new ApartmentAddressDto();
        ap.setId(a.getId());
        ap.setBuildingType(2);
        ap.setStreet(a.getStreet());
        ap.setNumber(a.getNumber());
        ap.setUser(a.getUser());
        ap.setFloor(((ApartmentAddress)a).getFloor());
        ap.setApartmentNumber(((ApartmentAddress)a).getApartmentNumber());

        return ap;
    }

    public Address addressDtoToAddress(AddressDto a){
        Address address;
        if(a.getBuildingType()==1)
            address = houseAddressDtoToAddress(a);
        else
            address = apartmentAddressDtoToAddress(a);
        return address;
    }

    private Address houseAddressDtoToAddress(AddressDto ha){
        Address a = new HouseAddress();
        a.setId(ha.getId());
        a.setBuildingType(1);
        a.setStreet(ha.getStreet());
        a.setNumber(ha.getNumber());
        return a;
    }

    private Address apartmentAddressDtoToAddress(AddressDto ap){
        Address a = new ApartmentAddress();
        a.setId(ap.getId());
        a.setBuildingType(2);
        a.setStreet(ap.getStreet());
        a.setNumber(ap.getNumber());
        ((ApartmentAddress)a).setFloor(((ApartmentAddressDto)ap).getFloor());
        ((ApartmentAddress)a).setApartmentNumber(((ApartmentAddressDto)ap).getApartmentNumber());

        return a;
    }
}
