package entity;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@DiscriminatorValue("2")
public class ApartmentAddress extends Address{
    @Column
    private int floor;

    @Column
    private int apartmentNumber;

    public ApartmentAddress(int buildingType, String street, String number, int floor, int apartmentNumber){
        super(buildingType, street, number);
        this.floor = floor;
        this.apartmentNumber = apartmentNumber;
    }

    public ApartmentAddress() {

    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
