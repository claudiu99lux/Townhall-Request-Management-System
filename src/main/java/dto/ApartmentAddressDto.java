package dto;



public class ApartmentAddressDto extends AddressDto{

    private int floor;
    private int apartmentNumber;

    public ApartmentAddressDto(){
        super();
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

    @Override
    public String toString() {
        return super.toString()+
                ", etaj " + floor +
                ", ap. " + apartmentNumber;
    }
}
