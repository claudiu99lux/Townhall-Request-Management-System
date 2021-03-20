package entity;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@DiscriminatorValue("1")
public class HouseAddress extends Address{
    public HouseAddress(int buildingType, String street, String number){
        super(buildingType, street, number);
    }

    public HouseAddress() {}
}
