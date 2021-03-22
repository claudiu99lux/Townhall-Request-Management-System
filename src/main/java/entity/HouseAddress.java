package entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class HouseAddress extends Address{
    public HouseAddress(String street, String number){
        super(1, street, number);
    }

    public HouseAddress() {}
}
