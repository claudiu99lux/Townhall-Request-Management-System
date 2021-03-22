package dto;

import entity.User;

import java.util.UUID;

public abstract class AddressDto {
    private String id;
    private int buildingType; //1 = house, 2 = apartment building
    private String street;
    private String number; //String because number can be 102A
    private User user;

    public AddressDto(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(int buildingType) {
        this.buildingType = buildingType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        String type;
        if(buildingType==1)
            type = "House";
        else
            type = "Apartment";
        return type + ": " + "str. " + street + ", nr. " + number;
    }
}
